<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="user.model.vo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String email = request.getParameter("userEmail"); 
  String phone = request.getParameter("userPhone");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인정보수정</title>
    <link rel="stylesheet" href="./css/myInfoUpdate.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<form action="/user/modify" method="POST">
    <div class="wrap">
        <div class="join">
            <h2>회원정보 수정</h2>
            <div class="login_id">
                <h4>아이디</h4>
                <input type="text" name="userId" id="userid" value="${userId }" readonly>
            </div>
            <div class="login_pw">
                <h4>비밀번호</h4>
                <input type="password" name="userPwd" id="password" placeholder="수정할 비밀번호를 입력해 주세요">
            </div>
            <div class="login_pw-re">
                <h4>비밀번호 확인 </h4>
                <input type="password" name="" id="passwordre" placeholder="비밀번호 확인">
            </div>
            <div class="login_name">
                <h4>이메일</h4>
                <input type="text" name="userEmail" id="han" placeholder="이메일을 입력해주세요" value="<%=email%>">
            </div>
            <div class="login_addr">
                <h4>휴대폰</h4>
                <input type="text" name="userPhone" id="useraddr"placeholder="휴대폰 번호를 입력해주세요" value="<%=phone%>">
            </div>

                <div class="submit">
                    <input type="submit" value="회원정보 수정">
                    <br><br><input type="reset" value="취소">
                </div>
            <!-- </form> -->
	        </div>
	    </div>
    </form>
</body>
</html>