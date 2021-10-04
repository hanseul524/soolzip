<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원페이지</title>
<link rel="stylesheet" href="/css/comm.css">
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
<style>
*{margin: 0;padding: 0;}
.my-wrapper{width: 1400px;background-color: white;margin: 0 auto;margin-top: 50px;}
main{width: 75%;height: 900px; border-top: solid 1px #e6e7e8;}
#profile{float: right;}
#profile-img{width: 200px; height: 200px; margin-top: 100px; margin-left: 40px;}
#profile img{width: 100%;height: 100%;}
#profile-name{margin-top: 10px;text-align: center;}
#profile-count{padding:40px;padding-top: 60px;}
#exit-user{margin-top:10px}
body:before{content:'';height:100%;display:inline-block;vertical-align:middle;}
#update-btn{margin-left: 30px;width: 250px;height: 50px; background:#fff; border:none; position:relative; transition:800ms ease all;outline:none;padding:0 2em;}
#update-btn:hover{background:#fff; color:#1AAB8A;}
#update-btn:before,#update-btn:after{content:'';position:absolute;top:0;right:0;height:2px;width:0;background:#1AAB8A;transition:400ms ease all;}
#update-btn:after{right:inherit; top:inherit;left:0;bottom:0;}
#update-btn:hover:before,#update-btn:hover:after{width:100%;transition:800ms ease all;}
.list li, .sub-tab li {	list-style: none; border-bottom: 1px solid #fff; transition: 0.4s;}
.list li:hover, .sub-tab li:hover {	border-bottom: 1px solid rgb(145, 140, 0);}
.list-div {width: 100%;height: 40px;}
.list {display: flex;}
.list li {flex: 1;display: block;text-align: center;line-height: 40px;border-bottom:solid 1px #e6e7e8; cursor:pointer;}
.list .active{border-bottom:1px solid rgb(145, 140, 0);}
.sub-tab .active{border-bottom:1px solid rgb(145, 140, 0);}
.sub-tab {width: 500px;height: 50px;display: none;position: absolute;margin-left: 25px;margin-top: 20px;}
.sub-tab ul {margin-top: 5px;}
.sub-tab ul li {text-align: center;line-height: 49px;border-bottom: 1px solid #e6e7e8;float: left;width: 200px; cursor:pointer;}
.myPage-con {width: 1000px;height: 680px;border-top: 1px solid #e6e7e8;margin-top: 50px;margin-left: 23px;display: none;overflow: auto;position: relative;}
.myPage-con::-webkit-scrollbar {display: none;}
.barwrap {width: 10px;height: 100%;background-color: #fff;float: right;top: 0;position: sticky;}
.bar,#bar2,#bar3,#bar4,#bar5,#bar6,#bar7,#bar8 {width: 100%;height: 0%;background-color: rgba(145, 140, 0,0.4);transition: height 0.3s ease;}
.active {display: block;}

.location-image img{width: 100%;height: auto;}
.grid-container{display: grid; grid-template-columns: repeat(auto-fill,minmax(200px, 1fr));grid-gap:1em}
.location-listing{position: relative;}
.location-image{line-height: 0;overflow: hidden;}
.location-image img{filter:blur(0px);transition: filter 0.3s ease-in;transform: scale(1.1);}
.location-title{font-size: 1.5em; font-weight: bold; text-decoration: none; z-index:1;position: absolute;width: 100%;height: 100%;top: 0;left: 0;opacity: 0;transition: opacity .5s; background: rgba(145, 140, 0, 0.4);color: white; display: flex;align-items: center;justify-content: center;}
.location-listing:hover .location-title{opacity: 1; color: white;}
.location-listing:hover .location-image img{filter:blur(2px)}
.content-size img{width:15px;height:15px;margin:-2px 3px 0 0; border-bottom:0!important}
</style>
<title></title>
</head>
<body>
	<div id="headerMain"></div>
	<div class="my-wrapper">
		<!--프로필  -->
		<aside id="profile">
			<div id="profile-img">
				<img src="/img/myPageLogo.png" alt="">
				<div id="profile-name" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">
				  	<span style="font-size:1.2em;">${userId }</span>
				</div>
			</div>
			<div id="profile-count" style="">
				<span>레시피 : ${recipeCount }개 </span><br> <br> <span>스토리
					: ${storyCount }개</span>
			</div>
			<!-- 쪽지 세션값 널이면 못보냄 로그인해야 보낼수 있음 -->
			<c:if test = "${user.userId eq null }">
			<form action="" method="POST">
				<button id="update-btn" class="faild-msg" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">쪽지보내기</button>
			</form>
			</c:if>
			<c:if test = "${user.userId ne null }">
			<form action="/send/reply" method="get">
				<button id="update-btn" class="suc-msg" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">쪽지보내기</button>
				<input type="hidden" value="${userId }" name="msgSendUser">
			</form>
			</c:if>
		</aside>

		<main>
			<div class="list-div">
				<ul class="list" id="kategori">
					<li class="list active" id="recipe" data-tab="tab-0">레시피</li>
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
                    <div class="grid-container">
					    <c:forEach items="${requestScope.rList }" var="rList"
						varStatus="index">
                            <article class="location-listing" style="float: left; margin: 10px;">
                                <a href="/recipe/detail?recipeNo=${rList.recipeNo }" class="location-title">Click!</a>
                                <div class="location-image">
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
                                    	<span class="content-size">
                                          	<img src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2764.png" alt="좋아요 수">
                                     	  	${rList.recipeLikeCount}
                                        </span>
                                        <span class="content-size">
                                            <img src="/img/댓글 아이콘.png" alt="댓글 수">
                                            ${rList.recipeReplyCount }
                                        </span><br> <span
                                            style="font-size: 0.6em;">작성일 :
                                            ${rList.recipeEnrollDate }</span>
                                    </div>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
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
				<c:if test="${!empty sList }">
				<div class="grid-container">
					<c:forEach items="${requestScope.sList}" var="sList"
					varStatus="index">
						 <article class="location-listing" style="float: left; margin: 10px;">
						 	<a href="/story/detail?storyNo=${sList.storyNo }" class="location-title">Click!</a>
								<div class="location-image">
									<a href="/story/detail?storyNo=${sList.storyNo }"> <img style="width: 100%; height: 100%;"
										src="/upload/${sList.fileName }" alt="">
									</a>
								</div>
								<div class="box-caption" style="margin: 10px;">
									<div class="box-title" style="width:200px; height:20px; overflow:hidden;">
										<a href="/story/detail?storyNo=${sList.storyNo }" style="text-decoration: none; color: black;">${sList.storyContents }</a>
										<br><span style="font-size:0.6em; color:gray;">${sList.storyTag }</span>
									</div>
									<div class="box-name" style="overflow: hidden;">
										<span class="content-size">
                                          <img src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2764.png" alt="좋아요 수">
                                     	  	${sList.storyLikeCount}
                                        </span>
										<span class="content-size">
                                            <img src="/img/댓글 아이콘.png" alt="댓글 수">
											${sList.storyReplyCount }</span><br> <span style="font-size: 0.6em;">작성일
											: ${sList.storyEnrollDate }</span>
										</div>
									</div>
							</article>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</main>
	</div>
	<div id="footerMain"></div>
</body>
</html>