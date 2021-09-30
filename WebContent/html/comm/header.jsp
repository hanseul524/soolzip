<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet" href="/css/comm.css">
<style>
ul, ol, li {
	list-style: none;
	margin: 0;
	padding: 0;
}
ul.myMenu>li {
	display: inline-block;
	text-align: center;
	position: relative;
}
ul.myMenu>li a:hover {
	color: #918c00
}
ul.myMenu>li ul.submenu {
	display: none;
	position: absolute;
	top: 34px;
	left: 0;
}
ul.myMenu>li:hover ul.submenu {
	display: block;
}
ul.myMenu>li ul.submenu>li {
	display: inline-block;
	background: #eee;
	width: 130px;
	border: 1px solid #eee;
	text-align: left;
}
ul.myMenu>li ul.submenu>li>a {
	font-size: small;
}
ul.myMenu>li ul.submenu>li:hover {
	background: #fff;
}
</style>
</head>
<body>
<div id="wrap" style="position: relative;">
	<!-- 사용자 nav -->
	<div class="user-area">
		<ul class="user-area-menu">
			<li><a href="#"> <img style="width: 50px; margin: 5px;"
					src="/img/suggestion.png" alt="">
			</a></li>
			<li>
				<a href="/recipe/register"> 
					<img style="width: 50px; margin: 5px;" src="/img/enrollRecipe.png" alt="">
				</a>
			</li>
			<c:if test ="${sessionScope.userId eq null}">
			<li><a href="index.jsp"> <img style="width: 50px; margin: 5px;"
					src="/img/myPagelogo.png" alt=""></a>
			</li>		
			</c:if>
			
			<c:if test="${sessionScope.userId ne null and userId ne ''}">
    			<li class="loginok">
        			<a href="index.jsp"><img style="width: 50px; margin: 5px;"
        			src="img/myPageLogo.png" alt=""></a>
      				<ul class="mypage">
        				<li><a href="#">마이페이지</a></li>
        				<li><a href="/user/logout">로그아웃</a></li>
        				<li><a href="#">투표하기</a></li>
      				</ul>
  				</li>
			</c:if>
		</ul>
	</div>
	<!-- 메인 로고 -->
	<div class="header">
		<a href="#"> <img style="width: 370px; height: 80px;"
			src="/img/mainLogo.png" alt="">
		</a>
		
	</div>
	
	<!-- 메인 nav -->
	<div class="nav" id="main-nav">
		<ul class="myMenu">
			<li class="menu1"><a href="/recipe/list">레시피</a>
				<ul class="menu1_s submenu">
					<li><a href="/recipe/list">레시피</a></li>
					<li><a href="#">명예의전당</a></li>
					<li><a href="#">투표하기</a></li>
				</ul></li>
			<li class="menu2"><a href="#">정보게시판</a></li>
			<li class="menu3"><a href="../story/storyList.html">스토리</a></li>
			<li class="menu4"><a href="#">화상채팅</a></li>
			<li class="menu5"><a href="#">공지사항</a></li>
			<li class="menu6"><a href="#">고객센터</a></li>
		</ul>
	</div>
	<div class="direct-search">
		<input type="text" id="searchInput" class="searchInput"
			placeholder="주류명 또는 레시피명을 검색해주세요." autocomplete="off"> <a
			href="#" class=""> <img
			src="https://www.greating.co.kr/front_pc/images/icon_header_search.png?ver=210304"
			id="dvSrhFilterBtn2" alt="검색">
		</a>
	</div>
</div>
</body>
</html>