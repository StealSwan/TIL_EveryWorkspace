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

	<c:set var="root" value="<%=request.getContextPath() %>"/>

<body>

	<!-- 메인이미지 -->
	<a href="${root }/">
		<img alt="" src="${root}/photo/K-055.png" width="400px;">
	</a>
	
	<b>SpringBoot + Mybatis + Tiles</b>
	
	&nbsp;&nbsp;&nbsp;&nbsp;
	
	<span style="margin-right: 50px;">
		<c:if test="${sessionScope.loginok==null}">
			<button type="button" class="btn btn-success"
			onclick="location.href='${root}/login/main'">로그인</button>
		</c:if>
		
		<c:if test="${sessionScope.loginok!=null}">
			<b>${sessionScope.myid}님이 로그인 중입니다.</b>
			<button type="button" class="btn btn-danger" style="width: 100px;"
			onclick="location.href='${root}/login/logoutprocess'">로그아웃</button>			
		</c:if>
	</span>

</body>
</html>