package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardUpdateFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 1. 글번호, 비번 받기
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

		// 4. 비번 일치 여부 체크
		// 일치하면 3번에서 가져온 vo객체를 req에 저장
		if (tmp.getPasswd().equals(passwd)) { // 비번 일치

			req.setAttribute("board", tmp);

			this.setViewName("/board/edit.jsp");
			this.setRedirect(false);
		} else { // 비번 불일치
			req.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewName("/board/message.jsp");
			this.setRedirect(false);
		}
	}

}
