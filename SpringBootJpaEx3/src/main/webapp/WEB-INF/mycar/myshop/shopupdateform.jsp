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

	<form action="shopupdate" method="post">
	
	<!-- hidden -->
	<input type="hidden" name="num" value="${dto.num}">
	
		<table class="table table-bordered" style="width: 500px;">
			<caption><b>상품명 입력</b></caption>
				<tr>
					<th bgcolor="#ddd">상품명</th>
						<td>
							<input type="text" name="sangpum" class="form-control" style="width: 200px;"
							value="${dto.sangpum}">
						</td>
				</tr>

				<tr>
					<th bgcolor="#ddd">가격</th>
						<td>
							<input type="text" name="price" class="form-control" style="width: 200px;"
							value="${dto.price}">
						</td>
				</tr>

				<tr>
					<th bgcolor="#ddd">이미지</th>
						<td>
							<input type="text" name="photoname" class="form-control" style="width: 200px;"
							value="${dto.photoname}">
						</td>
				</tr>
				
				<tr>
					<th bgcolor="#ddd">입고일</th>
						<td>
							<input type="date" name="ipgoday" class="form-control" style="width: 200px;"
							value="${dto.ipgoday}">
						</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info" style="width: 100px;">저장</button>
						<button type="button" class="btn btn-info" style="width: 100px;"
						onclick="location.href='shoplist'">목록</button>
					</td>
				</tr>
				
		</table>
	</form>

</body>
</html>