<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="../../css/comm.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.container {
	margin-top: 10px;
	background-color: #f1f1f2;
	padding: 10px 0 10px 0;
}

#contents_area{
	background-color: white;
	width: 69%;
	display: inline-block;
}

#contents_area div{
	padding: 10px 0 10px 0;
	border-bottom: 10px solid #f1f1f2;
}

#right_area{
	position: relative;
	background-color: white;
	width: 30%;
	float: right;
	vertical-align: top;
	
}

.container img {
	width: 200px;
	height: 150px;
	border-radius: 10px;
}
</style>
<script>
	$(document).ready(function() {
		$("#headerMain").load("/html/comm/header.html");
		$("#footerMain").load("/html/comm/footer.html");
	});
</script>
</head>
<body>
	<div id="headerMain"></div>
	<div class="container">
		<div style="margin: 0 auto; width: 1200px;">
			<div id="contents_area" align="center" >
				<!-- 헤더 -->
				<div>
					<img src="/upload/${requestScope.recipeOne.fileName }" alt="">
					<h1>${requestScope.recipeOne.recipeTitle }</h1>
					<p>${requestScope.recipeOne.recipeContents }</p>
				</div>
				
				
				<!-- 제조과정 -->
				<div>
					<h1>제조과정</h1>
					<c:forEach  items="${requestScope.mList }" var="mOne" varStatus="index">
						<img style="width: 700px; height: 500px; border-radius: 10px;"
							src="/upload/${mOne.fileName }"
							alt="">
						<p>${mOne.makeContents }</p>
					</c:forEach>
				</div>
				<div>
					<h2>한줄 댓글</h2>
					<form class="box_write"  action="/recipeReply/write" method="post">
						<textarea placeholder="한 줄 댓글을 남겨주세요." name="replyContents"></textarea>
						<input type="hidden" name="recipeNo" value="${requestScope.recipeOne.recipeNo}">
						<button name="button" type="submit">댓글남기기</button>
					</form>
					
					<!-- 댓글 리스트 -->
					<table>
						<tr>
							<th>이미지</th>
							<th>아이디</th>
							<th>댓글</th>
							<th>작성날짜</th>
							<th>수정/삭제</th>
						</tr>
						<c:forEach items="${recipeOne.replies}" var="reply" varStatus="index">
						<tr>
							<td><img alt="" src="/img/myPageLogo.png" style="width:50px;height:50px"></td>
							<td>${reply.replyUserId }</td>
							<td>${reply.replyContents }</td>
							<td>${reply.replyDate }</td>
							<td>
							<c:if test="${sessionScope.userId eq reply.replyUserId }">
									<a href="javascript:void(0)" onclick="showModifyReply(this)">수정</a>
									<a href="/recipeReply/delete?recipeNo=${reply.recipeNo }&replyNo=${reply.replyNo }">삭제</a>
							</c:if>
							</td>
						</tr>
						<tr style="display:none;">
							<td><img alt="" src="/img/myPageLogo.png" style="width:50px;height:50px"></td>
							<td>${reply.replyUserId }</td>
							<td><input type="text" size="40" value="${reply.replyContents }" id="modifyReply"></td>
							<td>${reply.replyDate }</td>
							<td>
								<a href="javascript:void(0)" onclick="modifyReply(this,${reply.replyNo},${reply.recipeNo })">수정완료</a>
								/
								<a href="javascript:void(0)" onclick="hideModifyReply(this)">취소</a>
							</td>			
						</tr>
						</c:forEach>
					</table>
					<form action="/recipeReply/modify" method="post" id="modifyForm">
						<input type="hidden" name="replyContents" id="modifyReplyContents">
						<input type="hidden" name="replyNo" id="modifyReplyNo">
						<input type="hidden" name="recipeNo" id="modifyRecipeNo">
					</form>
					<script>
						function modifyReply(obj, replyNo , recipeNo){
							var contents = $(obj).parent().prev().prev().find("input").val(); // obj를 이용하여 값 찾기
							$("#modifyReplyContents").val(contents);
							$("#modifyReplyNo").val(replyNo);
							$("#modifyRecipeNo").val(recipeNo);
							$("#modifyForm").submit();
						}
						function showModifyReply(obj) {
							$(obj).parents("tr").next().show();
							$(obj).parents("tr").hide();
						}
						function hideModifyReply(obj) {
							$(obj).parents("tr").prev().show();
							$(obj).parents("tr").hide();
						}
					</script>
				</div>
			</div>
			<!-- navi -->
			<div id="right_area" align="center">
 				<a href="#"><img src="/img/myPageLogo.png" alt=""></a>
 				
 				
<!--  				좋아요 폼 -->
 				<form>
  					<input type="hidden" name="recipeNo">					
 					<input type="hidden" name="likeNo">
 					
 					
 					
 					
 					<button>좋아요</button>
 				</form>
 				
<!--  				스크랩 폼 -->
 				<form>
 					<input type="hidden">
 					
 					<button>스크랩</button>
 				</form>
 				
 				<div style="border-top:10px solid #f1f1f2;">
 					<h1>재료 리스트</h1>
					<table border="1px">
						<thead>
							<tr>
								<th>재료</th>
								<th>양</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach  items="${requestScope.iList }" var="iOne" varStatus="index">
							<tr>
								<td>${iOne.ingredientName }</td>
								<td>${iOne.ingredientGram }</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
 				</div>
			</div>
			
		</div>
	</div>
	<div id="footerMain"></div>
	
</body>
</html>