<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class = "container">
	<h1> MVC Board</h1>
	<br><br>
	
	<table border="1">
	<c:if test="${boardView!=null ||  not empty boardView }">
		<c:forEach var="board" items="${boardView }">
        <tr>
            <td width="20%" class="m1"><b>글번호</b></td>
            <td width="30%">
            ${board.num }
            </td>
            <td width="20%" class="m1"><b>작성일</b></td>
            <td width="30%">
            ${board.wdate }
            </td>
        </tr>
        <tr>
            <td width="20%"  class="m1"><b>글쓴이</b></td>
            <td width="30%">${board.name } </td>
            <td width="20%"  class="m1"><b>조회수</b></td>
            <td width="30%">${board.readnum } </td>
        </tr>
        <tr>
            <td width="20%"  class="m1"><b>첨부파일</b></td>
            <td colspan="3" class="text-left">&nbsp; 
            <!-- 첨부파일이 있다면 --> 
            a[ a ]bytes
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>제목</b></td>
            <td colspan="3">
            ${board.title }
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>글내용</b></td>
            <td colspan="3">
            ${board.content }
            </td>
        </tr>
        	</c:forEach>
        </c:if>
        <tr>
            <td colspan="4" class="text-center">
                <a href="#"    onclick="goEdit()">글수정</a>| 
                <a href="#" onclick="goDel()">글삭제</a> |
                
        </td>
        </tr>
    </table>
</div>