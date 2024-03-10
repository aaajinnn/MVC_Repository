package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.MemberDAOMyBatis;
import user.model.MemberVO;

public class MemberUpdateAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 수정할 때는 항상 수정하려는 테이블의 pk가 있어야함
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String tel = req.getParameter("tel");

		if (id == null || name == null || pw == null || id.trim().isBlank() || pw.trim().isBlank()
				|| name.trim().isBlank()) {
			this.setViewName("memberList.do");
			this.setRedirect(true);
			return;
		}

		MemberVO user = new MemberVO(id, pw, name, tel, null);
		MemberDAOMyBatis dao = new MemberDAOMyBatis();
		int n = dao.updateMember(user);

		String msg = (n > 0) ? "회원정보 수정 완료" : "수정 실패";
		String loc = (n > 0) ? "memberList.do" : "javascript:history.back()";

		this.setViewName(CommonUtil.addMsgLoc(req, msg, loc));
		this.setRedirect(false);
	}

}
