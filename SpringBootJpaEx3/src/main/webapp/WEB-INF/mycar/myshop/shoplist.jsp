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

<style type="text/css">

	.box{
		width: 40px;
		height: 40px;
		border: 1px solid gray;
		border-radius: 100px;
	}

</style>

<title>Insert title here</title>
</head>
<body>

	<!-- static에 넣으면 경로 지정없이 바로 나온다 -->
	
	<button type="button" class="btn btn-info"
	style="width: 150px; margin-left: 200px; margin-top: 50px;"
	onclick="location.href='shopform'">상품정보 추가</button>
	
	<br><br>
	<h3 class="alert alert-success" style="width: 800px;">
		<b>총 ${count}개의 상품 정보가 있습니다</b>
	</h3>
	
	<table class="table table-bordered" style="width: 800px;">
		<caption><b>상품 정보</b></caption>
		<tr>
			<th width="50">no</th>
			<th width="130">상품명</th>
			<th width="80">가격</th>
			<th width="130">사진</th>
			<th width="150">입고일</th>
			<th width="150">편집</th>
		</tr>
		
		<c:forEach var="dto" items="${list}" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td>${dto.sangpum}</td>
				<td>
					<fmt:formatNumber value="${dto.price}" type="currency"/>
				</td>
				<td>
					<img alt="" src="${dto.photoname}" class="box">
				</td>
				<td>${dto.ipgoday}</td>
				
				<td>
					<button type="button" class="btn btn-info btn-xs"
					onclick="location.href='shopupdateform?num=${dto.num}'">수정</button>

					<button type="button" class="btn btn-danger btn-xs"
					onclick="location.href='shopdelete?num=${dto.num}'">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>