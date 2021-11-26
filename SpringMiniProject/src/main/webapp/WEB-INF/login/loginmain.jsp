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

	<form action="loginprocess" method="post">
		<table class="table table-bordered" style="width: 300px;">
			<caption><b>회원 로그인</b></caption>
				<tr>
					<th bgcolor="#ddd" width="150">아이디</th>
					<td>
						<input type="text" name="id" class="form-control"
						style="width: 150px;" placeholder="아이디" required="required">
					</td>
				</tr>

				<tr>
					<th bgcolor="#ddd" width="150">비밀번호</th>
					<td>
						<input type="password" name="pass" class="form-control"
						style="width: 150px;" placeholder="비밀번호" required="required">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info" style="width: 120px;">로그인</button>
						<button type="button" class="btn btn-success" style="width: 120px;"
						onclick="location.href='member/form'">회원가입</button>
					</td>
				</tr>
		</table>
	</form>

</body>
</html>