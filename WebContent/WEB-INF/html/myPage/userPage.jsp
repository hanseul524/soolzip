<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원페이지</title>
<link rel="stylesheet" href="/css/comm.css">
<link rel="stylesheet" href="/html/myPage/css/userPage.css">
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
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	var wrap = document.querySelector("#st");
	var bar = document.querySelector('#bar2');
	wrap.addEventListener('scroll', function() {
		var scrollTop = wrap.scrollTop;
		var scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		var percentage = (scrollTop / scrollHeight) * 100;
		bar2.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	$('.faild-msg').on('click', function() {
		alert('로그인이 필요한 서비스입니다.');
		return false;
	});
});
</script>

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
				 	<span>${userId }</span> 
				</div>
			</div>
			<div id="profile-count">
				<span>레시피 : ${recipeCount }개 </span><br> <br> <span>스토리
					: ${storyCount }개</span>
			</div>
			<!-- 쪽지 세션값 널이면 못보냄 로그인해야 보낼수 있음 -->
			<c:if test = "${user.userId eq null }">
			<form action="" method="POST">
				<input type="submit" value="쪽지보내기" id="update-btn" class="faild-msg">
			</form>
			</c:if>
			<c:if test = "${user.userId ne null }">
			<form action="/send/reply" method="get">
				<input type="submit" value="쪽지보내기" id="update-btn">
				<input type="hidden" value="${userId }" name="msgSendUser">
			</form>
			</c:if>
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
				<c:if test="${empty rList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty rList }">
					<c:forEach items="${requestScope.rList }" var="rList"
						varStatus="index">
						<div style="float: left; margin: 10px;">
							<div class="box-thumb" style="width: 200px; height: 200px;">
								<a href="/recipe/detail?recipeNo=${rList.recipeNo }"> <img
									style="width: 100%; height: 100%;"
									src="/upload/${rList.fileName }" alt="">
								</a>
							</div>
							<div class="box-caption" style="margin: 10px;">
								<div class="box-title">
									<a href="/recipe/detail?recipeNo=${rList.recipeNo }"
										style="text-decoration: none; color: black;">${rList.recipeTitle }</a>
								</div>
								<div class="box-name" style="overflow: hidden;">
									<span style="font-size: 0.6em;">댓글수 :
										${rList.recipeReplyCount }</span><br> <span
										style="font-size: 0.6em;">작성일 :
										${rList.recipeEnrollDate }</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<!-- 스토리 컨텐츠 -->
			<div class="myPage-con tab-2" id="st">
			<div class="barwrap">
					<div id="bar2"></div>
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