<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.wrap header, footer{
		display:none;
	}
</style>
<script>
	function id_check(){
		if(!idf.id.value){
			alert('아이디를 입력하세요.');
			idf.id.focus();
			return false;
		}
		return true;
	}
</script>
<!-- idCheck.jsp -->
<div class="container">
	<form name="idf" action="idCheck.do" method="post" onsubmit="return id_check()"> 
		<label for=id>아이디</label>
		<input type="text" name="id" placeholder="ID" autofocus="autofocus">
		<button class="btn">확   인</button>
	</form>
</div>