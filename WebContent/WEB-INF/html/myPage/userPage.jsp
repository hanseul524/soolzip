<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원페이지</title>
<link rel="stylesheet" href="/css/comm.css">
<link rel="stylesheet" href="/html/myPage/css/myPage.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
	});
	$(document).ready(function() {
		$('.list li,.sub-tab li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$("ul.list li").removeClass("active");
			$(".myPage-con").removeClass("active");
			$(this).addClass("active");
			$("." + tab_id).addClass("active");
		});
	});

	$(document).ready(function() {
		let wrap = document.querySelector(".myPage-con");
		let bar = document.querySelector('.bar');
		wrap.addEventListener('scroll', function() {
			let scrollTop = wrap.scrollTop;
			let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
			//scrollHeight 화면 스크롤
			//wrap.clientHeight div영역 높이
			let percentage = (scrollTop / scrollHeight) * 100;
			bar.style.height = percentage + '%';
		})
	});
	$(document).ready(function(){
		$("#update-btn").on("click",function(){
			if()
		});
	});
</script>
<style>
.list li, .sub-tab li {
	list-style: none;
}

.list li:hover, .sub-tab li:hover {
	background-color: rgba(0, 0, 0, 0.1);
}

.list-div {
	width: 100%;
	height: 80px;
}

.sub-tab {
	width: 500px;
	height: 50px;
	display: none;
	position: absolute;
	margin-left: 25px;
	margin-top: 20px;
}

.sub-tab ul {
	margin-top: 5px;
}

.sub-tab ul li {
	text-align: center;
	line-height: 49px;
	border: 1px solid black;
	float: left;
	width: 200px;
}

.list {
	display: flex;
}

.list li {
	flex: 1;
	display: block;
	text-align: center;
	line-height: 79px;
	border: 1px solid black;
}

.myPage-con {
	width: 1000px;
	height: 680px;
	border: 1px solid black;
	margin-top: 100px;
	margin-left: 23px;
	display: none;
	overflow: auto;
	position: relative;
}

.myPage-con::-webkit-scrollbar {
	display: none;
}

.barwrap {
	width: 10px;
	height: 100%;
	background-color: #fff;
	float: right;
	top: 0;
	position: sticky;
}

.bar {
	width: 100%;
	height: 0%;
	background-color: rgb(145, 140, 0);
	transition: height 0.3s ease;
}

.active {
	display: block;
}
</style>
<title></title>
</head>
<body>
	<div id="headerMain"></div>
	<div class="wrapper">
		<!--프로필  -->
		<aside id="profile">
			<div id="profile-img">
				<img src="/img/mainLogo.png" alt="">
				<div id="profile-name">
					<span>${user.userId }</span>
				</div>
			</div>
			<div id="profile-count">
				<span>레시피 : ${recipeCount }개 </span><br> <br> <span>스토리
					: ${storyCount }개</span>
			</div>
			<form action="쪽지보내기" method="POST">
				<input type="submit" value="쪽지보내기" id="update-btn">
			</form>
		</aside>

		<main>
			<div class="list-div">
				<ul class="list" id="kategori">
					<li class="list" id="recipe" data-tab="tab-0">레시피</li>
					<li class="list" id="story" data-tab="tab-2">스토리</li>
				</ul>
			</div>

			<!-- 레시피 콘텐츠 -->
			<div class="myPage-con tab-0 active">
				<div class="barwrap">
					<div class="bar"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty cList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty cList }">
					<c:forEach items="${requestScope.cList }" var="cList"
						varStatus="index">
						<div style="float: left; margin: 10px;">
							<div class="box-thumb" style="width: 200px; height: 200px;">
								<a href="#"> <img style="width: 100%; height: 100%;"
									src="/upload/${cList.fileName }" alt="">
								</a>
							</div>
							<div class="box-caption" style="margin: 10px;">
								<div class="box-title">
									<a href="#" style="text-decoration: none; color: black;">${cList.recipeTitle }</a>
								</div>
								<div class="box-name" style="overflow: hidden;">
									<span style="font-size: 0.6em;">작성일 :
										${cList.recipeEnrollDate }</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>

			<!-- 스토리 컨텐츠 -->
			<div class="myPage-con tab-2">
				<div class="barwrap">
					<div class="bar"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty sList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:forEach items="${requestScope.sList}" var="sList"
					varStatus="index">
					<c:if test="${!empty sList }">
						<div style="float: left; margin: 10px;">
							<div class="box-thumb" style="width: 200px; height: 200px;">
								<a href="/story/detail?storyNo=${sList.storyNo }"> <img
									style="width: 100%; height: 100%;"
									src="/upload/${sList.fileName }" alt="">
								</a>
							</div>
							<div class="box-caption" style="margin: 10px;">
								<div class="box-title"
									style="width: 200px; height: 20px; overflow: hidden;">
									<a href="/story/detail?storyNo=${sList.storyNo }"
										style="text-decoration: none; color: black;">${sList.storyContents }</a>
									<br> <span style="font-size: 0.6em; color: gray;">${sList.storyTag }</span>
								</div>
								<div class="box-name" style="overflow: hidden;">
									<span style="font-size: 0.6em;">댓글수 :
										${sList.storyReplyCount }</span><br> <span
										style="font-size: 0.6em;">작성일 : ${sList.storyEnrollDate }</span>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</main>
	</div>
	<div id="footerMain"></div>
</body>
</html>