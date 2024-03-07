package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 1. 글번호, 비밀번호 값 받기
		String numStr = req.getParameter("num");
		String passwd = req.getParameter("passwd");

		// 2. 유효성 체크 list.do로 이동
		if (numStr == null || passwd == null || numStr.trim().isBlank() || passwd.trim().isBlank()) {
			this.setRedirect(true);
			this.setViewName("list.do"); // 직접 delete.do로 검색하여 들어오는 경우 list.do로 redirect
			return;
		}

		int num = Integer.parseInt(numStr);

		// 3. 글번호로 해당글을 DB에서 가져오기 getBoard(num)
		BoardDAO dao = new BoardDAO();
		BoardVO tmp = dao.getBoard(num); // 해당 글번호로 정보 가져옴

		// 4. 해당글의 비번과 사용자가 입력한 비번이 일치하면 삭제, 일치하지 않으면 history.back();
		// [4-1]. 입력한 비번이 일치하면 삭제처리 => BoardDAO의 deleteBoard(num) 호출 => msg, loc처리
		// [4-2]. 일치하지 않으면 "비밀번호 틀려요", history.back();
		String msg = "", loc = "";
		if (!passwd.equals(tmp.getPasswd())) {
			// passwd : 사용자가 입력한 비밀번호/ tmp.getPasswd() : DB가 가지고있는 비밀번호
			msg = "비밀번호가 일치하지 않습니다.";
			loc = "javascript:history.back()";
		} else {
			int n = dao.deleteBoard(num); // DB에서 해당글 지우기
			msg = (n > 0) ? "삭제성공" : "삭제실패";
			loc = (n > 0) ? "list.do" : "javascript:history.back()";

			// 5. 서버에 업로드했던 파일이 있다면 서버에서 삭제처리
			String fileName = tmp.getFileName(); // 첨부파일명
			if (fileName != null) {
				String upDir = req.getServletContext().getRealPath("/upload"); // 업로드한파일의 절대경로
				File delFile = new File(upDir, fileName);
				if (delFile.exists()) { // 디렉토리에 해당파일이 존재한다면
					boolean b = delFile.delete();
					System.out.println("파일삭제 여부 : " + b); // 삭제됐다면 true반환
				} // if-----
			} // if-----
		} // else--------

		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);

		this.setViewName("/board/message.jsp");
		this.setRedirect(false);

	}

}
