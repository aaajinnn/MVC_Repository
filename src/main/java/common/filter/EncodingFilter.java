package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
//필터 등록 방법
// [1] web.xml에 등록하는 방법
/*=======================================
 *   <filter>
  	<filter-name>encodingFilter</filter-name>
  	<filter-class>common.filter.EncodingFilter</filter-class>
  	<init-param>
  		<param-name>encoding</param-name>
  		<param-value>UTF-8</param-value>
  	</init-param>
  </filter>
  <filter-mapping>
  	<filter-name>encodingFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
 *=======================================
 * */
// [2] @WebFilter 어노테이션을 이용해 등록

@WebFilter(urlPatterns = { "/*" }, // 모든 요청일때 동작
		initParams = { @WebInitParam(name = "encoding", value = "UTF-8") })

// web.xml로 등록하지 않을 시는 이렇게 anntation을 사용한다.(둘중 하나만 사용가능)
public class EncodingFilter extends HttpFilter implements Filter {

	private String charset; // 초기화 파라미터(@WebFilter의 initParams의 utf-8값을 얻어옴)

	// Filter의 추상메서드
	// init과 destroy는 딱 한번만 사용
	public void init(FilterConfig fConfig) throws ServletException { // filterConfig를 통해 initPrams값을 얻어옴

		charset = fConfig.getInitParameter("encoding");
		// System.out.println("EncodingFilter init()... charset : " + charset);

	}// -----------------------------------------

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// System.out.println("EncodingFilter doFilter()...");
		request.setCharacterEncoding(charset); // 인코딩 설정

		// filter chain에따라 요청을 전달한다
		chain.doFilter(request, response);

	}// -----------------------------------------

	public void destroy() {

		// System.out.println("EncodingFilter destroy()...");
	}// -----------------------------------------

}
