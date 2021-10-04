<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qnalist</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/main.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
$(document).ready(function () {
	$("#headerMain").load("/html/comm/header.jsp");
	$("#footerMain").load("/html/comm/footer.html");
	
	$('div > ul > li').click(function() {
        if ( $(this).hasClass('active') ) {
          $(this).find(' > ul').stop().slideUp(300);
          $(this).removeClass('active');
        }
        else {
          $(this).find(' > ul').stop().slideDown(300);
          $(this).addClass('active');
        }
	});
	$("a[name='trigger']").click(function(){
		$(this).closest("tr").next().toggle();
	});
	$("a[name='trigger']").click(function(){
		  $(this).closest("tr").next().toggle();
	});
});
</script>
</head>
<body>
<div id="headerMain"></div>
  <div id="contnet" class="contents">
    <div id="menu-bar">
      <br>
      <p style="text-align: center;">관리자님, <br> 안녕하세요.</p>
      <br>
      <ul>
        <li>
          <i class="fas fa-list"></i><a href="/admin/qnalist">문의사항 관리</a>
        </li>
        <li>
          <i class="far fa-user-circle"></i><a href="#">회원 관리</a>
            <ul>
                <li><a href="/user/list">회원 탈퇴</a></li>
                <li><a href="/user/change">관리자 권한 부여</a></li>
            </ul>
        </li>
        <li>
          <i class="fas fa-user-circle"></i><a href="/admin/list">관리자 관리</a>
        </li>
    </ul>
    </div>
  </div>
  <div class="menu-contents">
    <div id="contents-header">
      <h2>문의사항 관리</h2>
    </div>
    <div id="contents-list">
<!--     <form action="/admin/qnalist" method="get"> -->
    <table class="table table-hover">
      <tr>
        <th>접수번호</th>
        <th>제목</th>
        <th>작성자 ID</th>
        <th>등록일</th>
        <th>상태</th>
      </tr>
      <c:forEach items="${requestScope.qList}" var="qna" varStatus="index">
      <tr>
        <td>${qna.qnaNo}</td>
        <td><a href="#" name="trigger">${qna.qnaTitle}</a></td>
        <td>${qna.userId}</td>
        <td>${qna.qnaWriteDate}</td>
        <td>${qna.qnaStatus}</td>
      </tr>
      <tr class="qna-content">
      	<td colspan="5"><a name="first" id="first"></a>${qna.qnaContent}</td>
      </tr>
      </c:forEach>
    </table>
    <tr>
    	<td colspan="10" align="center">
    	<%= pageNavi %>
    	</td>
    </tr>
<!--     </form> -->
    <hr>
<!--     <div class="text-center"></div> -->
<!--       <ul class="pagination"> -->
<!--         <li><a href="#">1</a></li> -->
<!--         <li><a href="#">2</a></li> -->
<!--         <li><a href="#">3</a></li> -->
<!--         <li><a href="#">4</a></li> -->
<!--         <li><a href="#">5</a></li> -->
<!--       </ul> -->
<!--     </div> -->
  </div>
</div>
<div id="footerMain"></div> 
</body>
</html>