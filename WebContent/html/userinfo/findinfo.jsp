<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findinfo</title>
</head>
<body>
	  <div id="content" class="contents">
    <div class="loginbox" style="text-align: center;">
      <div class="login" >
        <h2 id="find-id">아이디 찾기</h2>
        <ul class="tabs">
          <li class="tab-link current" data-tab="tab-1">아이디 찾기</li>
          <li class="tab-link" data-tab="tab-2">비밀번호 찾기</li>
        </ul>
        <form action="/user/findid" method="post">
          <div id="tab-1" class="tab-content current">
            <div class="login_id">
              <input type="text" name="user-name" placeholder="이름">
            </div>
            <div class="login_pw">
              <input type="password" name="user-email" placeholder="이메일">
            </div>
            <div class="submit">
              <input type="submit" value="아이디 찾기">
            </div>
            </div>
          </form>
          <div>
            <span>회원 가입시 입력한 이름과 이메일을 입력해주세요.</span>
          </div>
          </div>
        <form action="/user/findpwd" method="post">
        <div id="tab-2" class="tab-content">
          <div class="login_id">
            <input type="text" name="user-id" placeholder="아이디">
          </div>
          <div class="login_pw">
            <input type="password" name="user-email" placeholder="이메일">
          </div>
          <div class="submit">
            <input type="submit" value="임시비밀번호 발송">
          </div>
          </div>
        </form>
        <div>
          <span>회원 가입시 입력한 닉네임과 이메일을 확인 후 <br> 
            가입하셨던 이메일로 임시 비밀번호를 발송해드립니다.</span>
        </div>
        </div>
    </div>
</body>
</html>