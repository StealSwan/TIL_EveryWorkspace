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


<title>Insert title here</title>
</head>
<body>
<div class="alert alert-success" style="width: 300px; font-size: 1.5em;" >
상품명: ${shopDto.sang}<br>
색상:<b style="color: ${shopDto.color};">${shopDto.color}</b><br>
가격:<fmt:formatNumber value="${shopDto.price}" type="currency"/><br>
선택한이미지: <img alt="" src="../photo/${shopDto.image}.png">
</div>
</body>
</html>