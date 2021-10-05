<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.model.vo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>개인정보수정</title>
<link rel="stylesheet" href="/html/myPage/css/myInfoUpdate.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
span {
	font-size: 0.7em;
	margin-top: 3px;
	color: red;
}
* {margin: 0;padding: 0;box-sizing: border-box;font-family: "Noto Sans KR", sans-serif;}
a {text-decoration: none;color: black;}
.wrap {width: 100%;height: 1000px;display: flex;align-items: center;justify-content: center;background: rgba(0, 0, 0, 0.1);}
.join {width: 30%;height: 900px; background: white; border-radius: 20px; display: flex; justify-content: center; align-items: center;flex-direction: column;}
h2 {color: rgba(145, 140, 0,0.4);font-size: 3em;}
.login_id {margin-top: 20px;width: 80%;}
.login_id input {width: 100%;height: 50px;border-radius: 30px;margin-top: 10px;padding: 0px 20px;border: 1px solid lightgray;outline: none;}
.login_pw {margin-top: 20px;width: 80%;}
.login_pw input {width: 100%;height: 50px;border-radius: 30px;margin-top: 10px;padding: 0px 20px;border: 1px solid lightgray;outline: none;}
.login_pw-re{margin-top: 20px;width: 80%;}
.login_pw-re input{width: 100%;height: 50px;border-radius: 30px;margin-top: 10px;padding: 0px 20px;border: 1px solid lightgray;outline: none;}
.login_name{margin-top: 20px;width: 80%;}
.login_name input {width: 100%;height: 50px;border-radius: 30px;margin-top: 10px;padding: 0px 20px;border: 1px solid lightgray;outline: none;}
.login_addr{margin-top: 20px;width: 80%;}
.login_addr input{width: 100%;height: 50px;border-radius: 30px;margin-top: 10px;padding: 0px 20px;border: 1px solid lightgray;outline: none;}
.submit {margin-top: 50px;width: 80%;}
.submit input {width: 100%;height: 50px;border: 0;outline: none;border-radius: 40px;background: linear-gradient(to left, rgba(145,140,0,0.4), rgba(145,140,0,0.4));color: white;font-size: 1.2em;letter-spacing: 2px;}
.submit input:hover{background: linear-gradient(to left, rgba(145,140,0,0.4), rgba(145,140,0,0.8));}
 
</style>
</head>
<body>
	<form action="/user/modify" method="POST">
		<div class="wrap">
			<div class="join">
				<h2>회원정보 수정</h2>
				<div class="login_id">
					<h4>아이디</h4>
					<input type="text" name="userId" id="userid"
						value="<%=request.getAttribute("userId")%>" readonly>
				</div>
				<div class="login_pw">
					<h4>비밀번호</h4>
					<input type="password" name="userPwd" id="password"
						placeholder="수정할 비밀번호를 입력해 주세요">
				</div>
				<span id="password_error"></span>
				<div class="login_pw-re">
					<h4>비밀번호 확인</h4>
					<input type="password" name="" id="password_re"
						placeholder="비밀번호 확인">
				</div>
				<span id="password_re_error"></span>
				<div class="login_name">
					<h4>이메일</h4>
					<input type="text" name="userEmail" id="email"
						placeholder="이메일을 입력해주세요"
						value="<%=request.getAttribute("userEmail")%>">
				</div>
				<span id="email_error"></span>
				<div class="login_addr">
					<h4>휴대폰</h4>
					<input type="text" name="userPhone" id="phone"
						placeholder="'-'제외하고 입력해 주세요"
						value="<%=request.getAttribute("userPhone")%>">
				</div>
				<span id="phone_error"></span>
				<div class="submit">
					<input type="submit" value="회원정보 수정" id="sb"> <br> <br>
					<input type="button" value="취소" id="tq"> <br> <br>
				</div>
			</div>
		</div>
	</form>
	<script>
		//2. 비밀번호
		$("#password").focusout(
				function() {
					var txt = "password";
					var el = $("#" + txt);
					var num = el.val().search(/[0-9]/g);
					var eng = el.val().search(/[a-z]/ig);
					var spe = el.val().search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
					if (el.val() == "") {
						$("#password_error").html("비밀번호를 입력하세요.");

					} else if (el.val().length < 8 || el.val().length > 16) {
						$("#password_error").html("8자~16자 이내로 입력하세요.");

					} else if (el.val().search(/\s/) != -1) {
						$("#password_error").html("비밀번호는 공백 없이 입력하세요.");

					} else if ((num < 0 && eng < 0) || (eng < 0 && spe < 0)
							|| (spe < 0 && num < 0)) {
						$("#password_error").html(
								"영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력하세요.");
					} else {
						$("#password_error").html("");
					}

				});
		$("#password_re").focusout(function() {
			var txt1 = $("#password").val();
			var txt2 = $("#password_re").val();
			if (txt1 != txt2) {
				$("#password_re_error").html('비밀번호가 다름니다');
			} else {
				$("#password_re_error").html('');
			}
		});
		$("#email").focusout(function() {
			regx = /^[a-zA-z0-9]{4,12}@/
			el = $("#email");
			if (el.val() == "") {
				$("#email_error").html("이메일을 입력하세요.");
			} else if (!regx.test(el.val())) {
				$("#email_error").html("이메일을 확인해주세요.(@앞에가 영문자,숫자로 4~12글자 )");
			} else {
				$("#email_error").html("");
			}
		});

		$("#phone").focusout(function() {
			ph = /^[0-9]{11}$/
			qw = $("#phone");
			if (ph.test(qw.val())) {
				$("#phone_error").html("");
			} else {
				$("#phone_error").html('입력값을 확인하세요');
			}
		});

		$("#sb").on(
				'click',
				function() {
					var pwd = $("#password_error").text();
					var pwdre = $("#password_re_error").text();
					var email = $("#email_error").text();
					var phone = $("#phone_error").text();

					if (pwd == "" && pwdre == "" && email == "" && phone == ""
							&& $("#password").val() != ""
							&& $("#password_re").val() != ""
							&& $("#email").val() != ""
							&& $("#phone").val() != "") {
						return true;
					} else {
						alert("입력 정보를 확인하세요.");
						return false;
					}
				});
		$("#tq").on("click", function() {
			history.back();
		});
	</script>
</body>
</html>