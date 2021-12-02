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

	<!-- enctype의 multipart/form-data 가 업로드의 첫걸음!!! -->
	<form action="insert" method="post" enctype="multipart/form-data">
		<b>상품명</b>:
		<input type="text" name="sang" required="required"><br>
		
		<b>단가</b>:
		<input type="text" name="dan" required="required"><br>
		
		<b>이미지</b>:
		<input type="file" name="photo" required="required"><br>
		
		<button type="submit">DB에 저장</button>
	</form>

</body>
</html>