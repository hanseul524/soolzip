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
		$("#headerMain").load("/html/comm/header.jsp");
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
						<img style="width: 700px; height: 500px; border-radius: 10px; <c:if test="${empty mOne.fileNo }">display: none;</c:if>"
							src="/upload/${mOne.fileName }"
							alt="" >
						<p>${mOne.makeContents }</p>
					</c:forEach>
				</div>
				<div>
					<h2>한줄 댓글</h2>
					<form class="box_write"  action="/recipeReply/write" method="post">
						<input type="hidden" name="recipeNo" value="${requestScope.recipeOne.recipeNo}">
					<c:if test ="${user.userId eq null}">
						<br>
						댓글 작성을 위해 로그인이 필요합니다
						<br>
						<a href="/index.jsp">로그인 하러가기</a>
						<br>
						<br>
						<br>
					</c:if>
					
					
					<c:if test="${user.userId ne null and userId ne ''}">
						<textarea placeholder="한 줄 댓글을 남겨주세요." name="replyContents" style="width:300px; height:50px; resize:none; "></textarea>
						<button name="button" type="submit">댓글남기기</button>
					</c:if>
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
							<c:if test="${user.userId eq reply.replyUserId }">
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
 				<a href="#">
 					<img src="/img/myPageLogo.png" alt="">
 					<br>
 					${recipeOne.userId }
 				</a>
		<!-- 좋아요 폼 -->
 				<form action="/recipe/like" method="post" id="">
  					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<input type="hidden" name="likeCheck" value ="${recipeOne.likeCheck }">
					<!--로그인 체크 -->
					<c:if test="${user.userId ne null and userId ne ''}">	
						<!--좋아요 아닌 상태 체크 -->
		 				<c:if test="${recipeOne.likeCheck eq null or recipeOne.likeCheck eq 0  }">
		 					<input type="submit" value="좋아요">		
		 				</c:if>
						<!--좋아요 상태 체크 -->
	 					<c:if test="${recipeOne.likeCheck ne null and recipeOne.likeCheck ne 0 }">
	 						<input type="submit"  value="좋아요 취소">	
	 					</c:if>
					</c:if>
 				</form>
 				
		<!-- 스크랩 폼 -->
 				<form action="/recipe/scrap" method="post" id="">
 					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<input type="hidden" name="scrapCheck" value ="${recipeOne.scrapCheck }">
 					<!--로그인 체크 -->
 					<c:if test="${user.userId ne null and userId ne ''}">	
	 					<!-- 스크랩 아닌 상태 체크 -->
	 					<c:if test="${recipeOne.scrapCheck eq null or recipeOne.scrapCheck eq 0  }">
		 					<input type="submit" value="스크랩">		
		 				</c:if>
						<!-- 스크랩 상태 체크 -->
	 					<c:if test="${recipeOne.scrapCheck ne null and recipeOne.scrapCheck ne 0 }">
	 						<input type="submit"  value="스크랩 취소">	
	 					</c:if>
 					</c:if>
 				</form>
 		<!-- 레시피 삭제 -->
 				<form action="/recipe/remove" method="post">
 				<c:if test="${user.userId eq recipeOne.userId}">	
 					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<input type="submit" value="레시피 삭제">
 				</c:if>
 				</form>
 			
 		<!-- 레시피 수정 -->
 			<c:if test="${user.userId eq recipeOne.userId}">
 				<a href="/recipe/modify?recipeNo=${recipeOne.recipeNo }">레시피 수정</button>
 			</c:if>
 
 
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