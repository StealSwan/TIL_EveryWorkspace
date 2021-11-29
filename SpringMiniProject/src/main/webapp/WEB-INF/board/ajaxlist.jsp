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

	img.photo{
		width: 200px;
		height: 200px;
		margin-left: 10px;
		border: 1px solid gray;
		border-radius: 20px;
		box-shadow: 5px 5px 5px 5px gray;
	}

</style>


<c:set var="root" value="<%=request.getContextPath() %>"/>
	<script type="text/javascript">
	$(function(){
		list1();
		$("#list1").click(function(){		
			list1();
		});
		
		$("#list2").click(function(){
			$("#list2").css("color","red");
			$("#list1").css("color","black");
			$.ajax({
				type:"get",
				url:"${root}/ajax/list1",
				dataType:"json",
				success:function(data){
					var s="<b>"+data.length+" 개의 자료가 있습니다<b><br>";
					$.each(data, function(i, a) {
						s+="<div'>";
						s+="<span>제목:"+a.subject+"</span><br>";
						s+="<span>작성자:"+a.writer+"</span><br>";
						s+="<span>작성일:"+a.writeday+"</span><br>";
						s+="<span><pre>"+a.content+"</pre></span>";					
						s+="</div>";
					});
					
					$("#showboard").html(s);
					
				}
			});
		});
	});


	function list1()
	{
		$("#list1").css("color","red");
		$("#list2").css("color","black");
		
		$.ajax({
			type:"get",
			url:"${root}/ajax/list2",
			dataType:"json",
			success:function(data){
				var s="<b>"+data.length+" 개의 자료가 있습니다<b><br><br>";			
				s+="<table class='phototb'>";
				s+="<tr>";
				$.each(data, function(i,a) {
					var photo=a.photo.split(",");
					console.dir(photo);
					s+="<td align='center'>";
					s+="<img class='photo' src='${root}/photo/"+photo[0]+"'><br>";
					s+="제목:"+a.subject+"(사진 총"+photo.length+"개)<br>";
					s+="작성자:"+a.writer+"<br>";
					s+="<span>작성일:"+a.writeday+"</span><br><br>"
					s+="</td>";
					
					if((i+1)%3==0)
						s+="</tr><tr>";
				});
				s+="</table>"
				$("#showboard").html(s);			
			}
		});
	}
	</script>


<title>Insert title here</title>
</head>
<body>

	<div class="alert alert-danger" style="font-size: 1.3em; width: 800px; ">
	  	Ajax로 Board목록 출력하기
	  	<span class="glyphicon glyphicon-th-large" style="margin-left: 200px; cursor: pointer;" id="list1"></span>
	  	<span class="glyphicon glyphicon-th-list" style="margin-left: 10px; cursor: pointer;" id="list2"></span>
	</div>

	<div id="showboard"></div>


</body>
</html>