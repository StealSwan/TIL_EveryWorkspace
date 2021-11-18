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

	<form action="process2" method="post">
		<table class="table table-bordered" style="width: 300px;">
			<tr>
				<th style="width: 100px;">상품명</th>
				<td>
					<input type="text" name="sang" class="form-control">
				</td>
			</tr>


			<tr>
				<th style="width: 100px;">수량</th>
				<td>
					<input type="text" name="su" class="form-control">
				</td>
			</tr>


			<tr>
				<th style="width: 100px;">단가</th>
				<td>
					<input type="text" name="dan" class="form-control">
				</td>
			</tr>
			
			<tr>
				<th style="width: 100px;">색상</th>
				<td>
					<input type="color" name="color" class="form-control">
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<button type="submit" class="btn btn-info btn-sm">서버에전송</button>
			</tr>
			
		</table>
	</form>

</body>
</html>