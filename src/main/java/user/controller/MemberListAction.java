package user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAOMyBatis;
import user.model.MemberVO;

public class MemberListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 페이징처리------------------------
		String pageNumStr = req.getParameter("pageNum");
		if (pageNumStr == null || pageNumStr.trim().isBlank()) {
			pageNumStr = "1";
		}
		int pageNum = Integer.parseInt(pageNumStr.trim());

		MemberDAOMyBatis dao = new MemberDAOMyBatis();

		// 페이징처리------------------------
		int userCount = dao.getMemberCount();
		int oneRecordPage = 5;
		int pageCount = (userCount - 1) / oneRecordPage + 1;

		if (pageNum > pageCount) {
			pageNum = pageCount;
		}
		int end = pageNum * oneRecordPage;
		int start = end - (oneRecordPage - 1);

		List<MemberVO> userList = dao.listMember(start, end);

		req.setAttribute("memberAll", userList);

		// 페이징처리------------------------
		req.setAttribute("totalCount", userCount);
		req.setAttribute("pageCount", pageCount);

		this.setViewName("/member/list.jsp");
		this.setRedirect(false);

	}

}
