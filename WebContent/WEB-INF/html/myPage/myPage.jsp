<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SULZIP</title>
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
	$('.sub-tab li,.list li').click(function() {
		var tab_id = $(this).attr('data-tab');

		$(".list li").removeClass("active");
		$(".myPage-con").removeClass("active");
		$(".sub-tab").removeClass("active");
		$(".sub-tab li").removeClass("active");
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
	let wrap = document.querySelector("#recipe123");
	let bar = document.querySelector('#bar2');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar2.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#story123");
	let bar = document.querySelector('#bar3');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar3.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#reply123");
	let bar = document.querySelector('#bar4');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar4.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#reply2");
	let bar = document.querySelector('#bar5');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar5.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#scrap123");
	let bar = document.querySelector('#bar6');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar5.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#myGetMsg123");
	let bar = document.querySelector('#bar7');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar7.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	let wrap = document.querySelector("#sendMsg123");
	let bar = document.querySelector('#bar8');
	wrap.addEventListener('scroll', function() {
		let scrollTop = wrap.scrollTop;
		let scrollHeight = wrap.scrollHeight - wrap.clientHeight;
		// scrollHeight 화면 스크롤
		// wrap.clientHeight div영역 높이
		let percentage = (scrollTop / scrollHeight) * 100;
		bar8.style.height = percentage + '%';
	})
});
$(document).ready(function() {
	$(".sendNo").on("click", function() {
		submit();
	})
});
$(document).ready(function(){
	$("#exit-user").on("click",function(){
		if(confirm('회원탈퇴 하시겠습니까?')){
			return true;
		}else{
			return false;
		}
	})
});
$(document).ready(function(){
	$(".update-btn").on("click",function(){
		submit();
	})
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
.update-btn{margin-left: 30px;width: 250px;height: 50px; background:#fff; border:none; position:relative; transition:800ms ease all;outline:none;padding:0 2em;}
.update-btn:hover{background:#fff; color:#1AAB8A;}
.update-btn:before,.update-btn:after{content:'';position:absolute;top:0;right:0;height:2px;width:0;background:#1AAB8A;transition:400ms ease all;}
.update-btn:after{right:inherit; top:inherit;left:0;bottom:0;}
.update-btn:hover:before,.update-btn:hover:after{width:100%;transition:800ms ease all;}
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
.myPage-con {width: 1000px;height: 680px;border-top: 1px solid #e6e7e8;margin-top: 100px;margin-left: 23px;display: none;overflow: auto;position: relative;}
.myPage-con::-webkit-scrollbar {display: none;}
.barwrap {width: 10px;height: 100%;background-color: #fff;float: right;top: 0;position: sticky;}
.bar,#bar2,#bar3,#bar4,#bar5,#bar6,#bar7,#bar8 {width: 100%;height: 0%;background-color: rgba(145, 140, 0,0.4);transition: height 0.3s ease;}
.active {display: block;}

.location-image img{width: 100%;}
.grid-container{display: grid; grid-template-columns: repeat(auto-fill,minmax(200px, 1fr));grid-gap:1em}
.location-listing{position: relative;}
.location-image{line-height: 0;overflow: hidden; height:70%;}
.location-image img{filter:blur(0px);transition: filter 0.3s ease-in;transform: scale(1.1);}
.location-title{font-size: 1.5em; font-weight: bold; text-decoration: none; z-index:1;position: absolute;width: 100%;height: 100%;top: 0;left: 0;opacity: 0;transition: opacity .5s; background: rgba(145, 140, 0, 0.4);color: white; display: flex;align-items: center;justify-content: center;}
.location-listing:hover .location-title{opacity: 1; color: white;}
.location-listing:hover .location-image img{filter:blur(2px)}
.content-size img{width:15px;height:15px;margin:-2px 3px 0 0; border-bottom:0!important}

</style>
<title></title>
</head>
<body>
	<div id="headerMain"></div> <br>
      <hr style="border: 0.2px solid rgb(236, 236, 236);">
	<div class="my-wrapper">
	<!--프로필  -->
		<aside id="profile">
			<div id="profile-img">
				<img src="/img/myPageLogo.png" alt="">
				<div id="profile-name" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">
				  	<span style="font-size:1.2em;">${user.userId }</span>
				</div>
			</div>
			<div id="profile-count" style="">
				<span>레시피 : ${recipeCount }개 </span><br> <br> <span>스토리
					: ${storyCount }개</span>
			</div>
			<form action="/user/update" method="POST"style="margin-top:0px">
				 <button class="update-btn" style="border-top:1px solid #e6e7e8;">회원 정보 수정</button> <input
					type="hidden" name="userId" value="${user.userId }"> <input
					type="hidden" name="userEmail" value="${user.userEmail }">
				<input type="hidden" name="userPhone" value="${user.userPhone }">
			</form>
			<form action="/exit/user" method ="get">
				<button class="update-btn" id="exit-user" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8; margin-top:0px;">회원탈퇴</button>
			</form>
			
		</aside>
		
		<main>
			<div class="list-div">
				<ul class="list" id="kategori">
					<li class="list tab-0 tab-1 active" id="recipe" data-tab="tab-0">레시피</li>
					<li class="list tab-2" id="story" data-tab="tab-2">스토리</li>
					<li class="list tab-3 tab-8" id="reply" data-tab="tab-3">작성한댓글</li>
					<li class="list tab-4" id="scrap" data-tab="tab-4">스크랩</li>
					<li class="list tab-5 tab-9" id="message" data-tab="tab-5">쪽지함</li>
				</ul>
			</div>
			<div class="sub-tab tab-0 tab-1 active">
				<ul class="con-kategori" id="recipe-con-kategori">
					<li id="cache-recipe-kategori" data-tab="tab-0" class="tab-0 active">임시저장 레시피</li>
					<li id="open-recipe-kategori" data-tab="tab-1" >공개된 레시피</li>
				</ul>
			</div>
			<div class="sub-tab tab-8 tab-3">
				<ul class="con-kategori" id="reply-con-kategori">
					<li id="get-reply-kategori" data-tab="tab-3" class="tab-3">레시피</li>
					<li id="send-reply-kategori" data-tab="tab-8">스토리</li>
				</ul>
			</div>
			<div class="sub-tab tab-9 tab-5">
				<ul class="con-kategori" id="messaage-con-kategori">
					<li id="get-message-kategori" data-tab="tab-5" class="tab-5">받은 쪽지</li>
					<li id="send-message-kategori" data-tab="tab-9">보낸 쪽지</li>
				</ul>
			</div>
			<!-- 레시피 콘텐츠 -->
			<div class="myPage-con tab-0 active">
				<div class="barwrap">
					<div class="bar" ></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty cList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty cList }">
					<div class="grid-container">
						<c:forEach items="${requestScope.cList }" var="cList"
							varStatus="index">
							<article class="location-listing" style="float: left; margin: 10px;">
								<a href="/recipe/modify?recipeNo=${cList.recipeNo }" class="location-title">Click!</a>
								<div class="location-image">
									 <a href="" class="sendNo"><img style="width: 100%; height: 100%;"
										src="/upload/${cList.fileName }" alt="">
									</a>
								</div>
								<div class="box-caption" style="margin: 10px;">
									<div class="box-title">
										<a href="" class="sendNo" style="text-decoration: none; color: black;">${cList.recipeTitle }</a>
									</div>
									<div class="box-name" style="overflow: hidden;">
										<span style="font-size: 0.6em;">작성일 :
											${cList.recipeEnrollDate }</span>
									</div>
								</div>
							</article>
						</c:forEach>
					</div>
				</c:if>
			</div>
			
			<div class="myPage-con tab-1 child-page-listing" id="recipe123">
				<div class="barwrap">
					<div class="bar" id="bar2"></div>
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
			<div class="myPage-con tab-2" id="story123">
				<div class="barwrap" >
					<div class="bar" id="bar3"></div>
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
										src="/story-upload/${sList.fileName }" alt="">
									</a>
								</div>
								<div class="box-caption" style="margin: 10px;">
									<div class="box-title" style="width:200px; height:20px; overflow:hidden;">
										<a href="/story/detail?storyNo=${sList.storyNo }" style="text-decoration: none; color: black;">${sList.storyContents }</a>
										<br><span style="font-size:0.6em; color:gray;">${sList.storyTag }</span>
									</div>
									<div class="box-name" style="overflow: hidden;">
										<span class="content-size">
											작성자 : ${sList.userId }<br>
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
			<!-- 댓글 컨텐츠 -->
			<div class="myPage-con tab-3" id="reply123"
				style="overflow: hidden; overflow-y: scroll;">
				<div class="barwrap">
					<div class="bar" id="bar4"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty reList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty reList }">
					<table class="table table-striped" style="width: 98%;">
						<tr style="text-align: center;">
							<th style="width: 80%;">댓글내용</th>
							<th style="width: 20%;">작성일</th>
						</tr>
						<c:forEach items="${requestScope.reList }" var="reList"
							varStatus="index">
							<tr>
								<td style="font-size:1.1rem">${reList.replyContents }</td>
								<td style="text-align: center; font-size:0.8rem;">${reList.replyDate.getMonth()+1 }-${reList.replyDate.getDate() } ${reList.replyDate.getHours() }:${reList.replyDate.getMinutes() }</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: left; font-size:0.6rem">ㄴ[레시피]<a
									href="/recipe/detail?recipeNo=${reList.recipeNo }">${reList.recipeTitle }</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="myPage-con tab-8" id="reply2"
				style="overflow: hidden; overflow-y: scroll;">
				<div class="barwrap">
					<div class="bar" id="bar5"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty srList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty srList }">
					<table class="table table-striped" style="width: 98%;">
						<tr style="text-align: center;">
							<th style="width: 80%;">댓글내용</th>
							<th style="width: 20%;">작성일</th>
						</tr>
						<c:forEach items="${requestScope.srList }" var="srList"
							varStatus="index">
							<tr>
								<td style="font-size:1.1rem">${srList.replyContents }</td>
								<td style="text-align: center; font-size:0.8rem;">${srList.replyDate.getMonth()+1 }-${srList.replyDate.getDate() } ${srList.replyDate.getHours() }:${srList.replyDate.getMinutes() }</td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: left;font-size:0.6rem">ㄴ[스토리]<a
									href="/story/detail?storyNo=${srList.storyNo }">${srList.storyContents }</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<!-- 스크랩컨텐츠 -->
			<div class="myPage-con tab-4" id="scrap123">
				<div class="barwrap">
					<div class="bar" id="bar6"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty scList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty scList }">
					<div class="grid-container">
					<c:forEach items="${requestScope.scList}" var="scList"
					varStatus="index">
						 <article class="location-listing" style="float: left; margin: 10px;">
						 	<a href="/recipe/detail?recipeNo=${scList.recipeNo }" class="location-title">Click!</a>
								<div class="location-image">
									<a href="/recipe/detail?recipeNo=${scList.recipeNo }"> <img style="width: 100%; height: 100%;"
										src="/upload/${scList.fileName }" alt="">
									</a>
								</div>
								<div class="box-caption" style="margin: 10px;">
									<div class="box-title" style="width:200px; height:20px; overflow:hidden;">
										<a href="/recipe/detail?recipeNo=${scList.recipeNo }" style="text-decoration: none; color: black;">
										<span style="font-size:0.6em; color:gray;">${scList.recipeTitle }</span></a>
									</div>
									<div class="box-name" style="overflow: hidden;">
										<span class="content-size">
                                         	 <img src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2764.png" alt="좋아요 수">
                                     	  	${scList.recipeLikeCount}
                                        </span>
										<span class="content-size">
                                            <img src="/img/댓글 아이콘.png" alt="댓글 수">
											${scList.recipeReplyCount }</span><br> <span style="font-size: 0.6em;">작성일
											: ${scList.recipeEnrollDate }</span>
										</div>
									</div>
							</article>
						</c:forEach>
					</div>
				</c:if>
			</div>
			<!-- 쪽지컨텐츠 -->
			<div class="myPage-con tab-5" id="myGetMsg123">
				<div class="barwrap" >
					<div class="bar"id="bar7"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty mgList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty mgList }">
					<table class="table table-striped" style="width: 98%;">
						<tr style="text-align: center;">
							<th style="width:5%;">번호</th>
							<th style="width: 30%;">제목</th>
							<th style="width: 20%;">발신자</th>
							<th style="width: 30%;">작성일</th>
						</tr>
						<%int cnt = 0; %>
						<c:forEach items="${requestScope.mgList }" var="mgList"
							varStatus="index">
							<% cnt +=1;  %>
							<tr>
								<td style="text-align: center;"><%=cnt %></td>
								<td><a href="/message/detail?msgNo=${mgList.msgNo }" onclick="window.open(this.href,'쪽지','width=1000 height =1300');return false;" >${mgList.msgName }</a></td>
								<td style="text-align: center;"><a href="/user/page?userId=${mgList.msgSendUser }">${mgList.msgSendUser }</a></td>
								<td style="text-align: center;">${mgList.msgSendDate.getMonth()+1 }-${mgList.msgSendDate.getDate() } ${mgList.msgSendDate.getHours() }:${mgList.msgSendDate.getMinutes() }</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="myPage-con tab-9" id="sendMsg123">
				<div class="barwrap">
					<div class="bar" id="bar8"></div>
				</div>
				<!--스크롤 인디게이터 -->
				<c:if test="${empty msList }">
					<center>
						<img style="margin-top: 250px;" src="/upload/story_none.png"
							alt="">
					</center>
				</c:if>
				<c:if test="${!empty msList }">
					<table class="table table-striped" style="width: 98%;">
						<tr style="text-align: center;">
							<th style="width:5%;">번호</th>
							<th style="width: 30%;">제목</th>
							<th style="width: 20%;">수신자</th>
							<th style="width: 30%;">작성일</th>
						</tr>
						<%int cnt = 0; %>
						<c:forEach items="${requestScope.msList }" var="msList"
							varStatus="index">
							<% cnt +=1;  %>
							<tr>
								<td style="text-align: center;"><%=cnt %></td>
								<td><a href="/message/msDetail?msgNo=${msList.msgNo }" onclick="window.open(this.href,'쪽지','width=1000 height =1300');return false;" >${msList.msgName }</a></td>
								<td style="text-align: center;"><a href="/user/page?userId=${msList.msgGetUser }">${msList.msgGetUser }</a></td>
								<td style="text-align: center;">${msList.msgSendDate.getMonth()+1 }-${msList.msgSendDate.getDate() } ${msList.msgSendDate.getHours() }:${msList.msgSendDate.getMinutes() }</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</main>
	</div>
	<div id="footerMain"></div>
</body>
</html>