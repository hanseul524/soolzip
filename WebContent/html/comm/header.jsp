<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet" href="/css/comm.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
input:focus{outline:none;}
#asd{width:100px; height:25px; position:absolute; margin-left:-20px; background-color:rgb(145, 140, 0);text-align:center;color:#fff;font-weight:bold;display:none;}
</style>
<script>
$(document).ready(function () {
	$("#log-btn").on("click",function(){
		if(confirm ("정말 로그아웃 하시겠습니까?")) {
			alert("로그아웃 되었습니다")
			location.href = "/user/logout";
			return true;
		}else{
			return false;
		}
	});
	$("#regicon").hover(function(){
		$("#asd").css("display","block");
	},function(){
		$("#asd").css("display","none");
	});
});
</script>
</head>
<body>
<div id="wrap" style="position: relative;">
	<!-- 사용자 nav -->
	<div class="user-area">
		<ul class="user-area-menu">
			<c:if test="${user.userAdmin eq 'Y' }">
			<li class="menu-li"><a href="/admin/qnalist"><img style="width: 45px; margin: 5px;"
					src="/img/adminLogo.png" alt="">
			</a></li>
			</c:if>
			<li class="menu-li" id="recipe-i">
				<a href="/recipe/register" id="regicon"> 
					<img style="width: 45px; margin: 5px;" src="/img/enrollRecipe.png" alt="">
				</a>
				<div id="asd">레시피 등록</div>
			</li>
			
			<c:if test ="${user.userId eq null}">
				<li class="menu-li"><a href="/index.jsp"> <img style="width: 50px; margin: 5px;"
						src="/img/myPageLogo.png" alt=""></a>
				</li>		
			</c:if>
			<c:if test="${user.userId ne null and userId ne ''}">

    			<li class="loginok">
        			<a href="#"><img style="width: 50px; margin: 5px;"
        			src="/img/myPageLogo.png" alt=""></a>
      				<ul class="mypage">
        				<li><a href="/myPage/myPage">마이페이지</a></li>
        				<li><a href="/user/logout" id="log-btn">로그아웃</a></li>
        				<li><a href="/vote/list">투표하기</a></li>
      				</ul>
  				</li>
			</c:if>
		</ul>
	</div>
	<!-- 메인 로고 -->
	<div class="header">
		<a href="/main"> <img style="width: 370px; height: 80px;"
			src="/img/mainLogo.png" alt="">
		</a>
		
	</div>
	
	<!-- 메인 nav -->
	<div class="nav" id="main-nav">
		<ul class="myMenu">
			<li class="menu1"><a href="/recipe/list">레시피</a>
			<li class="menu2"><a href="/story/list">스토리</a></li>
			<li class="menu3"><a href="/legend/recipe">명예의전당</a></li>
			<li class="menu4"><a href="/vote/list">투표현황</a></li>
			<li class="menu5"><a href="/service/center">고객센터</a></li>
		</ul>
	</div>
	<div class="direct-search">
		<form id="searchForm"action="/recipe/search" method="post">
			<input type="text" id="searchInput" name="searchInput" placeholder="주류명 또는 레시피명을 검색해주세요." autocomplete="off">
			<a href="javascript:searchForm.submit();" class="a">
			  <img src="https://www.greating.co.kr/front_pc/images/icon_header_search.png?ver=210304"  alt="검색">
			</a>
		</form>
	</div>
</div>
</body>
</html>