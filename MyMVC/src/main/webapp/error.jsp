<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.sql.SQLIntegrityConstraintViolationException" %>

<!-- 에러 처리 페이지가 되기 위해서는 page지시어에 isErrorPage속성값으로 true값을 주어야 한다.
		=> 내장객체 exception을 사용할 수 있다. -->
<jsp:include page="/inc/top.jsp" />

	<div class="container">
		
		<h2>서버 에러 발생</h2>
		<%
		if(exception instanceof SQLIntegrityConstraintViolationException){
			%>
			<script>
				alert('회원만 글을 쓸 수 있습니다. 로그인을 하세요');
				history.back();
			</script>
			<%
		}else{
		%>
			<h3 style='color:red'>
				<%=exception.getMessage() %>
				<%
					//디버그용
					exception.printStackTrace();
				%>
			</h3>
			[<a href="../index.jsp">Home으로 가기</a>]
			[<a href="javascript:history.back()">이전 페이지 가기</a>]
		
		<%}//else-------------- %>
	</div>
    
<jsp:include page="/inc/foot.jsp" />    