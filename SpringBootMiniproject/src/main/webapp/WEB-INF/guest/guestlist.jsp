<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

<script type="text/javascript">
$(function(){
	
	$("span.photo").click(function(){
		
		$("#photos").trigger("click");
	});
});

</script>

<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.loginok!=null }">

<form action="insert" method="post" enctype="multipart/form-data">
   <h4>이미지 등록하세요
    <input type="file" name="photos" id="photos" style="display: none;" multiple="multiple">&nbsp;&nbsp;
    <span class="glyphicon glyphicon-camera photo"  style="font-size: 20pt;"></span></h4>
    
    <table class="table table-bordered" style="width: 700px;">
      <tr>
        <th style="width: 120px;">닉네임</th>
          <td>
            <input type="text" class="form-control input-sm" style="width: 200px;" name="nickname">
          </td>
      </tr>
      <tr style="height: 100px;">
        
          <td colspan="2">
            <textarea style="width: 550px; height: 90px; float: left;" name="content" ></textarea>
            <button type="submit" class="btn btn-danger" style="width: 80px; height: 80px; margin-left: 10px;">저장</button>
          </td>
      </tr>
    </table>
</form>

</c:if>


<!-- 리스트 출력 -->

<hr>
<div class="alert alert-success" style="width: 700px;">
	<b>총 ${totalCount} 개의 데이타가 있습니다</b>
</div>
 
 <c:forEach var="a" items="${list}">
   <table id="guestlist" class="table table-bordered" style="width: 700px;" >
     <tr>
       <td>
         <c:if test="${a.photo!='no'}">
          <c:forTokens var="pp" items="${a.photo}" delims=",">
             <a href="${root }/photo/${pp}">
             <img alt="" src="${root }/photo/${pp}" style="max-width: 100px;"></a>
          </c:forTokens>
        </c:if>
         <pre><h6>  ${a.content}</h6></pre>
         <hr>
      
         <h6 style="color: gray;">${a.nickname} <span style="float: right; color: gray;"> 
         작성일: <fmt:formatDate value="${a.writeday}" pattern="yyyy-MM-dd HH:mm"/></h6></span>
        <br>
         <button type="button" class="btn btn-info btn-xs" style="width: 60px;"
         onclick="location.href='updateform?num=${a.num}&currentPage=${currentPage}'">수정</button>
         <button type="button" class="btn btn-info btn-xs" style="width: 60px;"
         onclick="location.href='delete?num=${a.num}&currentPage=${currentPage}'">삭제</button>
       </td>
     </tr>
   </table>
</c:forEach>
 


<!-- 페이징 -->
	<c:if test="${totalCount>0}">
		<div style="width: 800px;text-align: center;">
			<ul class="pagination">	
			<!-- 이전 -->
			<c:if test="${startPage>1}">
				<li><a href="list?currentPage=${startPage-1}">이전</a></li>
			</c:if>
			
			<c:forEach var="pp" begin="${startPage}" end="${endPage}">
				<c:if test="${currentPage==pp}">
					<li class="active"><a href="list?currentPage=${pp}">${pp}</a></li>
				</c:if>
				<c:if test="${currentPage!=pp}">
					<li><a href="list?currentPage=${pp}">${pp}</a></li>
				</c:if>
			</c:forEach>
			
			<!-- 다음 -->
			<c:if test="${endPage<totalPage}">
				<li><a href="list?currentPage=${endPage+1}">다음</a></li>
			</c:if>
			</ul>
		</div>
	</c:if>

</body>
</html>
