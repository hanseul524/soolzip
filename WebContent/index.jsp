<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userLogin</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function () {
	$("#headerMain").load("/html/comm/header.jsp");
	$("#footerMain").load("/html/comm/footer.html");
	
	$('#frm').on("submit", function() {
		
		
	    if($('#user-id').val() == "") {
	      alert("아이디를 입력해주세요.");
	      return false;
	    }else if($('#user-pwd').val() == "") {
	      alert("패스워드를 입력해주세요.");
	      return false;
	    }
	    return true;
	  });
});
</script>
</head>
<body>
<div id="headerMain"></div>
  <div id="content" class="contents">
    <div class="loginbox" style="text-align: center;">
      <div class="login">
        <h2>Login</h2>
        <form action="/user/login" name="loginForm" id="frm" method="post">
          <div class="login_id">
            <input type="text" id="user-id" name="user-id" placeholder="아이디">
          </div>
          <div class="login_pw">
            <input type="password" id = "user-pwd" name="user-pwd" placeholder="패스워드">
          </div>
          <div class="login_etc">
            <div class="forget_id">
            <a href="./html/userinfo/findinfo.jsp">아이디/비밀번호 찾기</a>
            </div>
          </div>
          <div class="submit">
            <input type="submit" value="login">
          </div>
          <div class="signup">
            <a href="register.html">signup</a>
       </div>
       <ul id="sns-login">
         <li class="boxlist"><a href="#"><img src="./img/ico_member_naver.png" alt=""><span class="boxtext">네이버</span></a></li>
         <li class="boxlist"><a href="#"><img src="./img/ico_member_kakao.png" alt=""><span class="boxtext">카카오</span></a></li>
       </ul>
       </form>
    </div>
    </div>
  </div>
<div id="footerMain"></div>  
</body>
</html>