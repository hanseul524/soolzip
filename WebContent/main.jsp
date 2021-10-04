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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
</head>
<script>
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
	});
</script>
<body>
	<div id="headerMain"></div>
    <!-- contents -->
  <div id="content" class="contents">
    <div class="row" style="background-color: #918c00; margin: 30px 0px 50px 0px;">
      <div class="video-con">
        <video autoplay controls loop>
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
  </div>
    <div id="footerMain"></div>
</body>
</html>