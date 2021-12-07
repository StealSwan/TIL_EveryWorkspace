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

	<!-- 로그인 상태에서만 보이게 -->
	<c:if test="${sessionScope.loginok!=null}">
		<button type="button" class="btn btn-default" style="width: 100px; margin-left: 600px;" 
		onclick="location.href='form'">글쓰기</button>
	</c:if>

	<br><br>
	
	<table class="table" style="width: 750px;">
		<tr bgcolor="#ddd">
			<th width="50">번호</th>
			<th width="350">제목</th>
			<th width="100">작성자</th>
			<th width="70">조회</th>
			<th width="50">등록일</th>
		</tr>
		
		<c:if test="${totalCount==0}">
			<tr height="50">
				<td colspan="5" align="center">
					<h4><b>등록된 글이 없습니다</b></h4>
				</td>
			</tr>
		</c:if>
	</table>

</body>
</html>