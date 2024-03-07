<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- bbs관련 js파일 참조 -->
	<!-- -------------------------------------------------- -->
	
	<!-- -------------------------------------------------- -->
		<!-- content -->
		<div class="container">
			<h1>MVC Borad 글수정</h1>
			<br><br>
			<c:choose>
				<c:when test="${board eq null }"> <!-- if -->
					<script>
						alert('해당 글은 존재하지 않습니다.');
						history.back();
					</script>
				</c:when>
				<c:otherwise> <!-- else -->
				<!-- 파일업로드 : post, enctyle="multipart/form-data" -->
			<form name="bbsF" method="post" action="updateEnd.do"  enctype="multipart/form-data">
			<!-- hidden filed 글번호-------------------------- -->
			<input type="hidden" name="num" value="${board.num }">
			<!-- ---------------------------------------------- -->
			<table border="1">
				<tr>
					<th width="20%">글제목</th>
					<td width="80%">
						<input type="text" name="title" value="${board.title }" id="title" placeholder="Title" required>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="name" value="${board.name }" id="name" placeholder="Name" required>
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<textarea name="content" id="content" placeholder="Content" rows="7" cols="60">${board.content }
						</textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="passwd" id="passwd" placeholder="Password"  required>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:if test="${board.fileName ne null}">
						${board.fileName } [${board.fileSize } bytes]
						<br>
						</c:if>
						<!--old파일이 있다면 삭제처리 => 옛 첨부파일을 hidden으로 넘기자 -->
						<input type="hidden" name="old_fileName" value="${board.fileName }">
						<!-- --------------------------------------------------------- -->
						<input type="file" name="fileName" id="fileName">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<button>글수정</button>
						<button type="reset">다시쓰기</button>
					</td>
				</tr>
			</table>
			</form>
				</c:otherwise>
			</c:choose>
		</div>