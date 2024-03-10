<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user eq null }">
	<script>
		alert('존재하지 않는 회원입니다.');
		history.back();
	</script>
</c:if>
<div id="wrap">
		<form name="mf" method="post" action="memberUpdateEnd.do">
			<table border="1" style="width: 80%; margin: 3em auto">
				<tr>
					<th colspan="2">Member Update - 회원정보 수정</th>
				</tr>
				<tr>
					<th>
						이   름
					</th>
					<td>
						<input type="text" name="name" value="${user.name }">
						<%-- jsp주석
							<%=변수 %> : 변수값을 출력함
							<%=메서드 %> : 메서드가 반환하는 값을 출력
						 --%>
					</td>
				</tr>
				<tr>
					<th>
						아이디
					</th>
					<td>
						<input type="text" name="id" value="${user.id }" readonly>
					</td>
				</tr>
				<tr>
					<th>
						비밀번호
					</th>
					<td>
						<input type="password" name="pw" >
					</td>
				</tr>
				<tr>
					<th>
						연락처
					</th>
					<td>
						<input type="text" name="tel" value="${user.tel }">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="text-center">
					<button>수정처리</button> <!-- submit -->
					<button type="reset">다시쓰기</button>
					</td>
				</tr>
			</table>
			<div class="text-center">
				<a href="memberList.do">회원목록</a>
			</div>
		</form>
	</div>
