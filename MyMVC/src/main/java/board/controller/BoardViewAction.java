package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String numStr = req.getParameter("num");
		if (numStr == null) {
			this.setRedirect(true); // redirect방식으로 이동
			this.setViewName("list.do"); // jps를 막아두었기때문에 XXX.do로 이동하여야함
			return;
		}

		int num = Integer.parseInt(numStr.trim()); // 글번호

		BoardDAO dao = new BoardDAO();

		// 1. 조회수 증가
		dao.updateReadNum(num);

		// 2. 해당 글 가져오기

		BoardVO vo = dao.getBoard(num);

		req.setAttribute("vo", vo);

		this.setViewName("/board/view.jsp");
		this.setRedirect(false);

	}

}
