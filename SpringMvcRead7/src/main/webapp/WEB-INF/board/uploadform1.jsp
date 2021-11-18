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
  <form action="upload1" class="form-inline" method="post" enctype="multipart/form-data">
    <table class="table table-bordered" style="width: 400px;">
      <caption><b>스프링 업로드(파일1개)</b></caption>
         <tr>
           <th bgcolor="pink">제목</th>
             <td>
               <input type="text" name="title" class="form-control" style="width: 150px;">
             </td>
         </tr>
         
         <tr>
           <th bgcolor="pink">업로드</th>
             <td>
               <input type="file" name="photo" class="form-control">
             </td>
         </tr>
         
         <tr>
           <td colspan="2" align="center">
             <button type="submit" class="btn btn-success">업로드 #1</button>
           </td>
         </tr>
    </table>
  </form>
</body>
</html>