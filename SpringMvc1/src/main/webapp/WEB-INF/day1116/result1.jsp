<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

	<h2>여기는 result1.jsp입니다</h2>
	<h3>HomeController로부터 포워드</h3>
	
	<b>이름: ${name}</b><br>
	<!-- requsetScope는 생략가능 -->
	<b>이름: ${requestScope.name}</b><br>
	
	<b>주소: ${addr}</b>

</body>
</html>