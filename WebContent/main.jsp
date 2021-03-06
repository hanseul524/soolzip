<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>semiProjectMain</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/comm.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css">
  	<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
  	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<script> 
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
		
	var swiper = new Swiper(".mySwiper", {
           slidesPerView: 4,
           spaceBetween: 30,
           slidesPerGroup : 4,
           speed: 800,
           // loop : true, // 무한 반복
           autoplay: {
           delay: 3000,
           disableOnInteraction: false,
           },
           pagination: {
           el: ".swiper-pagination",
           clickable: true,
           },
           navigation: {
           nextEl: ".swiper-button-next",
           prevEl: ".swiper-button-prev",
           },
       });
	});
</script>
<style>
.swiper {width: 90%; height:40%}
.swiper-slide {text-align:center;display:flex;align-items:center;justify-content:center;}
.swiper-slide img {width: 200px;height: 200px;object-fit: cover; border-radius:20px; border:1px solid #0704041a;}
.swiper-slide:hover{}
.update-btn{width: 250px;height: 50px; background:#fff; border:none; position:relative; transition:800ms ease all;}
.update-btn:hover{background:#fff; color:#1AAB8A;}
.update-btn:before,.update-btn:after{content:'';position:absolute;top:0;right:0;height:2px;width:0;background:#1AAB8A;transition:400ms ease all;}
.update-btn:after{right:inherit; top:inherit;left:0;bottom:0;}
.update-btn:hover:before,.update-btn:hover:after{width:100%;transition:800ms ease all;}
</style>
<body>
<div id="headerMain"></div>
    <!-- contents -->
  <div id="content" class="contents">
    <div class="row" style="background-color: #918c00; margin: 30px 0px 50px 0px;">
      <div class="video-con">
        <video autoplay controls loop style="pointer-events: none;">
          <source src="/img/main.mp4" type="video/mp4">
        </video>
      </div>
      <div class="text-con">
        <img src="/img/content-text2.png" alt="">
      </div>
      <div class="search-con">
        <input style="color: #fff;" type="text" name="" id="" placeholder="레시피 검색하기 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
      </div>
    </div>
	<div style="width:1300px; height: 700px; margin: 0 auto;">
     	<span style="margin-left:30px;font-size:2rem; font-weight:bold;">명예의 전당 레시피</span>
     	<p style="margin-left: 30px; font-weight: nomal; color:#918c00;">술집에서 가장 인기있는 레시피를 소개합니다.</p>
     	<a href="/vote/list"><button class="update-btn" style="float:right;border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">투표하러 가기</button></a>
     	<span style="float:right; margin-right: 30px; line-height:50px; font-size:20px; font-weight: nomal">내손으로 직접뽑는 레시피
     	<i class="fas fa-chevron-right"></i>
     	</span>
   	
	    <div class="swiper mySwiper" style="width:1200px; height:500px; margin:0 auto;">
	        <div class="swiper-wrapper" style="">
	            <!-- 1번사진 0-->
	           <c:if test="${!empty rList }">
		           <c:forEach items="${requestScope.rList }" var="rList" varStatus="index">
		            <div class="swiper-slide">
		                <a href="/recipe/detail?recipeNo=${rList.recipeNo }" style="position: absolute; text-decoration: none;">
		                <img src="/upload/${rList.fileName }"><br>
		                <span style="font-size:1rem;font-weight:400">${rList.recipeTitle }</span>
	                    </a>
		            </div>
		            </c:forEach>
	     		</c:if>
	     		<c:if test="${empty rList }">
	     			<img style="margin:0 auto" src="/upload/story_none.png"
							alt="">
	     		</c:if>
	     	</div>
	         <!-- 네비게이션 버튼 지정 -->
	         <div class="swiper-button-prev"style=""></div><!-- 이전 버튼 -->
	         <div class="swiper-button-next"style=""></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
	         <!-- 페이징 -->
	         <div class="swiper-pagination"></div>
	     </div>
  	</div>
  	<div class="story-go">
  		<img src="/img/storyimg.png" class="imgback">
  		<img src="/img/storytext.png" class="imgtext">
  		<button type="button" class="Btn2"><a href="/story/list">바로가기</a></button>
  	</div>
  </div>
	<!-- 명예의전당 후보 -->
    <div id="footerMain"></div>
</body>
</html>