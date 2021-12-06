<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=Gugi&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script type="text/javascript">

	$(function(){
		
		//이메일 선택 이벤트
		$("#selemail").change(function(){
			
			if($(this).val()=='-'){
				
				//지정된 이메일지우고 포커스
				$("#email2").val('');
				$("#email2").focus();
				
			} else{
				
				$("#email2").val($(this).val());
			}
		});
		
		
		//ID 입력후 포커스 벗어나면 체크
		$("#id").blur(function(){
			
			//입력값 앞뒤공백 제거
			var id=$(this).val().trim();
			
			//만약 아이디 입력 안했을 경우
			if(id.length==0){
				
				alert("아이디를 입력해주세요");
				
				return;
			}
			
			
			//Ajax
			$.ajax({
				
				type:"get",
				dataType:"json",
				data:{"id":id},
				url:"idcheck",
				success:function(data){
					
					if(data.check==1){
						
						//b 태그 안의 idmsg
						$("b.idmsg").html("<font color='red'>이미 등록된 아이디입니다</font>");
						$("#id").val("");
						$("#id").focus();	
					
					} else{
						
						$("b.idmsg").html("<font color='blue'>사용가능한 아이디입니다</font>");
					}
				}
				
			});
			
		});
		
	});
	
	
	function check(f){
		
		//비밀번호 체크
		if(f.pass.value!=f.pass2.value){
			
			alert("비밀번호가 서로 다릅니다");
			
			//초기 입력값 삭제
			f.pass.value="";
			f.pass2.value="";
			
			//action 호출되지 않도록
			return false;
		}
	}

</script>

<title>Insert title here</title>
</head>
<body>
<form action="update" method="post" class="form-inline" name="memberfrm"
onsubmit="return check(this)">
	
	
	<!-- hidden -->
	<input type="hidden" name="num" value="${dto.num}">	
	
<table class="table table-bordered" style="width: 500px;">
	<caption><b>회원정보 수정</b></caption>

	<tr>
		<th width="100" bgcolor="#aaa">이름</th>
		<td>
			<input type="text" name="name"  class="form-control"
				required="required" style="width: 120px;" value="${dto.name}">			
		</td>
	</tr>
	<tr>
		<th width="100" bgcolor="#aaa">핸드폰</th>
		<td>
			<input type="text" name="hp"  class="form-control"
				required="required" style="width: 200px;" value="${dto.hp}">			
		</td>
	</tr>
	<tr>
		<th width="100" bgcolor="#aaa">주소</th>
		<td>
			<input type="text" name="addr"  class="form-control"
				required="required" style="width: 400px;" value="${dto.addr}">			
		</td>
	</tr>
	<tr>
		<th width="100" bgcolor="#aaa">이메일</th>
		<td>
			<input type="text" name="email1"  class="form-control"
				required="required" style="width: 80px;" value="${dto.email1}">	
			<b>@</b>
			<input type="text" name="email2" id="email2" class="form-control"
				required="required" style="width: 150px;" value="${dto.email2}">	
			<select id="selemail" class="form-control">
				<option value="-">직접입력</option>
				<option value="naver.com">네이버</option>
				<option value="nate.com">네이트</option>
				<option value="gmail.com">구글</option>
				<option value="hanmail.net">다음</option>				
			</select>	
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<button type="submit" class="btn btn-default"
			style="width: 100px;">저장하기</button>
			
			<button type="reset" class="btn btn-default"
			style="width: 100px;">다시하기</button>
			
		</td>		
	</tr>
</table>
</form>
</body>
</html>

