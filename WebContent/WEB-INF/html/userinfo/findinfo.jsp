<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findinfo</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findinfo.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function(){
	$("#headerMain").load("/html/comm/header.jsp");
	$("#footerMain").load("/html/comm/footer.html");
	  
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');
		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');
		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	});
	$("#idfrm").on("sumbit", function() {
		$("#findid").css("display", "block");
		$("#searchid").css("display", "none");
	})
});
</script>
</head>
<body>
<div id="headerMain"></div>
  <div id="content" class="contents">
    <div class="loginbox" style="text-align: center;">
      <div class="login" >
        <h2 id="find-id">아이디 찾기</h2><h2 id="find-pwd" style="display: none;">비밀번호 찾기</h2>
        <ul class="tabs">
          <li class="tab-link current" data-tab="tab-1">아이디 찾기</li>
          <li class="tab-link" data-tab="tab-2">비밀번호 찾기</li>
        </ul>
        <form action="/user/findid" method="get" id="idfrm">
          <div id="tab-1" class="tab-content current">
            <div class="login_id">
              <input type="text" name="user-name" placeholder="이름">
            </div>
            <div class="login_pw">
              <input type="text" name="user-email" placeholder="이메일">
            </div>
            <div class="submit">
              <input type="submit" value="아이디 찾기">
            </div>
          </form>
          <div>
            <span id="searchid">회원 가입시 입력한 이름과 이메일을 입력해주세요.</span>
            <c:if test="${not empty userOne }">
            <span id="findid">회원님의 아이디는 ${userOne.userId}입니다.</span>
            </c:if>
          </div>
          </div>
        <form action="/user/findpwd" method="get">
        <div id="tab-2" class="tab-content">
          <div class="login_id">
            <input type="text" name="user-id" placeholder="아이디">
          </div>
          <div class="login_pw">
            <input type="text" name="user-email" placeholder="이메일">
          </div>
          <div class="submit">
            <input type="submit" value="임시비밀번호 발송">
          </div>
        </form>
        <div>
          <span>회원 가입시 입력한 아이디와 이메일을 확인 후 <br> 
            가입하셨던 이메일로 임시 비밀번호를 발송해드립니다.</span>
        </div>
        </div>
    </div>
    </div>
  </div>
<div id="footerMain"></div>
</body>
</html>