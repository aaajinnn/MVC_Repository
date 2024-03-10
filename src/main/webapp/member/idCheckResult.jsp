<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.wrap header, footer {
	display: none;
}
</style>
<script>
	function id_check() {
		if (!idf.id.value) {
			alert('아이디를 입력하세요.');
			idf.id.focus();
			return false;
		}
		return true;
	}
	
	function setId(uid){
		//window > document > form
		//opener ==> 부모창(window객체)
		opener.document.mf.id.value=uid;
		
		//self ==> 자기창
		self.close();
	}
</script>
<!-- idCheckResult.jsp -->
<div class="container">
	<div id="idResult" style="margin:1em auto">
		<h3 style='color:red'>${msg }</h3>
		<br>
		<c:if test="${result eq 'ok' }">
			<button onclick="setId('${uid}')">아이디 사용하기</button>
		</c:if>
	</div>

	<form name="idf" action="idCheck.do" method="post"
		onsubmit="return id_check()">
		<label for=id>아이디</label> <input type="text" name="id"
			placeholder="ID" autofocus="autofocus">
		<button class="btn">확 인</button>
	</form>
</div>