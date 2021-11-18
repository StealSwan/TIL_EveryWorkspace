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


<title>Insert title here</title>
</head>
<body>
<div style="margin-left: 200px; margin-top: 50px;">
  <button type="button" class="btn btn-info btn-lg"
  style="width: 200px;" onclick="location.href='shop/form1'">각각읽기</button><br>
  <button type="button" class="btn btn-info btn-lg"
  style="width: 200px;" onclick="location.href='shop/form2'">DTO읽기</button><br>
  <button type="button" class="btn btn-info btn-lg"
  style="width: 200px;" onclick="location.href='shop/form3'">Map읽기</button><br>
  
  
  <button type="button" class="btn btn-info btn-lg"
  style="width: 200px;" onclick="location.href='board/uploadform1'">1개이미지업로드</button><br>
  <button type="button" class="btn btn-info btn-lg"
  style="width: 200px;" onclick="location.href='board/uploadform2'">여러개이미지업로드</button><br>
</div>
</body>
</html>