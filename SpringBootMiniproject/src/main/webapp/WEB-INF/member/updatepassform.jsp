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

	<div style="margin-left: 200px;">
		<!-- 여기서 처리가 되어야 update로 넘어가는 구조 -->
		<form action="updatepass" method="post">
			
			<!-- hidden -->
			<input type="hidden" name="num" value="${num}">
			
			<b>수정에 필요한 비밀번호를 입력해주세요</b>
			
			<br><br>
			
			<div>
				<input type="text" name="pass" class="form-control"
				style="width: 120px;" required="required" autofocus="autofocus">
				
				<button type="submit" class="btn btn-info">확인</button>
			</div>
		</form>
	</div>

</body>
</html>