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
<c:if test="${sessionScope.loginok!=null }">

<form action="update" method="post" enctype="multipart/form-data">

	<input type="hidden" name="num" value="${dto.num }">
   <input type="hidden" name="currentPage" value="${currentPage }"> 

   <h4>이미지 등록안할시 이전 이미지 들어가요~~
    <input type="file" name="photos" id="photos" style="display: none;" multiple="multiple">&nbsp;&nbsp;
    <span class="glyphicon glyphicon-camera photo"  style="font-size: 20pt;"></span></h4>
    
    <table class="table table-bordered" style="width: 700px;">
      <tr>
        <th style="width: 120px;">닉네임</th>
          <td>
            <input type="text" class="form-control input-sm" style="width: 200px;" name="nickname"
            value="${dto.nickname }">
          </td>
      </tr>
      <tr style="height: 100px;">
        
          <td colspan="2">
            <textarea style="width: 550px; height: 90px; float: left;" name="content" >${dto.content }</textarea>
            <button type="submit" class="btn btn-danger" style="width: 80px; height: 80px; margin-left: 10px;">수정</button>
          </td>
      </tr>
    </table>
</form>

</c:if>

</body>
</html>

</html>