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

<style type="text/css">

	.table>tbody>tr>td {
		vertical-align: middle;
	}

</style>


<title>Insert title here</title>
</head>
<body>

	<button type="button" class="btn btn-info"
	style="width: 100px;" onclick="location.href='bookform'">상품추가</button>

	<c:if test="${totalCount==0}">
		<div class="alert alert-info"><b>저장된 상품 정보가 없습니다</b></div>
	</c:if>

	<c:if test="${totalCount>0}">
		<div class="alert alert-info"><b>총 ${totalCount}개의 책정보가 있습니다</b></div>
		
		<c:forEach var="a" items="${list}">
			<table class="table table-bordered" style="width: 550px;">
				<tr>
					<td rowspan="4">						
						<!-- photo가 있다면 다중 출력 -->
						<c:if test="${a.bookphoto!='no'}">
							<c:forTokens var="image" items="${a.bookphoto}" delims=",">
								<img alt="" src="bookimage/${image}" class="img-thumbnail"
								style="max-width: 100px; max-height: 80px; float: left; margin-left: 10px;">
									&nbsp;&nbsp;
							</c:forTokens>
						</c:if>
					</td>
				</tr>
				
				
				<tr align="center">		
					<td bgcolor="pink" width="100px;">
						<b>책이름</b>
					</td>
					
					<td width="200px;">
						${a.bookname}
					</td>	
				</tr>
				
				
				<tr align="center">
					<td bgcolor="pink">
						<b>책가격</b>
					</td>
					
					<td>
						<fmt:formatNumber value="${a.bookprice}" type="currency"/>
					</td>			
				</tr>
				
				
				<tr align="center">
					<td bgcolor="pink">
						<b>입고일</b>
					</td>
					
					<td>
						<fmt:formatDate value="${a.ipgoday}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>
				
				<tr align="center">
					<td colspan="3">		
						<button type="button" class="btn btn-info btn-xs" style="width: 60px;"
						onclick="location.href='bookupdateform?num=${a.num}'">상품수정</button>
	
						<button type="button" class="btn btn-danger btn-xs" style="width: 60px;"
						onclick="location.href='bookdelete?num=${a.num}'">상품삭제</button>
					</td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	

</body>
</html>