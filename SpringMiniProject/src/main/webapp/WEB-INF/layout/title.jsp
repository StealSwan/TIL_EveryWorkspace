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

	<a href="${root}/home"><img alt="" src="${root}/image/mask2.jpg" width="250px;" align="middle"></a>
	<span style="font-size: 15pt; font-weight: bold;">Spring+Mybatis+Tiles</span>
	
	<span style="margin-left: 20px;">
		<c:if test="${sessionScope.loginok==null}">
			<button type="button" class="btn btn-info" onclick="location.href='login'">로그인</button>
		</c:if>
		
		<c:if test="${sessionScope.loginok!=null}">
			<b>${sessionScope.myid}님</b>
		</c:if>
	</span>
	
</body>
</html>