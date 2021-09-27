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
<!-- 헤더 푸터 css파일넣기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function(){
       $("#header").load("/html/comm/header.html");
       $("#footer").load("/html/comm/footer.html");
    });
</script>
<title>sulzip-mypage</title>
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
                <span>레시피 : ${recipeCount }개 </span><br><br>
                <span>스토리 : ${storyCount }개</span>
            </div>
            	<form action="/html/myPage/myInfoUpdate.jsp" method="POST">
	                <input type="submit" value="회원 정보 수정" id="update-btn">
	                <input type="hidden" name="userId"value="${user.userId }">
	                <input type="hidden" name="userEmail"value="${user.userEmail }">
	                <input type="hidden" name="userPhone"value="${user.userPhone }">
                </form>
        </aside>
        <main>
            <ul class="list" id="kategori">
                <li class="list" id="recipe">레시피</li>
                <li class="list" id="story">스토리</li>
                <li class="list" id="reply">댓글</li>
                <li class="list" id="scrap">스크랩</li>
                <li class="list" id="message">쪽지함</li>
            </ul>
                <!--레시피 카테고리-->
                <ul class="con-kategori" id="recipe-con-kategori">
                    <li id="cache-recipe-kategori">임시저장 레시피</li>
                    <li id="open-recipe-kategori">공개된 레시피</li>
                </ul>
                <div class="myPage-con" id="cache-recipe">
					<c:forEach var="rList" items="${rList }">
						<ul>
							<li>${rList.file_name }</li>
						</ul>
					</c:forEach>
					
                </div>
                <div class="myPage-con" id="open-recipe">
                    <h1>공개된 레시피</h1>
                </div>
                <!--스토리 카테고리-->
                <div class="myPage-con" id="open-story">
                    <h1>스토리</h1>
                </div>
                <!--댓글 카테고리-->
                <ul class="con-kategori" id="reply-con-kategori">
                    <li id="get-reply-kategori">받은 댓글</li>
                    <li id="send-reply-kategori">내가 쓴 댓글</li>
                </ul>
                <div class="myPage-con" id="get-reply-con">
                    <h1>받은댓글</h1>
                </div>
                <div class="myPage-con" id="send-reply-con">
                    <h1>보낸댓글</h1>
                </div>
                <!--스크랩 카테고리-->
                <div class="myPage-con" id="scrap-con">
                    <h1>스크랩</h1>
                </div>
                <!--쪽지함 카테고리-->
                <ul class="con-kategori" id="messaage-con-kategori">
                    <li id="get-message-kategori">받은 쪽지</li>
                    <li id="send-message-kategori">보낸 쪽지</li>
                </ul>
                <div class="myPage-con" id="get-message-con">
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
                <div class="myPage-con" id="send-message-con">
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
    <script>
        /*각 카테고리 눌럿을때 화면에서 사라지고 보여줄 스크립트*/
        /*제일 처음 보여야할 화면*/
        $("#cache-recipe").show();
        $("#cache-recipe-kategori").show();
        $("#open-recipe-kategori").show();  
        /*쪽지함 카테고리 눌럿을때*/
        $("#message").on("click",function(){
            $("#recipe-con-kategori").hide();
            $("#cache-recipe").hide();
            $("#open-recipe").hide();
            $("#open-story").hide();
            $("#get-reply-con").hide();
            $("#send-reply-con").hide();
            $("#scrap-con").hide();
            $("#get-message-con").show();
            $("#send-message-con").hide();
            $("#get-message-kategori").show();
            $("#send-message-kategori").show();
            $("#get-reply-kategori").hide();
            $("#send-reply-kategori").hide();
        });
        /*쪽지함 컨텐츠 카테고리 눌럿을때*/
        $("#get-message-kategori").on("click",function(){
            $("#get-message-con").show();
            $("#send-message-con").hide();
        });
        $("#send-message-kategori").on("click",function(){
            $("#get-message-con").hide();
            $("#send-message-con").show();
        });
        /*레시피 카테고리 눌럿을때*/
        $("#recipe").on("click",function(){
            $("#recipe-con-kategori").show();
            $("#cache-recipe").show();
            $("#open-recipe").hide();
            $("#open-story").hide();
            $("#get-reply-con").hide();
            $("#send-reply-con").hide();
            $("#scrap-con").hide();
            $("#get-message-con").hide();
            $("#send-message-con").hide();
            $("send-message-kategori").hide();
            $("get-message-kategori").hide();
            $("#get-reply-kategori").hide();
            $("#send-reply-kategori").hide();
            $("#get-message-kategori").hide();
            $("#send-message-kategori").hide();
        });
        /*임시저장 레시피 눌럿을때*/
        $("#cache-recipe-kategori").on("click",function(){
            $("#open-recipe").hide();
            $("#cache-recipe").show();
        });
        /*전체공개 레시피 눌럿을때*/
        $("#open-recipe-kategori").on("click",function(){
            $("#open-recipe").show();
            $("#cache-recipe").hide();
        });
        /*스토리 카테고리 눌럿을때*/
        $("#story").on("click",function(){
            $("#recipe-con-kategori").hide();
            $("#cache-recipe").hide();
            $("#open-recipe").hide();
            $("#open-story").show();
            $("#get-reply-con").hide();
            $("#send-reply-con").hide();
            $("#scrap-con").hide();
            $("#get-message-con").hide();
            $("#send-message-con").hide();
            $("#get-reply-kategori").hide();
            $("#send-reply-kategori").hide();
            $("#get-message-kategori").hide();
            $("#send-message-kategori").hide();
        });
        /*댓글 카테고리 눌럿을때*/
        $("#reply").on("click",function(){
            $("#recipe-con-kategori").hide();
            $("#cache-recipe").hide();
            $("#open-recipe").hide();
            $("#open-story").hide();
            $("#get-reply-con").show();
            $("#send-reply-con").hide();
            $("#scrap-con").hide();
            $("#get-message-con").hide();
            $("#send-message-con").hide();
            $("#get-reply-kategori").show();
            $("#send-reply-kategori").show();
            $("#get-message-kategori").hide();
            $("#send-message-kategori").hide();
        });
        /*댓글 콘텐츠 카테고리 눌럿을때*/
        $("#get-reply-kategori").on("click",function(){
            $("#get-reply-con").show();
            $("#send-reply-con").hide();
        });
        $("#send-reply-kategori").on("click",function(){
            $("#get-reply-con").hide();
            $("#send-reply-con").show();
        });
         
        /*스크랩 카테고리 눌럿을때*/
        $("#scrap").on("click",function(){
            $("#recipe-con-kategori").hide();
            $("#cache-recipe").hide();
            $("#open-recipe").hide();
            $("#open-story").hide();
            $("#get-reply-con").hide();
            $("#send-reply-con").hide();
            $("#scrap-con").show();
            $("#get-message-con").hide();
            $("#send-message-con").hide();
            $("#get-reply-kategori").hide();
            $("#send-reply-kategori").hide();
            $("#get-message-kategori").hide();
            $("#send-message-kategori").hide();
        });
       if(window.location.hash == "#messaage-con-kategori"){
           $(".myPage-con").hide();
           $("#get-message-con").show();
           $("#recipe-con-kategori").hide();
           $("#get-message-kategori").show();
           $("#send-message-kategori").show();
       }
       if(window.location.hash == "#messaage-con-kategori"){
           $(".myPage-con").hide();
           $("#send-message-con").show();
           $("#recipe-con-kategori").hide();
           $("#get-message-kategori").show();
           $("#send-message-kategori").show();
       }
    </script>
</body>
</html>
</body>
</html>