<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- bbs관련 js파일 참조 -->
	<!-- -------------------------------------------------- -->
	
	<!-- -------------------------------------------------- -->
		<!-- content -->
		<div class="container">
			<h1>MVC Borad</h1>
			<br><br>
			<!-- 
			파일 업로드시 주의사항
			1. form태그내에 method는 post로 지정
			2. post방식일때 enctype을 multipart/form-data로 지정해야 한다.
			
				multipart/form-data로 지정해야 파일명과 파일데이터가 서버에 전달된다.
				
				application/x-www-form-urlencoded(디폴트) 로 지정하면 파일명만 전송된다.
			 -->
			<form name="bbsF" method="post" action="insert.do" enctype="multipart/form-data" >
			<table border="1">
				<tr>
					<th width="20%">글제목</th>
					<td width="80%">
						<input type="text" name="title" id="title" placeholder="Title" required>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="name" id="name" placeholder="Name" required>
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<textarea name="content" id="content" placeholder="Content" rows="7" cols="60"></textarea>
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
						<input type="file" name="fileName" id="fileName">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<button>글쓰기</button>
						<button type="reset">다시쓰기</button>
					</td>
				</tr>
			</table>
			</form>
		</div>