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

<c:set var="root" value="<%=request.getContextPath()%>"/>
</head>
<body>

	<table class="table" style="600px;">
		<caption><b>내용보기</b></caption>
		<tr>
			<td>
				<b style="font-size:1.3em">${dto.subject}</b>
					<span style="color: gray; float: right">
						<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd HH:mm"/>
					</span>
			</td>
		</tr>
		
		<tr>
			<td>
				<b>${dto.writer}(${dto.id})</b>
					<span style="float: right;">조회 ${dto.readcount}</span>
					
					<div>
						<c:if test="${dto.photo!='no'}">
							<c:forTokens var="f" items="${dto.photo}" delims=",">
								<div>
									<a href="download?clip=${f}"><span class="glyphicon glyphicon-download-alt"></span>
										<span>${f}</span>
									</a>
								</div>
							</c:forTokens>
						</c:if>
					</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<pre>${dto.content}</pre>
				<br>
				<c:if test="${dto.photo!='no'}">
					<c:forTokens var="im" items="${dto.photo}" delims=",">
						<img alt="" src="${root}/photo/${im}" class="img-thumbnail" style="max-width: 200px;">
							&nbsp;&nbsp;
					</c:forTokens>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				<c:if test="${sessionScope.myid==dto.id}">
					<button type="button" class="btn btn-success" style="width: 80px;"
					onclick="location.href='updateform?num=${dto.num}&currentPage=${currentPage}'">수정</button>
					
					<button type="button" class="btn btn-success" style="width: 80px;"
					onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage}'">삭제</button>
					
				</c:if>
				
					<button type="button" class="btn btn-success" style="width: 80px;"
					onclick="location.href='list?currentPage=${currentPage}'">목록</button>
			</td>
		</tr>
	</table>

</body>
</html>