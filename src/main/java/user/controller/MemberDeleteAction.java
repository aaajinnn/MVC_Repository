package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.MemberDAOMyBatis;
import user.model.MemberVO;

public class MemberDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");

		if (id == null || id.trim().isBlank()) {
			this.setViewName("javascript:history.back()");
			this.setRedirect(true);
			return;
		}
		MemberDAOMyBatis dao = new MemberDAOMyBatis();

		int n = dao.deleteMember(id);

		String msg = (n > 0) ? "삭제 완료" : "삭제 실패";
		String loc = (n > 0) ? "memberList.do" : "javascript:history.back()";

		String viewName = CommonUtil.addMsgLoc(req, msg, loc);

		this.setViewName(viewName);
		this.setRedirect(false);
	}

}
