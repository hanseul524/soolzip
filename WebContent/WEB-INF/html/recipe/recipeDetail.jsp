<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>레시피 상세페이지</title>
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

#mainImg {
	width: 350px;
	border-radius: 10px;
	max-width: 100%; height: auto; border:1px solid #eaeaea; padding: 0px;
	margin: 40px 0 20px 0; 
}
#userImg{
	widtt:100px;
}

#mkimg{
	display: block;
    width: 300px;
    margin: 0 auto 13px;
}
.btn.green {background-color: #9abf7f;}
.btn {
	margin: 9px;
}
a[class*="btn"] {text-decoration: none;}
input[class*="btn"], 
button[class*="btn"] {border: 0;}

.rounded {
  border-radius: 10px;
}
.btn:active {
	top: 4px;	
}
.btn {
	position: relative;
	border: 0;
	padding: 15px 25px;
	display: inline-block;
	text-align: center;
	color: white;
}
.btn.green {box-shadow: 0px 4px 0px #87a86f;}
.replytb{width:100%;}
.replytb{border:1px solied black;}
.replytb td{text-align:center;}
</style>
<script>
	$(document).ready(function() {
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
	});
	$('a').click(function(event){
	    event.preventDefault(); 
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
					<img id="mainImg"src="/upload/${requestScope.recipeOne.fileName }" alt="">
					<h1>${requestScope.recipeOne.recipeTitle }</h1>
					<p>${requestScope.recipeOne.recipeContents }</p>
					<span>${recipeOne.recipeMainDrink }</span>
					<span>${recipeOne.recipeAlcohol }</span>
					
				</div>
				
				
				<!-- 제조과정 -->
				<div>
					<h1>제조과정</h1>
					
					<c:forEach  items="${requestScope.mList }" var="mOne" varStatus="index">
						<img id="mkimg" <c:if test="${empty mOne.fileNo }">display: none;</c:if>"
							src="/upload/${mOne.fileName }"
							alt="" >
						<p>${mOne.makeContents }</p>
					</c:forEach>
				</div>
				<!-- 제조과정 end -->
				
				<!-- 댓글작성 -->
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
						<textarea placeholder="한 줄 댓글을 남겨주세요." name="replyContents" style="width:80%; height:50px; resize:none; position:relative"></textarea>
						<button name="button" type="submit" style="float:right; margin-right:15px; margin-left:-50px;height:50px;background:#fff;border:none">댓글남기기</button>
					</c:if>
					</form>
					
					<!-- 댓글 리스트 -->
					<table class="replytb">
						<tr>
							<th>아이디</th>
							<th>댓글</th>
							<th>작성날짜</th>
							<th>수정/삭제</th>
						</tr>
						<c:forEach items="${recipeOne.replies}" var="reply" varStatus="index">
						<tr>
							<td>${reply.replyUserId }</td>
							<td>${reply.replyContents }</td>
							<td>${reply.replyDate.getMonth()+1 }.${reply.replyDate.getDate()} ${reply.replyDate.getHours() }:${reply.replyDate.getMinutes() }</td>
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
					<!-- 댓글 end -->
					<!-- 댓글 수정 -->
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
			
			
			<!-- nav 바  -->
			<div id="right_area" align="center">
 				<a href="/user/page?userId=${recipeOne.userId }">
 					<img id="userImg" src="/img/myPageLogo.png" alt="">
 					<br>
 					<h3>${recipeOne.userId }</h6>
 				</a>
			<!-- 좋아요 폼 -->
 				<form action="/recipe/like" method="post" id="">
  					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<input type="hidden" name="likeCheck" value ="${recipeOne.likeCheck }">
					<!--로그인 체크 -->
					<c:if test="${user.userId ne null and userId ne ''and user.userId ne recipeOne.userId}">	
						<!--좋아요 아닌 상태 체크 -->
		 				<c:if test="${recipeOne.likeCheck eq null or recipeOne.likeCheck eq 0  }">
		 					<input id="likebtn" type="submit" value="좋아요" style="display:none">
		 					<img src="/img/빈하트.png" style="height:25px; width:25px; cursor:pointer" onclick="javascript: $('#likebtn').click();">
		 						
		 				</c:if>
						<!--좋아요 상태 체크 -->
	 					<c:if test="${recipeOne.likeCheck ne null and recipeOne.likeCheck ne 0 }">
	 						<input id="likebtn" type="submit" value="좋아요취소" style="display:none">
		 					<img src="/img/찬하트.png" style="height:25px; width:25px; cursor:pointer" onclick="javascript: $('#likebtn').click();">
		 						
	 					</c:if>
					</c:if>
 				</form>
 				
		<!-- 스크랩 폼 -->
 				<form action="/recipe/scrap" method="post" id="">
 					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<input type="hidden" name="scrapCheck" value ="${recipeOne.scrapCheck }">
 					<!--로그인 체크 -->
 					<c:if test="${user.userId ne null and userId ne '' and user.userId ne recipeOne.userId }">	
	 					<!-- 스크랩 아닌 상태 체크 -->
	 					<c:if test="${recipeOne.scrapCheck eq null or recipeOne.scrapCheck eq 0 }">
		 					<input type="submit" value="스크랩">		
		 				</c:if>
						<!-- 스크랩 상태 체크 -->
	 					<c:if test="${recipeOne.scrapCheck ne null and recipeOne.scrapCheck ne 0 }">
	 						<input type="submit"  value="스크랩 취소">	
	 					</c:if>
 					</c:if>
 				</form>
 		<!-- 레시피 삭제 -->
 				<form id="recipeRM"action="/recipe/remove" method="post">
 				<c:if test="${user.userId eq recipeOne.userId}">	
 					<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
 					<a href="javascript:recipeRM.submit();" class="btn green rounded">레시피 삭제</a>
 				</c:if>
 				</form>
 			
 		<!-- 레시피 수정 -->
 			<c:if test="${user.userId eq recipeOne.userId}">
 				<a href="/recipe/modify?recipeNo=${recipeOne.recipeNo }" class="btn green rounded">레시피 수정</a>
 			</c:if>
 
 				<!-- 재료 리스트 -->
 				<style>
 				table.type11 {
				  border-collapse: separate;
				  border-spacing: 1px;
				  text-align: center;
				  line-height: 1.5;
				  margin: 20px 10px;
				}
				table.type11 th {
				  width: 155px;
				  padding: 10px;
				  font-weight: bold;
				  vertical-align: top;
				  color: #fff;
				  background: #918c00 ;
				}
				table.type11 td {
				  width: 155px;
				  padding: 10px;
				  vertical-align: top;
				  border-bottom: 1px solid #ccc;
				  background: #eee;
				}
 				</style>
 				
 				<div style="border-top:10px solid #f1f1f2;">
 					<h1>재료 리스트</h1>
					<table class="type11">
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