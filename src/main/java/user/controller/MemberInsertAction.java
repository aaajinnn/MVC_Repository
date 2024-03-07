package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.MemberDAOMyBatis;
import user.model.MemberVO;

public class MemberInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String tel = req.getParameter("tel");

		if (name == null || id == null || pw == null || name.trim().isBlank() || id.trim().isBlank()
				|| pw.trim().isBlank()) {
			this.setViewName("join.do");
			this.setRedirect(true);
			return;
		}
		MemberVO user = new MemberVO(id, pw, name, tel, null);

		MemberDAOMyBatis dao = new MemberDAOMyBatis();

		int n = dao.insertMember(user);

		String msg = (n > 0) ? "회원가입 완료! 로그인 페이지로 이동합니다." : "회원가입 실패-아이디 중복체크 하세요";
		String loc = (n > 0) ? "login.do" : "javascript:history.back()";

		String viewName = CommonUtil.addMsgLoc(req, msg, loc);

		this.setViewName(viewName);
		this.setRedirect(false);
	}

}
