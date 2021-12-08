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

<c:set var="root" value="<%=request.getContextPath() %>"/>

<title>Insert title here</title>
</head>
<body>

	<table class="table table bordered" style="width: 600px; font-family: Nanum Pen Script; font-style: 17pt;">
		<tr>
			<td>
				<h3>
					<b>${dto.subject}</b>
					<span style="font-size: 14pt; color: gray;">
						<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd HH:mm"/>
					</span>
				</h3>
				
				<span>작성자: ${dto.name}(${dto.myid})</span>
				<c:if test="${dto.uploadfile!='no'}">
					<span>
						<a href="download?clip=${dto.uploadfile}">
							<span class="glyphicon glyphicon-download-alt"></span>
								<b>${dto.uploadfile}</b>
						</a>
					</span>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				<c:if test="${bupload==true}">
					<h3>업로드 이미지 파일입니다</h3>
					<img alt="" src="${root}/photo/${dto.uploadfile}" style="max-width: 400px;">
				</c:if>
				
				<br><br>
				
				<pre style="background-color: #fff; border: 0px;">${dto.content}</pre>
				
				<br>
				
				<b>조회 ${dto.readcount}</b>
			</td>
		</tr>
		
		
		<!-- 버튼 추가 -->
		<tr>
			<td align="right">
				<!-- 로그인 중일때만 글쓰기 -->
				<c:if test="${sessionScope.loginok!=null}">
					<button type="button" class="btn btn-default" style="width: 100px;" 
					onclick="location.href='form'">글쓰기</button>
				</c:if>
				
				<!-- 목록은 조건없음 -->
					<button type="button" class="btn btn-default" style="width: 100px;" 
					onclick="location.href='list?currentPage=${currentPage}'">목록</button>	
						
				<!-- 로그인중이면서 세션의 아이디와 같은 아이디로 쓴글에만 수정, 삭제버튼 보이게 -->
				<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.myid}">
					<button type="button" class="btn btn-default" style="width: 100px;"
					onclick="location.href='updateform?num=${dto.num}&currentPage=${currentPage}'">수정</button>

					<button type="button" class="btn btn-default" style="width: 100px;"
					onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage}'">삭제</button>
				</c:if>			
			</td>
		</tr>
	</table>

</body>
</html>