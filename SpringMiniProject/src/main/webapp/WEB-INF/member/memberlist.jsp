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

	<button type="button" class="btn btn-info" onclick="location.href='form'"
	style="margin-left: 400px;">회원가입</button>
	
	<hr>

	<table class="table table-bordered" style="width: 700px;">
		<tr style="background-color: orange;">
			<th width="70">번호</th>
			<th width="80">회원명</th>
			<th width="100">아이디</th>
			<th width="200">핸드폰</th>
			<th width="200">가입일</th>
			<th width="150">수정삭제</th>
		</tr>
		
		<c:forEach var="dto" items="${list}" varStatus="i">
			<tr>
				<td align="center">${i.count}</td>
				<td>${dto.name}</td>
				<td>${dto.id}</td>
				<td>${dto.hp}</td>
				<td>
					<fmt:formatDate value="${dto.gaipday}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<button type="button" class="btn btn-info" style="width: 60px;" onclick="">수정</button>
					<button type="button" class="btn btn-danger" style="width: 60px;" onclick="">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>