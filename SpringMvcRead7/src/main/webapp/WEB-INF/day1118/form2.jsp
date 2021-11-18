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
<form action="read2" class="form-inline" method="post">
	<table class="table table-bordered" style="width: 300px;">
	   <tr>
	     <th bgcolor="orange">상품명</th>
	       <td>
	         <input type="text" name="sang" class="form-control" width="120px;">
	       </td>
	   </tr>
	   <tr>
	     <th bgcolor="orange">색상</th>
	       <td>
	         <input type="color" name="color" value="#ffff00">
	       </td>
	   </tr>
	   
	   <tr>
	     <th bgcolor="orange">가격</th>
	       <td>
	         <input type="text" name="price" class="form-control" width="120px;">
	       </td>
	   </tr>
	   
	   <tr>
	     <th bgcolor="orange">이미지</th>
	       <td>
	         <select name="image" class="form-control">
	           <option value="acc02">악세서리1</option>
	           <option value="acc03">악세서리2</option>
	           <option value="bag03">가방1</option>
	           <option value="bag04">가방2</option>
	         </select>
	       </td>
	   </tr>
	   
	   <tr>
	     <td colspan="2" align="center">
	       <button type="submit" class="btn btn-success">데이타 전송</button>
	     </td>
	   </tr>
	</table>
</form>
</body>
</html>