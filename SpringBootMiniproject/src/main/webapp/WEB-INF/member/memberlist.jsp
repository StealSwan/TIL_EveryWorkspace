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

	<h3 class="alert alert-info" style="width: 900px;">총 ${count}명의 회원이 가입되어 있습니다</h3>

	<table class="table table-bordered" style="width: 900px;">
		<caption><b>전체 회원 목록</b></caption>
		<tr bgcolor="#ccf">
			<th width="50">번호</th>
			<th width="70">회원명</th>
			<th width="70">아이디</th>
			<th width="120">핸드폰</th>
			<th width="150">주소</th>
			<th width="150">이메일</th>
			<th width="100">가입일</th>
			<th width="100">수정/삭제</th>
		</tr>
		
		
		<c:forEach var="a" items="${list}" varStatus="i">
			 <tr>
			 	<td align="center">${i.count}</td>
			 	<td>${a.name}</td>
			 	<td>${a.id}</td>
			 	<td>${a.hp}</td>
			 	<td>${a.addr}</td>
			 	<td>${a.email}</td>
			 	<td>
			 		<fmt:formatDate value="${a.gaipday}" pattern="yyyy-MM-dd"/>
			 	</td>
			 	<td>
			 		<button type="button" class="btn btn-info btn-xs"
			 		onclick="location.href='updatepassform?num=${a.num}'">수정</button>

			 		<button type="button" class="btn btn-info btn-xs del" num="${a.num}">삭제</button>
			 	</td>
			 </tr>
		</c:forEach>
	</table>

	
	<!-- 삭제 modal -->
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-sm">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">삭제확인</h4>
	      </div>
	      
	      
	      <div class="modal-body form-inline">
	      	<!-- hidden -->
	      	<input type="hidden" id="delnum">
	      	
	        <b>삭제 비밀번호: </b>
	        <input type="password" id="delpass" class="form-control" style="width: 120px;">
	      </div>
	      
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default delbtn" data-dismiss="modal">삭제</button>
	      </div>
	    </div>
	
	  </div>
	</div>


<!-- 모달 나오게 함 -->
<script type="text/javascript">

	$("button.del").click(function(){
		
		//삭제 버튼에 num을 심어놓음
		var num = $(this).attr("num");
		
		//alert(num);
		
		/* Modal 안에 num 값 넣음 */
		$("#delnum").val(num);
		
		/* 모달 띄우기 */
		$("#myModal").modal();
		
		
		//삭제 이벤트
		$("button.delbtn").click(function(){
			
			//num, pass읽기
			var num = $("#delnum").val();
			var pass = $("#delpass").val();
			
			console.log(num+", "+pass);
			
			
			//삭제는 ajax로 해보기
			$.ajax({
				
				type:"get",
				dataType:"json",
				data:{"num":num,"pass":pass},
				url:"memberdelete",
				success:function(data){
					
					if(data.check==1){

						//비번이 맞는 경우
						//현재페이지 새로고침
						location.reload();
					} else{
						
						//비번이 틀린경우
						alert("비밀번호가 맞지 않습니다");
					}
				}
				
			});
		});
	});

</script>

</body>
</html>