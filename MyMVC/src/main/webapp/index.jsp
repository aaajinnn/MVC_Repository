<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<h1>MyMVC Index Page</h1>
	<hr>
	<h2 style='color:blue'><%=request.getAttribute("msg") %></h2>
	<!-- el expression ==> ${key} ==> key에 해당하는 value값 출력-->
	<h2 style='color:red'>${msg} </h2>
</div>