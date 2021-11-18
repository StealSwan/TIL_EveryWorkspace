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
<form action="read1" class="form-inline" method="post">
	<table class="table table-bordered" style="width: 300px;">
	   <tr>
	     <th bgcolor="orange">이름</th>
	       <td>
	         <input type="text" name="name" class="form-control" width="120px;">
	       </td>
	   </tr>
	   <tr>
	     <th bgcolor="orange">성별</th>
	       <td>
	         <input type="radio" name="gender" value="남자" checked="checked">남자
	         &nbsp;&nbsp;
	         <input type="radio" name="gender" value="여자">여자
	       </td>
	   </tr>
	   
	   <tr>
	     <th bgcolor="orange">지역</th>
	       <td>
	         <select name="addr" class="form-control">
	           <option value="서울">서울</option>
	           <option value="경기">경기</option>
	           <option value="대구">대구</option>
	           <option value="세종">세종</option>
	           <option value="부산">부산</option>
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