<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//컨텍스트명 구하기
	String ctx = request.getContextPath(); // "/MyWeb"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyWeb</title>
<link rel="stylesheet" type="text/css" href="<%=ctx %>/css/style.css">
</head>
<body>

	<div class="wrap">
		<header>
			<!-- top menu -->
			<ul class="topMenu">
				<!-- 어디서 include 될지 모르기때문에 컨텍스트명을 기준으로 절대경로 설정 -->
				<li><a href="<%=ctx %>/index.do">Home</a></li> 
				<li><a href="<%=ctx%>/login.do">로그인</a></li>
				<li><a href="#">a님 로그인 중...</a></li>
				<li><a href="<%=ctx%>/logout.do">로그아웃</a></li>
				<li><a href="<%=ctx %>/join.do">회원가입</a></li>
				<li><a href="<%=ctx %>/admin/memberList.do">회원목록</a></li>
				<li><a href="<%=ctx %>/board/list.do">게시판 글목록</a></li>
				<li><a href="<%=ctx %>/user/mypage.do">MyPage</a></li>
			</ul>
		</header>
