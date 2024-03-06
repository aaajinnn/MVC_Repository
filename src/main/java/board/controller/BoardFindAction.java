package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardFindAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 0. pageNum파라미터값 받기
		String pageNumStr = req.getParameter("pageNum");
		if (pageNumStr == null) {
			pageNumStr = "1"; // default는 1페이지로 지정
		}
		int pageNum = Integer.parseInt(pageNumStr.trim());
		if (pageNum < 1) { // 0이나 음수값이라면 1페이지 보여주기
			pageNum = 1;
		}

		// 검색 유형과 검색어 받기
		String findTypeStr = req.getParameter("findType");
		int findType = Integer.parseInt(findTypeStr);
		String findKeyword = req.getParameter("findKeyword");

		// BoardDAO생성 ListBoard() 호출
		BoardDAO dao = new BoardDAO();

		// 1. 총 게시글 수 가져오기
		int totalCount = dao.getFindTotalCount(findType, findKeyword);

		// 2. 한 페이지에 보여줄 목록 개수 정하기(5개)
		int oneRecordPage = 5;

		// 3. 총 페이지 수 구하기
		/*
		 * int pageCount = 1; if (totalCount % oneRecordPage == 0) { pageCount =
		 * totalCount / oneRecordPage; } else { pageCount = totalCount / oneRecordPage +
		 * 1; }
		 */
		int pageCount = (totalCount - 1) / oneRecordPage + 1;
		System.out.println("pageCount : " + pageCount);

		// 0-2. 범위에 맞지 않는 값일 때 페이지 지정
		// 4. jsp에서 페이지 네비게이션 출력 => 링크 ==> pageNum파라미터 전달
		if (pageNum > pageCount) { // 직접 url에 검색해서 들어왔을때 마지막 페이지 보여주기
			pageNum = pageCount;
		}

		// 5. pageNum을 이용해서 DB에서 끊어올 범위 정하기 ==> lisgBoard()로 start, end 매개변수 전달
		int end = pageNum * oneRecordPage;
		int start = end - (oneRecordPage - 1);

		// 5. 게시물 목록 가져오기
		List<BoardVO> boardList = dao.findBoard(start, end, findType, findKeyword); // ==> findBoard()로 매개변수 전달

		// 반환하는 List<BoardVO> 객체를 req에 저장 ===> jsp에서 불러오기 할수있음
		req.setAttribute("boardAll", boardList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("findKeyword", findKeyword);

		this.setViewName("find.jsp");
		this.setRedirect(false);
	}

}
