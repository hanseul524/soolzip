<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userlist</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/userlist.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
          <i class="fas fa-list"></i><a href="#">문의사항</a>
            <ul>
                <li><a href="/user/list">문의사항 관리</a></li>
                <li><a href="#">답변</a></li>
            </ul>
        </li>
        <li>
          <i class="far fa-user-circle"></i><a href="/user/list">회원 관리</a>
        </li>
        <li>
          <i class="fas fa-user-circle"></i><a href="/admin/list">관리자 관리</a>
        </li>
    </ul>
    </div>
  </div>
  <div class="menu-contents">
    <div id="contents-header">
      <h2>회원 계정 관리</h2>
      <hr style="border-top: 1px solid black; margin: 10px;">
    </div>
    <div id="contents-search">
    <form action="" method="get">
      <input type="text" name="user-id" id="user-id" placeholder="전체 회원 조회">
      <a href="#"><img src="/img/icon_header_search.png" alt=""></a>
    </form>
    </div>
    <div id="contents-list">
    <table class="table table-hover">
      <tr>
        <th>회원번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>이메일</th>
        <th>휴대폰</th>
        <th>권한부여</th>
        <th>삭제</th>
      </tr>
    <c:forEach items="${requestScope.uList}" var="user" varStatus="index">
      <tr>
        <td>${user.userNo}</td>
        <td>${user.userId}</td>
        <td>${user.userName}</td>
        <td>${user.userEmail}</td>
        <td>${user.userPhone}</td>
        <td>${user.userAdmin}</td>
        <td><input type="checkbox" name="checkList"></td>
      </tr>
    </c:forEach>
    <hr>
	<tr>
		<td colspan="10" align="center">
		<%= pageNavi %>
		</td>
	</tr>
    <div class="text-center"></div>
<!--       <ul class="pagination"> -->
<!--         <li><a href="#">1</a></li> -->
<!--         <li><a href="#">2</a></li> -->
<!--         <li><a href="#">3</a></li> -->
<!--         <li><a href="#">4</a></li> -->
<!--         <li><a href="#">5</a></li> -->
<!--       </ul> -->
      <div class="removeCk">
    </table>
        <input type="submit" value="선택삭제">
      </div>
    </div>
  </div>
</div>
<div id="footerMain"></div> 
</body>
</html>