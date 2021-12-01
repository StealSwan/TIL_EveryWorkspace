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
	
		<table class="table table-bordered" style="width: 500px;">
			<caption><b>차 정보 입력</b></caption>
				<tr>
					<th bgcolor="#ddd">자동차명</th>
						<td>
							<input type="text" name="carname" class="form-control" style="width: 200px;"
							value="${dto.carname}">
						</td>
				</tr>

				<tr>
					<th bgcolor="#ddd">단가</th>
						<td>
							<input type="text" name="carprice" class="form-control" style="width: 200px;"
							value="${dto.carprice}">
						</td>
				</tr>

				<tr>
					<th bgcolor="#ddd">자동차 색상</th>
						<td>
							<input type="color" name="carcolor" class="form-control" style="width: 200px;"
							value="${dto.carcolor}">
						</td>
				</tr>
				
				<tr>
					<th bgcolor="#ddd">구입일</th>
						<td>
							<input type="date" name="carguip" class="form-control" style="width: 200px;" value="${dto.carguip}">
						</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info" style="width: 100px;">저장</button>
						<button type="button" class="btn btn-info" style="width: 100px;"
						onclick="location.href='list'">목록</button>
					</td>
				</tr>
				
		</table>
	</form>

</body>
</html>