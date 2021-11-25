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

<script type="text/javascript">

	//list2 선택되도록
	$(function(){
		$("span.list2").trigger("click");
	})

</script>


<title>Insert title here</title>
</head>
<body>
	
	<div style="width: 800px; text-align: right; margin: 50px 50px; font-size: 2em;">
		<span class="glyphicon glyphicon-th-list list1"
			style="cursor: pointer;"></span>
		<span class="glyphicon glyphicon-th-large list2"
			style="cursor: pointer; margin-left: 10px;"></span>
	</div>


	<div class="restlist" style="margin-top: 30px; margin-left: 50px;">
	
	</div>
	

<script type="text/javascript">
	
	//클릭시 색변환
	$("span.list1").click(function(){
		$("span.list1").css("color","red");
		$("span.list2").css("color","black");
	
		$.ajax({
			type:"get",
			dataType:"json",
			url:"list1",
			success:function(data){
				
				var s="";
				s+="<table class='table' style='width:800px'>";
				
				$.each(data, function(i,elt){
					s+="<tr height='110'><td>";
					s+="<h3><b>"+elt.subject+"</b></h3>";
					s+=elt.content+"<br>";
					
					if(elt.photo!='no'){
						s+="<span style='float:right'>";
						s+="<img width='100' height='100' src='../photo/"+elt.photo+"'><br>";
						s+="</span>";
					}
					
					s+="작성자:"+elt.writer;
					s+="</td></tr>";
				});
				
				s+="</table>";
				
				$(".restlist").html(s);
			}
		});
	});
	
	
	//2번째 클릭시
	$("span.list2").click(function(){
		$("span.list2").css("color","red");
		$("span.list1").css("color","black");
	
		$.ajax({
			type:"get",
			dataType:"json",
			url:"list1",
			success:function(data){
				
				var s="";
				s+="<table class='table' style='width:800px'>";
				s+="<tr>";
				
				var n=0;
				
				$.each(data, function(i,elt){
				
					if(elt.photo!='no'){
						
						n++;
						s+="<td width='200'>";
						s+="<img width='200' height='200' src='../photo/"+elt.photo+"'><br>";
						s+="작성자:"+elt.writer;
						s+="</td>";
						
						if(n%4==0){
							s+="</tr><tr>";
						}
					}
					
				});
				
				s+="</table>";
				
				$(".restlist").html(s);
			}
		});
	});
</script>		
</body>
</html>