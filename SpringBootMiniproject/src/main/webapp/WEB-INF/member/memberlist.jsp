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

	<h3 class="alert alert-info" style="width: 900px;">총 ${count}명의 회원이 가입되어 있습니다</h3>

	<table class="table table-bordered" style="width: 900px;">
		<caption><b>전체 회원 목록</b></caption>
		<tr bgcolor="#ccf">
			<th width="50">번호</th>
			<th width="70">회원명</th>
			<th width="70">아이디</th>
			<th width="120">핸드폰</th>
			<th width="150">주소</th>
			<th width="150">이메일</th>
			<th width="100">가입일</th>
			<th width="100">수정/삭제</th>
		</tr>
		
		
		<c:forEach var="a" items="${list}" varStatus="i">
			 <tr>
			 	<td align="center">${i.count}</td>
			 	<td>${a.name}</td>
			 	<td>${a.id}</td>
			 	<td>${a.hp}</td>
			 	<td>${a.addr}</td>
			 	<td>${a.email}</td>
			 	<td>
			 		<fmt:formatDate value="${a.gaipday}" pattern="yyyy-MM-dd"/>
			 	</td>
			 	<td>
			 		<button type="button" class="btn btn-info btn-xs"
			 		onclick="location.href='updatepassform?num=${a.num}'">수정</button>

			 		<button type="button" class="btn btn-info btn-xs"
			 		onclick="location.href='delete?num=${a.num}'">삭제</button>
			 	</td>
			 </tr>
		</c:forEach>
	</table>

</body>
</html>