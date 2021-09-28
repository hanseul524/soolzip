<%@page import="recipe.model.vo.Recipe"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SULZIP</title>
<link rel="stylesheet" href="/css/comm.css">
<link rel="stylesheet" href="/html/myPage/css/myPage.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#header").load("/html/comm/header.html");
		$("#footer").load("/html/comm/footer.html");
	});
	$(document).ready(function(){
        $('.list li,.sub-tab li').click(function(){
            var tab_id = $(this).attr('data-tab');

            $("ul.list li").removeClass("active");
            $(".myPage-con").removeClass("active");
            $(".sub-tab").removeClass("active");
            $(this).addClass("active");
            $("."+tab_id).addClass("active");
        });
    });
</script>
<style>
	.list li,.sub-tab li{list-style: none;}
	.list li:hover,.sub-tab li:hover{background-color: rgba(0,0,0,0.1);}
	.list-div{width: 100%; height: 80px;}
	.sub-tab{width: 500px; height: 50px; display: none; position: absolute; margin-left: 25px; margin-top: 20px;}
	.sub-tab ul{margin-top: 5px; }
	.sub-tab ul li{text-align: center;line-height: 49px; border:1px solid black; float: left; width: 200px;}
	.list{display: flex;}
	.list li{flex:1; display: block; text-align: center; line-height: 79px; border:1px solid black;}
	.myPage-con{width: 1000px;height: 680px;border: 1px solid black;margin-top: 100px;margin-left: 23px; display: none;}
	.active{display: block;}
</style>
<title></title>
</head>
<body>
    <div id="header"></div>
    <div class="wrapper">
        <aside id="profile">
            <div id="profile-img">
                <img src="/img/mainLogo.png" alt="">
                <div id="profile-name">
                    <span>${user.userId }</span>
           </div>
       </div>
       <div id="profile-count">
           <span>레시피 : ${recipeCount }개 </span><br> <br> <span>스토리
               : ${storyCount }개</span>
       </div>
       <form action="/html/myPage/myInfoUpdate.jsp" method="POST">
           <input type="submit" value="회원 정보 수정" id="update-btn"> <input
               type="hidden" name="userId" value="${user.userId }"> <input
               type="hidden" name="userEmail" value="${user.userEmail }">
           <input type="hidden" name="userPhone" value="${user.userPhone }">
       </form>
   </aside>
   <main>
       <div class="list-div">
           <ul class="list" id="kategori">
               <li class="list" id="recipe" data-tab="tab-0">레시피</li>
               <li class="list" id="story" data-tab="tab-2">스토리</li>
               <li class="list" id="reply" data-tab="tab-3">댓글</li>
               <li class="list" id="scrap" data-tab="tab-4">스크랩</li>
               <li class="list" id="message" data-tab="tab-5">쪽지함</li>
           </ul>
       </div>
       <div class="sub-tab tab-0 tab-1 active">
           <ul class="con-kategori" id="recipe-con-kategori">
               <li id="cache-recipe-kategori" data-tab="tab-0">임시저장 레시피</li>
               <li id="open-recipe-kategori" data-tab="tab-1">공개된 레시피</li>
           </ul>
       </div>
       <div class="sub-tab tab-8 tab-3">          
           <ul class="con-kategori" id="reply-con-kategori">
               <li id="get-reply-kategori" data-tab="tab-3">받은 댓글</li>
               <li id="send-reply-kategori" data-tab="tab-8">내가 쓴 댓글</li>
           </ul>
       </div>
       <div class="sub-tab tab-9 tab-5">
           <ul class="con-kategori" id="messaage-con-kategori">
               <li id="get-message-kategori" data-tab="tab-5">받은 쪽지</li>
               <li id="send-message-kategori" data-tab="tab-9">보낸 쪽지</li>
           </ul>
       </div>
       <!-- 레시피 콘텐츠 -->
       <div class="myPage-con tab-0 active">a</div>
       <div class="myPage-con tab-1">
           <c:if test="${empty rList }">
               <center>
                   <img style="margin-top: 250px;" src="/upload/story_none.png"
                       alt="">
               </center>
           </c:if>
           <c:if test="${!empty rList }">
               <c:forEach items="${requestScope.rList }" var="rList"
                   varStatus="index">
                   <div style="float: left; margin: 10px;">
                       <div class="box-thumb" style="width: 200px; height: 200px;">
                           <a href="#"> <img style="width: 100%; height: 100%;"
                               src="/upload/${rList.fileName }" alt="">
                           </a>
                       </div>
                       <div class="box-caption" style="margin: 10px;">
                           <div class="box-title">
                               <a href="#" style="text-decoration: none; color: black;">${rList.recipeTitle }</a>
                           </div>
                           <div class="box-name" style="overflow: hidden;">
                               <span style="font-size: 0.6em;">댓글수 :
                                   ${rList.recipeReplyCount }</span><br> <span
                                   style="font-size: 0.6em;">작성일 :
                                   ${rList.recipeEnrollDate }</span>
                           </div>
                       </div>
                   </div>
               </c:forEach>
           </c:if>
                   ${requestScope.pageNavi}

        </div>
        <div class="myPage-con tab-2">
            <h1>스토리</h1>
        </div>
        <div class="myPage-con tab-3">
            <h1>받은댓글</h1>
        </div>
        <div class="myPage-con tab-8">
            <h1>보낸댓글</h1>
        </div>
        <div class="myPage-con tab-4">
            <h1>스크랩</h1>
        </div>
        
        <div class="myPage-con tab-5">
            <table border="1" id="get-message-table" class="message-table">
                <tr>
                    <th>발신자</th>
                    <th>제목</th>
                    <th>작성일</th>
                </tr>
                <tr>
                    <td>asd</td>
                    <td>asd</td>
                    <td>asd</td>
                </tr>
            </table>
        </div>
        <div class="myPage-con tab-9">
            <table border="1" id="send-message-table" class="message-table">
                <tr>
                    <th>수신자</th>
                    <th>제목</th>
                    <th>작성일</th>
                </tr>
                <tr>
                    <td>asd</td>
                    <td>asd</td>
                    <td>asd</td>
                </tr>
            </table>
        </div>
</div>
</main>
</div>
<div id="footer"></div>
</body>
</html>