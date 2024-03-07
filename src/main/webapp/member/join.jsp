<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<form name="mf" method="post" action="<%=request.getContextPath()%>/signup.do">
		<!-- action : 서블렛으로 이동 -->
		<table border="1" style="width: 80%; margin: 3em auto">
			<tr>
				<th colspan="2">Signup - 회원가입</th>
			</tr>
			<tr>
				<th>이 름</th>
				<td><input type="text" name="name" required> <!-- VO의 멤버변수명과 맞춰주는것이 좋다. -->
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="id" required>
					<button type="button" class="btn">아이디 중복체크</button>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" required></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" name="tel"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button>회원가입</button> <!-- submit -->
					<button type="reset">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
