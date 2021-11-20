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

	<span class="alert alert-danger">
		<b>총 ${totalCount} 개의 데이터가 있습니다</b>
	</span>
	
	<br><br>
	
	<button type="button" class="btn btn-info" onclick="location.href='writeform'"
	style="margin-left: 400px;">글쓰기</button>
	
	<hr>

	<table class="table table-bordered" style="width: 700px;">
		<tr style="background-color: orange;">
			<th width="50">번호</th>
			<th width="200">제목</th>
			<th width="80">작성자</th>
			<th width="120">작성일</th>
		</tr>
		
		<c:forEach var="dto" items="${list}" varStatus="i">
			<tr>
				<td align="center">${i.count}</td>
				<td>
					<a href="content?num=${dto.num}">${dto.subject}</a>
				</td>
				<td>${dto.writer}</td>
				<td>
					<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>