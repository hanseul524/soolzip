<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>쪽지</title>
<style>
* {
	margin: 0;
	padding: 0;
}

.h1 {
	width: 100vw;
	height: 100vh;
	background-color: white;
}

#msg-name {
	border: none;
	width: 80%;
	height: 10%;
	outline: none;
	margin-left: 4%;
	font-size: 1.5em;
}

#msg-sub {
	border: none;
	width: 80%;
	outline: none;
	margin-left: 3%;
	padding: 10px;
}

textarea {
	border: none;
	resize: none;
	width: 95%;
	height: 78%;
	outline: none;
	margin-left: 3%;
	margin-top: 5px;
	padding: 10px;
	font-size: 1.2em;
}

hr {
	width: 95%;
	margin: 0 auto;
}

.btn {
	width: 70px;
	float: right;
	padding: 3px;
	margin: 3px;
	margin-right: 10px;
	margin-bottom: 10px;
	border: none;
	background-color: #fff;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="h1">
		<c:forEach items="${requestScope.sdList }" var="sdList"
			varStatus="index">
			<input type="text" id="msg-name" readonly value="${sdList.msgName }">
			<br>
			<span id="msg-sub">수신자 : ${sdList.msgGetUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성일:
				${sdList.msgSendDate }</span>
			<form action="/delete/sendMsg" method="get">
				<button class="btn" id="msg-del" style="margin-right: 30px">삭제</button>
				<input type="hidden" name="msgNo" value="${sdList.msgNo }">
			</form>
				
			<hr>
			<textarea readonly>${sdList.msgContents }</textarea>
			<br>
		</c:forEach>
	</div>
	<script>
		$("#msg-del").on("click", function() {
			confirm("정말 삭제하시겠습니까?");
			if (true) {
				submit();
			} else {
				history.back();
			}
		});
	</script>
</body>