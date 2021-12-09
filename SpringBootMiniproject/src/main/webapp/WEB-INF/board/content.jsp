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

	.day{
		color: gray;
		margin-left: 10px;
		margin-right: 10px;
		font-size: 0.8em;
	}
	
	.amod, .adel{
		cursor: pointer;
		font-size: 0.6em;
		color: gray;
	}

</style>

<script type="text/javascript">

	$(function(){
		
		//자바스크립트에서 var 안붙이면 전역변수
		num = $("#num").val();
		//alert(num);
		
		//로그인 세션 - 전연변수
		loginok="${sessionScope.loginok}";
		
		//내 아이디 - 전역변수
		myid="${sessionScope.myid}";
		
		//검산용 - 로그인했을 때 안했을 때 확인
		//alert(loginok+","+myid);
		
		
		//글들어가면 바로 댓글목록 보이게
		list();
		
		
		$("#btnansweradd").click(function(){
			
			var content=$("#content").val();
			
			//댓글 안쓰고 추가 버튼 누를경우
			if(content.trim().length==0){
				
				alert("댓글내용을 입력해주세요");
				
				//입력안하면 종료
				return;
			}
			
			
			//입력했을시 Ajax로
			$.ajax({
			
				type:"post",
				//반환이 되어야 json 타입으로 입력가능 : 아예 빼도 되긴 함 - ainsert는 void
				//리스트 출력 등은 반환값이 있기에 가능
				dataType:"text",	
				url:"ainsert",
				data:{"num":num, "content":content},
				success:function(data){
					
					//alert("성공");
					
					//댓글목록 가져오기
					list();
				}
			});
		});
		
		
		////////////////////////
		//댓글 삭제
		$(document).on("click", "span.adel", function(){
			
			var idx=$(this).attr("idx");
			//alert(idx);
			
			//확인창
			var a = confirm("해당 댓글을 삭제할까요?");
			
			//확인할 경우, 삭제
			if(a==true){
			
				$.ajax({
					
					type:"get",
					url:"adelete",
					data:{"idx":idx},
					success:function(data){
						
						list();
					}
				});
			}
			
		});
		
		
		////////////////////////
		//수정 다이얼로그 띄우기
		//각 게시물에 해당되는 num이 같을 수 있는 것 - idx는 댓글마다 다 다르다 중복 X - Primary key 
		$(document).on("click","span.amod",function(){
			
			//댓글 Primary Key
			//전역변수로 변경
			idx=$(this).attr("idx");
			
			$.ajax({
				
				type:"get",
				dataType:"json",
				url:"adata",
				data:{"idx":idx},
				success:function(data){
					
					$("#ucontent").val(data.content);
				}
			});
			
			//모달 띄우기
			$("#myModal").modal();
		});
		
		
		/////////////////////////////
		//수정
		$(document).on("click","#btnaupdate",function(){
			
			var content=$("#ucontent").val();
			
			$.ajax({
				
				type:"post",
				//데이터타입 반환값 X라서 일단 text로 그냥해줌
				dataType:"text",
				url:"aupdate",
				data:{"idx":idx,"content":content},
				success:function(data){
					
					list();
				}
			});
		});
	});
	
	
	//리스트 - 창 열자마자 리스트 보이게
	function list(){
		
		$.ajax({
			
			type:"get",
			//반환 타입이 json
			dataType:"json",
			url:"alist",
			data:{"num":num},
			success:function(data){
				
				//총댓글 개수 출력
				$("span.acount").text(data.length);
				
				var s="";
				
				$.each(data,function(i,dto){
					
					s+="<b>"+dto.name+"</b>"+dto.content+"&nbsp;&nbsp";
					s+="<span class='day'>"+dto.writeday+"</span>";
					
					//내가 쓴 글에만 수정, 삭제 버튼 보이게
					//javascript에는 equal이 없어서 ==으로
					if(loginok=='yes' && myid==dto.myid){
						
						s+="<span class='glyphicon glyphicon-pencil amod' idx='"+dto.idx+"'></span>";
						s+="&nbsp";
						s+="<span class='glyphicon glyphicon-trash adel' idx='"+dto.idx+"'></span>";
					}
					
					s+="<br><br>";
				});
				
				
				$("div.alist").html(s);
			}
		});
	}

</script>

<title>Insert title here</title>
<c:set var="root" value="<%=request.getContextPath() %>"/>
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
				
				<b>조회 ${dto.readcount}</b> &nbsp;&nbsp;&nbsp;
				
				<b>댓글 <span class="acount"></span></b>
			</td>
		</tr>
		
		
		<!-- 댓글창 -->
		<tr>
			<td>
				<div class="alist"></div>

				<!-- hidden은 조건문 안에서 -->
				<input type="hidden" id="num" value="${dto.num}">
				
				<c:if test="${sessionScope.loginok!=null}">
					<div class="aform">
						<div class="form-inline">
							<input type="text" class="form-control" style="width: 500px;"
							placeholder="댓글을 입력하세요" id="content">
							
							<button type="button" class="btn btn-info"
							style="width:60px;" id="btnansweradd">등록</button>
						</div>
					</div>
				</c:if>
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



	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
	  <div class="modal-dialog">
	   
		<!-- Modal Content -->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title">댓글 수정</h4>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	         <input type="text" id="ucontent" class="form-control">
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal"
	        id="btnaupdate">수정</button>
	      </div>
	
	    </div>
	  </div>
	</div>

</body>
</html>