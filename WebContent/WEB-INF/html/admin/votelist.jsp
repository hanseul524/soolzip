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
<title>votelist</title>
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
	var delchk = [];
	$('.chk:checked').each(function() {
		console.log($(this).val());
	});
});
</script>
<style>
.myButton {
    background-color:#acacac;
    border-radius:20px;
    border:1px solid #8a8a8a;
    display:inline-block;
    cursor:pointer;
    color:#ffffff;
    font-size:12px;
    padding:0 20px;
    text-decoration:none;
    width: 100px;
    height: 40px;
  }
  .myButton:hover {
    background-color:rgb(145, 140, 0);
  }
  .myButton:active {
    position:relative;
    top:1px;
  }
</style>
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
         <li>
          <i class="fas fa-poll"></i><a href="#">투표 관리</a>
        </li>
    </ul>
    </div>
  </div>
  <div class="menu-contents">
    <div id="contents-header">
    <form action="" method="get">
      <h2>투표 관리</h2>
      <button class="myButton" type="submit">start</button>
      <button class="myButton" type="submit">end</button>
      <hr style="border-top: 1px solid black; margin: 10px;">
    </div>
<!--     <div id="contents-search"> -->
<!--     <form action="/user/search" method="get"> -->
<!--       <input type="text" name="searchId" id="user-id" placeholder="아이디로 회원 조회"> -->
<!--       <input type="submit" id="search" value="검색"> -->
<!-- <!--       <a href="#"><img src="/img/icon_header_search.png" alt=""></a> --> -->
<!--     </form> -->
<!--     </div> -->
    <div id="contents-list">
    <table class="table table-hover">
      <tr>
        <th>레시피 번호</th>
        <th>제목</th>
        <th>좋아요</th>
        <th>조회수</th>
        <th>아이디</th>
        <th>작성일</th>
      </tr>
    <c:forEach items="" var="" varStatus="">
      <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
    </c:forEach>
    <hr> 
<!--     <div class="text-center"></div> -->
<!--       <ul class="pagination"> -->
<!--         <li><a href="#">1</a></li> -->
<!--         <li><a href="#">2</a></li> -->
<!--         <li><a href="#">3</a></li> -->
<!--         <li><a href="#">4</a></li> -->
<!--         <li><a href="#">5</a></li> -->
<!--       </ul> -->
    </form>
    </table>
    </div>
  </div>
</div>
</form>
<div id="footerMain"></div> 
</body>
</html>