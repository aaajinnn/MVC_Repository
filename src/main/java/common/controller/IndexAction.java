package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.MemberDAOMyBatis;

//SubController(일꾼) => AbstractAction추상클래스를 상속받는다. ==> execute()메서드를 구현해아함(override)
//index.do ==> IndexAction을 찾아서 execute()호출 한 뒤 ==> index.jsp로 forward이동해서 응답
//매핑 정보는 MyMVC/WEB-INF/command.properties 파일에 저장되어 있다.
public class IndexAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("IndexAction의 execute() 호출됨...");

		MemberDAOMyBatis dao = new MemberDAOMyBatis();
		int cnt = dao.getMemberCount();

		req.setAttribute("msg", "MyBatis ORM Framework를 사용해봅시다.");
		req.setAttribute("cnt", cnt);

		// 뷰페이지 지정
		this.setViewName("index.jsp");

		// 이동방식 지정
		this.setRedirect(false); // forward방식으로 이동, true로 한다면 redirect => X (항상 frontController을 거쳐가야함)
	}

}
