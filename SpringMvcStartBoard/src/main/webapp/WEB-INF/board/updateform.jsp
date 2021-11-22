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

	<form action="update" method="post">

	<!-- hidden -->
	<input type="hidden" name="num" value="${dto.num}">
	
		<table class="table table-bordered" style="width: 400px;">
			<caption>글수정</caption>
				<tr>
					<th width="100">작성자</th>
					<td>
						<!-- dto랑 name 맞춰주기 -->
						<input type="text" name="writer" class="form-control" style="width: 100px;"
						value="${dto.writer}">
					</td>
				</tr>

				<tr>
					<th width="100">제목</th>
					<td>
						<!-- dto랑 name 맞춰주기 -->
						<input type="text" name="subject" class="form-control" style="width: 300px;"
						value="${dto.subject}">
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<textarea style="width: 400px; height: 150px;"
						class="form-control" name="content">${dto.content}</textarea>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info">db 수정</button>
					</td>
				</tr>
		</table>
	</form>

</body>
</html>