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
	<form action="bookupdate" method="post" enctype="multipart/form-data">
	
		<!-- hidden으로 num읽기 -->
		<input type="hidden" name="num" value="${dto.num}">
	
		<b>책명</b>:
		<input type="text" name="bookname" value="${dto.bookname}"><br>
		
		<b>책가격</b>:
		<input type="text" name="bookprice" value="${dto.bookprice}"><br>
		
		<b>책표지</b>:
		<input type="file" name="upload" multiple="multiple"><br>
		
		<button type="submit">DB수정</button>
	</form>


</body>
</html>