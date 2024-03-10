package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.MemberDAOMyBatis;

//idCheck
public class IdCheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 요청방식에 따라 로직을 다르게 처리하자
		// 팝업으로띄웠을때는 get, 확인눌렀을때는 post(결과를보여줌)
		String method = req.getMethod();
		System.out.println("method : " + method);
		// get방식 요청 => id입력 form 보여주고
		// post방식 요청 => id 사용가능 여부를 보여주자
		if (method.equalsIgnoreCase("get")) { // get방식
			this.setViewName("/member/idCheck.jsp");
		} else { // post방식
			String id = req.getParameter("id");
			if (id == null || id.trim().isBlank()) {
				this.setViewName(CommonUtil.addMsgBack(req, "아이디를 입력하세요"));
				this.setRedirect(false);
				return;
			}
			MemberDAOMyBatis dao = new MemberDAOMyBatis();
			boolean isUse = dao.idCheck(id);
			// 사용가능하면 true반환, 중복아이디면 false반환
			String msg = (isUse) ? id + "는 사용 가능합니다" : id + "는 이미 사용중 입니다.";
			String result = (isUse) ? "ok" : "fail";

			req.setAttribute("msg", msg);
			req.setAttribute("result", result);
			req.setAttribute("uid", id);

			this.setViewName("/member/idCheckResult.jsp");
		}
		this.setRedirect(false);

	}

}
