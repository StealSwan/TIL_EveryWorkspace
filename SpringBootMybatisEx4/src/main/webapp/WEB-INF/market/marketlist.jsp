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
	
	<button type="button" class="btn btn-info"
	style="width: 100px;" onclick="location.href='form'">상품추가</button>

	<br>

	<c:if test="${totalCount==0}">
		<div class="alert alert-info"><b>저장된 상품정보가 없습니다</b></div>
	</c:if>
	
	<c:if test="${totalCount>0}">
		<div class="alert alert-danger"><b>총 ${totalCount}개의 상품정보가 있습니다</b></div>
	</c:if>

	<br>
	
	<c:forEach var="a" items="${list}">
		<table class="table table-bordered" style="width: 500px;">
			<tr>
				<td>
					<img alt="" src="photo/${a.photoname}" style="max-width: 150px;" align="left">
					<h4> 상품명: ${a.sang}</h4>
					<h4> 단가: <fmt:formatNumber value="${a.dan}" type="currency"/></h4>
					<h4> 입고일: <fmt:formatDate value="${a.ipgo}" pattern="yyyy-MM-dd HH:mm"/></h4>
					
					<button type="button" class="btn btn-info btn-xs" style="width: 60px;"
					onclick="location.href='updateform?num=${a.num}'">상품수정</button>

					<button type="button" class="btn btn-info btn-xs" style="width: 60px;"
					onclick="location.href='delete?num=${a.num}'">상품삭제</button>
				</td>
			</tr>
		</table>
	</c:forEach>

</body>
</html>