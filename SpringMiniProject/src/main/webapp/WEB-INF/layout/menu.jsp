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

<!-- 경로 -->
<c:set var="root" value="<%=request.getContextPath() %>"></c:set>

<title>Insert title here</title>
</head>
<body>

	<a href="${root}/home">Home</a> &nbsp;&nbsp;
	<a href="${root}/login/loginmain">Login</a> &nbsp;&nbsp;
	<a href="${root}/member/form">회원가입</a> &nbsp;&nbsp;	<!-- 입력시 해당 매핑주소를 가진 컨트롤러로 이동 -->
	<a href="${root}/board/list">회원게시판</a> &nbsp;&nbsp;
	
</body>
</html>