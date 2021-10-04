<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>semiProjectMain</title>
    <link rel="stylesheet" href="css/comm.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script> 
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script> 
</head>
<script>
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
		AOS.init();
	});
</script>
<style>
.img{position: relative; height: 100vh;background-size: cover;}
.img-cover{position: absolute;height: 100%;width: 100%;background-color: rgba(0, 0, 0, 0.4);z-index:1;}
.img .content{position: absolute;top:50%;left:20%;transform: translate(-50%, -50%);font-size:2rem;color: white;z-index: 2;text-align:center;}
.search{position:absolute;left:40%;top:50%;transform: translate(-10%, -10%);z-index:2;}
.search a {position: absolute;right: 10px;top: 3px;	display: inline-block;vertical-align: middle;}
.search img{height:40px;}
.content2{position: absolute;top:50%;left:10%;transform: translate(-30%, -50%);font-size:2rem;color: white;z-index: 2;text-align:center;}
#regend{border:1px solid black;width:60%;height:70%; position:absolute;left:30%;top:20%;}
.content3{position: absolute;top:50%;left:50%;transform: translate(-50%, -50%);font-size:2rem;color: white;z-index: 2;text-align:center;}
</style>
<body>
	<div id="headerMain" style="margin-bottom:70px"></div>
    <!-- contents -->
    <div class="img" style="background-image: url(/upload/피나콜라다.PNG);">
        <div class="content">
            <h1>최고의 맛집</h1>
            <h2>술ZIP~</h2>
        </div>
        <div class="search">
			<input type="text" id="searchInput" class="searchInput" size=80
			placeholder="주류명 또는 레시피명을 검색해주세요." autocomplete="off" style="height:40px;outline:none;border-radius:20px;pading:0px 20px"> <a
			href="#"> <img
			src="https://www.greating.co.kr/front_pc/images/icon_header_search.png?ver=210304"
			id="dvSrhFilterBtn2" alt="검색"></a>
		</div>
        <div class="img-cover"></div>
    </div>
    <div class=img style="background-image: url(/upload/얼음잔.PNG);">
    	<div class="content2">
            <h1>명예의 전당</h1>
            <h2>레시피</h2>
            <button>투표하러가기</button>
        </div>
        <div id="regend">
        	
        </div>
        <div class="img-cover"></div>
    </div>
    <div class=img style="background-image: url(/upload/얼음잔.PNG);">
    	<div class="content3">
            <h1>우리는 이런 사이트에요</h1>
            <h2>ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</h2>
        </div>
        <div class="img-cover"></div>
    </div>
    <div id="footerMain"></div>
</body>
</html>