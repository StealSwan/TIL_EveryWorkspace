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
<div class="alert alert-info" style="width: 400px; font-size: 1.5em;" >

<h2>Map으로 읽어서 출력</h2>
상품명: ${sang}<br>
색상:<b style="color: ${color};">${color}</b><br>
가격:<fmt:formatNumber value="${price}" type="currency"/><br>
선택한이미지: <img alt="" src="../photo/${image}.png">
</div>
</body>
</html>