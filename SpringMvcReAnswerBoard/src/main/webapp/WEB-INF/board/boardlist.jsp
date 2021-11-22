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
	
	<h1>전체갯수: ${totalCount}</h1>
	<div style="margin-left: 50px;">
		<table class="table table-bordered" style="width: 800px;">
			<caption><b>Spring 게시판</b>
				<span style="float: right;">
					<button type="button" class="btn btn-default" onclick="location.href='writeform'">글쓰기</button>
				</span>
			</caption>
				
				<tr bgcolor="#ddd" align="center">
					<th width="50">번호</th>
					<th width="300">제목</th>
					<th width="80">작성자</th>
					<th width="120">작성일</th>
					<th width="60">조회</th>
				</tr>
				
				<c:if test="${totalCount==0}">
					<tr>
						<td colspan="5" align="center">
							<b>등록된 게시글이 없습니다.</b>
						</td>
					</tr>
				</c:if>
				
				
				<c:if test="${totalCount>0}">
					<c:forEach var="dto" items="${list}">
						<tr>
							<td align="center">${no}</td>
								<c:set var="no" value="${no-1}"/>
							
							<!-- 제목출력 -->	
							<td>
								<!-- relevel만큼 공백을 줘야함 -->
								<c:forEach var="sp" begin="1" end="${dto.revel}">
									&nbsp;&nbsp;
								</c:forEach>
								
								
								<!-- 답글인 경우 re 이미지 출력 -->
								<c:if test="${dto.relevel>0}">
									<img alt="" src="../photo/re.png">
								</c:if>
								
								<!-- 제목...통해서 내용보기 -->
								<a href="content?num=${dto.num}&currentPage=${currentPage}&key=list">${dto.subject}</a>
								
								<!-- 사진이 있을 경우, 아이콘 표시 -->
								<c:if test="${dto.photo!='no'}">
									<span style="font-size: 0.8em; color: #ccc;"
									class="glyphicon glyphicon-picture"></span>
								</c:if>
							</td>
							
							
							<td align="center">${dto.writer}</td>
							<td align="center"><fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd"/></td>
							<td align="center">${dto.readcount}</td>
						</tr>
					</c:forEach>
				</c:if>
		</table>
		
		
		<!-- 페이징 처리 -->
		<c:if test="${totalCount>0}">
			<div style="width: 800px; text-align: center;">
				<ul class="pagination">
				
				<!-- 이전 -->
				<c:if test="${startPage>1}">
					<li><a href="list?currentPage=${startPage-1}">이전</a></li>
				</c:if>
				
					<c:forEach var="pp" begin="${startPage}" end="${endPage}">
						<c:if test="${currentPage==pp}">
							<li class="active"><a href="list?currentPage=${pp}">${pp}</a></li>
						</c:if>
						
						<c:if test="${currentPage!=pp}">
							<li><a href="list?currentPage=${pp}">${pp}</a></li>
						</c:if>
					</c:forEach> 
					
				<!-- 다음 -->
				<c:if test="${endPage>totalPage}">
					<li><a href="list?currentPage=${endPage+1}">다음</a></li>
				</c:if>
				</ul>
			</div>
		</c:if>
		
	</div>
	
</body>
</html>