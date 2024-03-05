<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!-- -------------------------------------------------------- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- -------------------------------------------------------- -->
<!-- 제어문은 core태그에 모여있음 
	 숫자, 날짜 format은 fmt태그에 있음 -->
	 
		<!-- content -->
		<div class="container">
			<h1>MVC Board</h1>
			<br>
			[<a href="input.do">글쓰기</a>]
			<br><br>
			<table border="1">
				<tr>
					<th class="m1" width="10%">글번호</th>
					<th class="m1" width="40%">글제목</th>
					<th class="m1" width="20%">작성자</th>
					<th class="m1" width="20%">작성일</th>
					<th class="m1" width="10%">조회수</th>
					
				</tr>
				<!-- ----------------------------boardAll.size()==0 -->
				<c:if test="${boardAll == null || empty boardAll } ">
					<tr>
						<td colspan="5">
						<b>데이터가 없습니다.</b>
						</td>
					</tr>
				</c:if>
				<c:if test="${boardAll != null && not empty boardAll }">
				<%--for(BoardVO board : boardAll){...} --%>
					<c:forEach var="board" items="${boardAll }"><!--var="변수명(속성)" items="자료구조(값)"  -->
						<tr>
							<td>${board.num }</td>
							<td>
							<a href="view.do?num=${board.num}">${board.title }</a>
							
							</td>
							<td>${board.name }</td>
							<td>
							<fmt:formatDate value="${board.wdate }" pattern="yyyy-MM-dd"/>
							</td>
							<td>${board.readnum }</td>
						</tr>
					</c:forEach>
				</c:if>
				<!-- ----------------------------------------------- -->
				<tr>
					<td colspan="3" style="text-align:center">
					<!-- 페이지 네비게이션 -->
					<c:forEach var="i" begin="1" end="${pageCount }" step="1"><!-- step : 디폴트 1 -->
						[<a href="list.do?pageNum=${i}"   <c:if test="${pageNum == i }">class='active'</c:if>     > ${i} </a>]
					</c:forEach>
					</td>
					<td colspan="2" style="text-align:center">
						총 게시글 수
						<span style="color:red;font-weight:bold">${totalCount }</span>개
					</td>
				</tr>
			</table>
		</div>
