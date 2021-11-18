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
	
	<form action="read">
		<table class="table table-bordered" style="width: 400px;">
			<tr>
				<th bgcolor="pink">아이디</th>
				<td>
					<input type="text" name="myid" class="form-control">
				</td>
			</tr>

			<tr>
				<th bgcolor="pink">비밀번호</th>
				<td>
					<input type="password" name="mypass" class="form-control"
					width="150px;">
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn btn-successs">데이터 전송</button>
				</td>
			</tr>

		</table>
	</form>
	
</body>
</html>