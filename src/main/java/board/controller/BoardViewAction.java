package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String numStr = req.getParameter("num");
		int num = Integer.parseInt(numStr); // 글번호

		BoardDAO dao = new BoardDAO();

		List<BoardVO> boardView = dao.getBoard(num);

		req.setAttribute("boardView", boardView);

		this.setViewName("/board/view.jsp");
		this.setRedirect(false);
//		System.out.println(num);
	}

}
