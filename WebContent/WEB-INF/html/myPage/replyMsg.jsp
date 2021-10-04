<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
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
	border-left: none;
	border-right: none;
	border-top: none;
	height: 10%;
	outline: none;
	margin-left: 4%;
	font-size: 1.5em;
	margin-top:20px;
	margin-bottom: 20px
}

#susin {
	outline: none;
	border: none;
	width: 70px;
	height: 3%;
	margin-left: 3%;
	margin-bottom: 15px;
	font-size: 1em;
}

#susin-id {
	outline: none;
	height: 3.5%;
}

textarea {
	resize: none;
	width: 90%;
	height: 500px;
	outline: none;
	margin-left: 3%;
	margin-top: 5px;
	padding: 10px;
	font-size: 1.2em;
}

hr {
	width: 95%;
	margin: 0 auto;
	margin-bottom: 10px;
	background-color: rgba(173, 173, 173, 0.89);
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
	font-size: 1em;
}

.cn {
	margin-right: 30px;
}
</style>
</head>
<body>
	<div class="h1">
		<form action="/msg/replyMsg" method="post">
			<input type="text" id="msg-name" name="msgName" placeholder="제목입력란" size=50><br>
			<input type="text" id="susin" value="수신자 : " readonly><input
				type="text" id="susin-id" placeholder="수신자 id" name="msgGetUser"
				value="<%=request.getAttribute("msgSendUser")%>"> <input
				type="button" value="이전" class="btn cn"> <input
				type="submit" value="전송" class="btn">
			<hr>
			<textarea name="contents" onKeyUp="javascript:fnChkByte(this,'1000')"
				placeholder="내용 입력란"></textarea>
				<br>
			<span id="byteInfo" style="margin-left: 29px;">0</span>/ 1000bytes
		</form>

	</div>
	<script>
		function fnChkByte(obj, maxByte) {
			var str = obj.value;
			var str_len = str.length;

			var rbyte = 0;
			var rlen = 0;
			var one_char = "";
			var str2 = "";

			for (var i = 0; i < str_len; i++) {
				one_char = str.charAt(i);
				if (escape(one_char).length > 4) {
					rbyte += 2; //한글2Byte
				} else {
					rbyte++; //영문 등 나머지 1Byte
				}
				if (rbyte <= maxByte) {
					rlen = i + 1; //return할 문자열 갯수
				}
			}
			if (rbyte > maxByte) {
				alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
				str2 = str.substr(0, rlen);
				obj.value = str2;
				fnChkByte(obj, maxByte);
			} else {
				document.getElementById('byteInfo').innerText = rbyte;
			}
		}
		$(".cn").on("click", function() {
			history.back();
		});
	</script>
</body>
</html>