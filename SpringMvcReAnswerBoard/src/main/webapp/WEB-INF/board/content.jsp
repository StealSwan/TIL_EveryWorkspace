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
	
	#answer{
		border-bottom: 1px solid #ddd;
		margin-bottom: 20px;
		padding-top: 10px;
		padding-bottom: 10px;
		padding-left: 20px; 
	}
	
</style>


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
			
			
			<!-- 댓글출력 -->
			<tr>
				<td>
					<div id="answer">
					
						<b>댓글 ${acount}</b> <br><br>
						<c:forEach var="a" items="${alist}">
							${a.nickname}: ${a.content} &nbsp;&nbsp;
							<span style="color: gray; font-size: 0.8em;">
								<fmt:formatDate value="${a.writeday}" pattern="yyyy-MM-dd HH:mm"/>
							</span> &nbsp;
							
							<span class="glyphicon glyphicon-pencil" style="cursor: pointer;"></span>&nbsp;
							<span class="adel glyphicon glyphicon-trash" style="cursor: pointer;" idx="${a.idx}"></span><br>
						</c:forEach>
						
					</div>
					
					<form action="ainsert" method="post" class="form-inline">
						<!-- hidden -->
						<input type="hidden" name="num" value="${dto.num}">
						<input type="hidden" name="currentPage" value="${currentPage}">
						<div>
							<b>닉네임: </b>
							<input type="text" name="nickname" class="form-control input-sm"
							style="width: 100px;" required="required">
							<b style="margin-left: 30px;">비밀번호: </b>
							<input type="password" name="pass" class="form-control input-sm"
							style="width: 100px;" required="required">			
							<br><br>
							<input type="text" name="content" class="form-control input-sm"
							style="width: 500px;" required="required"
							placeholder="댓글내용을 입력하세요">
							
							<button type="submit" class="btn btn-default btn-sm">확인</button>			
						</div>
					</form>
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


<script type="text/javascript">

	//댓글 삭제
	$("span.adel").click(function(){
		
		var idx=$(this).attr("idx");
		//alert(idx);
		
		//비번입력
		var pass=prompt("비밀번호를 입력해주세요");
		//alert(pass);	//취소시 null
		
		if(pass==null)
			return;	//취소시 함수종료
			
			
		$.ajax({
			type:"get",
			dataType:"json",
			url:"adelete",	//Controller의 매핑주소
			data:{"idx":idx, "pass":pass},	//여기서 Controller로 보내고 check값을 받음, 성공할 경우 아래로 내려감
			success: function(data){
				
				if(data.check==0){
					
					alert("비밀번호가 맞지않습니다");
				}else{
					
					alert("댓글 삭제성공");
					location.reload();
				}
			}
		});
	});

</script>

</body>
</html>