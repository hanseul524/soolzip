<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userLogin</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
  <div id="content" class="contents">
    <div class="loginbox" style="text-align: center;">
      <div class="login">
        <h2>Login</h2>
        <form action="/user/login" name="loginForm" method="post">
          <div class="login_id">
            <input type="text" name="user-id" placeholder="아이디">
          </div>
          <div class="login_pw">
            <input type="password" name="user-pwd" placeholder="비밀번호">
          </div>
          <div class="login_etc">
            <div class="forgot_id">
            <a href="findinfo.html">아이디 찾기&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </div>
            <div class="forgot_pwd">
              <a href="findinfo.html">비밀번호 찾기</a>
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
</body>
</html>