<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=Gugi&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<!-- 세션이 종료되면 로그인 페이지로 가도록 -->
	<c:if test="${sessionScope.loginok==null}">
		<c:redirect url="../login"/>
	</c:if>
	
	
	<form action="insert" method="post" enctype="multipart/form-data">
	
		<!-- hidden -->
		<input type="hidden" name="writer" value="${name}">
		<input type="hidden" name="id" value="${sessionScope.myid}">
		<input type="hidden" name="currentPage" value="${currentPage}">
	
	
		<table class="table table-bordered" style="width: 500px;">
			<caption><b>글쓰기</b></caption>
				<tr>
					<th bgcolor="#ddd" width="120">이름(아이디)</th>
					<td>
						<!-- mview에 name으로 저장해놓은 값 -->
						<b>${name}(${sessionScope.myid})</b>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="#ddd" width="120">제목</th>
					<td>
						<input type="text" name="subject" class="form-control" required="required">
					</td>
				</tr>

				<tr>
					<th bgcolor="#ddd" width="120">사진</th>
					<td>
						<input type="file" name="upload" class="form-control" multiple="multiple">
					</td>
				</tr>

				<tr>
					<th bgcolor="#ddd" width="120">내용</th>
					<td>
						<textarea style="width: 350px; height: 150px;"
						name="content" required="required"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info">글저장</button>
						<button type="button" class="btn btn-default">이전</button>
					</td>
				</tr>
				
		</table>
	</form>

</body>
</html>