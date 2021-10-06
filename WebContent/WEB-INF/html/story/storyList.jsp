<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 조회</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="../../css/story/storyList.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
</head>
<style>
    a{  /* 밑줄 해제 */
        text-decoration: none;
        background-color: transparent;
        cursor: pointer;
        color: black;
    }
    div{
        display: block;
    }
</style>
<script>
    $(document).ready(function(){
        $("#headerMain").load("/html/comm/header.jsp");
        $("#footerMain").load("/html/comm/footer.html");
    });
</script>
<body>
    <div id="headerMain"></div>
     <br>
      <hr style="border: 0.2px solid rgb(236, 236, 236);">
    <div style="text-align:center;">
    <span class="h1text" style="font-family:'Sunflower', sans-serif;font-size:3rem;font-weight:bold;margin:50px;">스토리</span>
    </div>
    
    <div class="wrap">
        <div class="container">
            <div class="contents">
                <div class="list_form">
                    <ul class="nav1 list_form_nav" style="position: relative;">
                        <li role="presentation" class="total">
                            <a href="">전체</a>
                        </li>
                        <li role="presentation" class="total">
                            <a href="">MY</a>
                        </li>
                        <div class="pull-right">
                            <a href="/story/register"><button  class="btn btn-info" style="font-size: 20px;">스토리 등록</button></a>
                        </div>
                    </ul>
                    <div class="story_tag">
                        <form action="">
                            <!-- <img class="story_search_img" src="./최지현_포트폴리오_수정[JH.GG]/img/그웬.PNG" alt=""> --> <input class="story_tag_search" type="search" placeholder="#아침 #00 🔍">
                            <div align="center">${requestScope.pageNavi }</div>
                        </form>
                    </div>
                    <!-- 스토리 썸네일 타이틀 등등 -->
                    <div class="story_List" style="padding-top: 20px;">
                    <c:forEach items="${requestScope.storyList}" var="sOne" varStatus="index">
                        <div class="story_num1">
                            <div class="thumbnail">
                                <a href="/story/detail?storyNo=${sOne.storyNo }">
                                	<div class="story_List_thumb" style="background: url(/story-upload/${sOne.fileName }) center no-repeat; background-size: cover;"></div>
                                </a>
                                <!-- 스토리 내용 틀-->
                                <div class="story_List_contents">
                                    <!-- 스토리 내용 -->
                                    <div class="story_List_contents_title">${sOne.storyContents }[${sOne.storyViewCount }]</div>
                                    <!-- 스토리 푸터 -->
                                    <div class="story_List_contents_name">
                                        <!-- 좋아요/댓글 왼쪽-->
                                        <div class="story_List_contents_l">
                                            <span>
                                                <img src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2764.png" alt="좋아요 수">
                                             	${sOne.storyLikeCount}
                                            </span>
                                            <span>
                                                <img src="../../img/댓글 아이콘.png" alt="댓글 수">
                                                ${sOne.storyReplyCount }
                                            </span>
                                        </div>
                                        <!-- 작성자 오른쪽 -->
                                        <div class="story_List_contents_r">
                                            <!-- a태그에 회원정보로 이동 img 링크삽입 -->
                                            <a href=""><img src="../img/myPageLogo.png alt="">${sOne.userId }</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                        <!--2번째 스토리 -->
             
                        <!-- n번째 스토리 -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footerMain"></div>
</body>
</html>