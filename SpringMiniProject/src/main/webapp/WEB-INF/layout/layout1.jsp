<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=Gugi&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
div.layout{
border: 0px solid gray;
position: absolute;
}


div.title{
  width: 100%;
  height: 80px;
  line-height: 80px;
  font-size: 30pt;
  font-family: 'Gaegu';
  text-align: center;
}

div.menu{
	width: 100%;
	height: 80px;
	line-height: 80px;
	font-size: 25pt;
	font-family: 'Gaegu';
	text-align: center;
	top: 80px;
}

div.info{
	width: 170px;
	height: 200px;
	line-height: 10px;
	font-size: 15pt;
	font-family: 'Gaegu';
	left: 30px;
	top: 300px;
	padding: 20px 10px;
	border: 5px groove lightgray;
	border-radius: 30px;
}

div.main{
	width: 1200px;
	height: 700px;
	font-size: 14pt;
	font-family: 'Gaegu';
	left: 300px;
	top: 200px;
}

a,a:hover {
	color: black;
	text-decoration: none;
}

</style>




<title>Insert title here</title>
</head>
<body>

	<div class="layout title">
		<tiles:insertAttribute name="title"/>
	</div>

	<div class="layout menu">
		<tiles:insertAttribute name="menu"/>
	</div>

	<div class="layout info">
		<tiles:insertAttribute name="info"/>
	</div>

	<div class="layout main">
		<tiles:insertAttribute name="main"/>
	</div>

</body>
</html>