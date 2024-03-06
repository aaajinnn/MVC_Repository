package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 0. 파일 업로드 처리
		// <1> 업로드할 디렉토리의 절대경로 얻기
		ServletContext application = req.getServletContext();
		String upDir = application.getRealPath("/upload"); // application/upload
		// 이클립스의 upload폴더에는 파일이 들어오지않음, upDir에는 업로드됨
		System.out.println("upDir : " + upDir);

		// <2> cos.jar의 MultipartRequest 객체를 생성 ==> 알아서 업로드 처리를 해줌
		MultipartRequest mreq = null;
		try {
			// 동일한 파일명일때 파일명에 인덱스 번호를 붙인다. => 덮어쓰기 방지
			DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
			mreq = new MultipartRequest(req, upDir, 100 * 1024 * 100, "utf-8", df); // 업로드 최대용량 : 100MB
		} catch (IOException e) {
			System.out.println("파일 업로드 실패 : " + e);
			// throw new ServletException(e); // 파일용량 초과 또는 enctype이 multipart/form-data가
			// 아니거나
			return;
		}

		// model1 방식에서 jsp에 작성했던 코드를 XXXAction에 작성해준다.(insert.jsp -> BoardInsertAction)
		// 1.사용자가 입력한 값 받기
		String name = mreq.getParameter("name"); // MultipartRequest를 이용해 파라미터값 추출
		String passwd = mreq.getParameter("passwd");
		String title = mreq.getParameter("title");
		String content = mreq.getParameter("content");
		System.out.println("name : " + name);

		// 첨부파일명, 파일크기 받기 => 나중에
		// 첨부파일명 ==> getParameter가 아님!!! 메소드주의할것!!!!
		String fileName = mreq.getFilesystemName("fileName");

		// 파일크기
		File file = mreq.getFile("fileName");
		long fileSize = 0;
		if (file != null) { // 파일첨부를 했다면
			fileSize = file.length(); // 파일크기
		}

		// 2. 유효성 체크
		if (name == null || passwd == null || title == null || name.trim().isBlank() || passwd.trim().isBlank()
				|| title.trim().isBlank()) {
			this.setRedirect(true); // redirect는 여기서하지않음(프론트컨트롤러에서)
			this.setViewName("input.do"); // input.do로 redirect 이동 ===> 프론트컨트롤러에서 알아서 이동시킴

			return;
		}

		// 3. 1번에서 받은 값 BoardVO객체에 담기
		BoardVO vo = new BoardVO(0, name, passwd, title, content, null, 0, fileName, fileSize);

		// 4. BoardDAO생성 후 insertBoard() 호출
		BoardDAO dao = new BoardDAO();
		int n = dao.insertBoard(vo);

		// 5. 그 결과 메시지, 이동경로 설정해서 req에 저장하기
		String msg = (n > 0) ? "글 등록 완료" : "글 등록 실패";
		String loc = (n > 0) ? "list.do" : "javascript:history.back()";

		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);

		this.setViewName("/board/message.jsp");
		this.setRedirect(false);
	}

}
