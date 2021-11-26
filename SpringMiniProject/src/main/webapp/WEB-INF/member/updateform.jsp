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

	<form action="update" method="post" class="form-inline">
	
		<!-- hidden -->
		<input type="hidden" name="num" value="${dto.num}">
	
		<table class="table table-bordered" style="width: 500px;">
			<caption><b>회원가입</b></caption>
				<tr>
					<th bgcolor="orange" width="120px;">회원명</th>
					<td>
						<input type="text" name="name" placeholder="회원명" required="required"
						class="form-control" style="width: 130px;" value="${dto.name}">
					</td>
				</tr>

				<tr>
					<th bgcolor="orange" width="120px;">연락처</th>
					<td>
						<input type="text" name="hp" placeholder="핸드폰번호" required="required"
						class="form-control" style="width: 200px;" value="${dto.hp}">
					</td>
				</tr>

				<tr>
					<th bgcolor="orange" width="120px;">비밀번호</th>
					<td>
						<input type="password" name="pass" placeholder="비밀번호" required="required"
						class="form-control" style="width: 130px;">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-info">수정완료</button>
						<button type="button" class="btn btn-info"
						onclick="location.href='list'">회원명단</button>
					</td>
				</tr>
		</table>
	</form>

</body>
</html>