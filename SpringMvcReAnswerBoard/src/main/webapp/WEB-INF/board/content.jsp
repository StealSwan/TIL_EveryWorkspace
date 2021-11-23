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

	<div style="margin: 50px 50px;">
		<table class="table table-bordered" style="width: 600px;">
			<caption>내용보기</caption>
			<tr>
				<td>
					<h3><b>${dto.subject}</b></h3>
					<span style="float: right; margin-right: 20px;">
						조회 ${dto.readcount} &nbsp;&nbsp;&nbsp;
							<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd HH:mm"/>
					</span>
					
					<br>
					
					<h5><b>작성자: ${dto.writer}</b></h5>
				</td>
			</tr>
			
			<tr>
				<td>
					<pre>${dto.content}</pre>
					<br><br>
					
					<c:if test="${dto.photo!='no'}">
						<c:forTokens var="pp" items="${dto.photo}" delims=",">
							<a href="../photo/${pp}">
								<img alt="" src="../photo/${pp}" style="max-width: 150px;">
							</a>
						</c:forTokens>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td align="right">
					<button class="btn btn-default btn-sm"
					onclick="location.href='writeform'">글쓰기</button>
				
					<button class="btn btn-default btn-sm"
					onclick="location.href='writeform?num=${dto.num}&regroup=${dto.regroup}&restep=${dto.restep}&relevel=${dto.relevel}&currentPage=${currentPage}'">답글</button>
					
					<button class="btn btn-default btn-sm"
					onclick="location.href='updatepassform?num=${dto.num}&currentPage=${currentPage}'">수정</button>

					<button class="btn btn-default btn-sm"
					onclick="location.href='deletepassform?num=${dto.num}&currentPage=${currentPage}'">삭제</button>

					<button class="btn btn-default btn-sm"
					onclick="location.href='list?currentPage=${currentPage}'">목록</button>
				</td>
			</tr>
			
		</table>
	</div>

</body>
</html>