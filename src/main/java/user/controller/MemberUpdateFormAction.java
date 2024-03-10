package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAOMyBatis;
import user.model.MemberVO;

public class MemberUpdateFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		if (id == null || id.trim().isBlank()) {
			this.setViewName("memberList.do");
			this.setRedirect(true);
			return;
		}

		MemberDAOMyBatis dao = new MemberDAOMyBatis();

		// 회원정보 가져오기 memberVO반환
		MemberVO user = dao.getMember(id);

		req.setAttribute("user", user);
		this.setViewName("/member/edit.jsp");
		this.setRedirect(false);

	}

}
