package common.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static String addMsgLoc(HttpServletRequest req, String msg, String loc) {

		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);

		return "/message.jsp";
	}// -----------------------------------

	// 메시지 추가하고 history.back()
	public static String addMsgBack(HttpServletRequest req, String msg) {

		req.setAttribute("msg", msg);
		req.setAttribute("loc", "javascript:history.back()");

		return "/message.jsp";
	}// -----------------------------------

}
