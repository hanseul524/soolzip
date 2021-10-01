<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 상세 페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="../../css/story/storyDetail.css">
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
    h4{
        font-family: inherit;
        font-weight: normal;
        line-height: 140%;
    }
    textarea{
        font-family: inherit;
        overflow: auto;
        font: inherit;
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
    <h1 class="h1text" style="text-align: center;">상세페이지</h1>
    <div class="container">
        <div class="contents">
            <div class="two_list">
                <div class="story_view">
                    <div class="story_view_top">
                        <div class="story_view_top_name">
                            <!-- 회원페이지로 이동 링크 -->
                            <a href="">
                                <img src="../../img/myPageLogo.png" alt="">최지현(작성자)
                            </a>
                            <span class="story_view_top_date">시/분/초</span>
                        </div>
                        <div class="story_view_top_button">
                                <!-- 링크 달아줘야함 타입은 히든으로 회원가입한 사람만 볼 수 있게-->
                                <input type="button" value="수정"><input type="button" value="삭제">
                        </div>
                    </div>
                    <div class="story_view_contents">
                        <div class="story_view_contents_img">
                            <img src="/story-upload/${requestScope.storyOne.fileName }" alt="">
                        </div>
                        <!-- 등록할때 쓴 내용을 받아와야함 -->
                        <div class="story_view_contents_txt">${requestScope.storyOne.storyContents }</div>
                        <div class="Story_view_tags">
                            <div class="story_tag_list_txt">${requestScope.storyOne.storyTag }</div>
                        </div>
                    </div>
                </div>
                <!-- 댓글입력 -->
                <div class="reply_write">
                    <div class="input_reply" style="position: relative;">
                        <textarea style="height: 100px; width: 80%; resize: none;" name="text" id="text" class="reply_form"></textarea>
                        <span class="input-btn">
                            <button type="button" class="btn" style="height:100px; width:100px;">등록</button>
                        </span>
                    </div>
                    <!-- 좋아요 -->
                    <div class="btn-like like">
                        <button type="button" class="btn">
                            <img style="vertical-align: middle;" src="../img/좋아요.png" alt="">
                        </button>
                    </div>
                </div>
                <!-- 댓글-->
                <div class="view_reply">
                    <div class="reply_title">
                        댓글
                        <!-- 댓글수 넣어야 댐 -->
                        <span>댓글수(3)</span>
                    </div>
                </div>
                <!-- 회원들 댓글 -->
                <div class="other reply_list" style="margin-top: 15px;">
                    <div class="other_left">
                        <!-- 회원 링크 -->
                        <a href=""><img class="other_object" src="../../img/myPageLogo.png" alt=""></a>
                    </div>
                    <div class="other_mid" style="position: relative;">
                        <!-- 댓글 작성자 작성일 -->
                        <h4 class="mid-title">
                            <b class="info_name" style="cursor: pointer;">최지현</b>
                            날짜 시간 분 초
                        </h4>
                            <!-- 이름을 한번더 넣을거면 b -->
                            <!-- 댓글 -->
                            댓글 댓글 댓글 갱플랭크 못생겼다.
                    </div>
                </div>
                <!-- 회원들 댓글 -->
                <div class="other reply_list" style="margin-top: 15px;">
                    <div class="other_left">
                        <!-- 회원 링크 -->
                        <a href=""><img class="other_object" src="../../img/myPageLogo.png" alt=""></a>
                    </div>
                    <div class="other_mid" style="position: relative;">
                        <!-- 댓글 작성자 작성일 -->
                        <h4 class="mid-title">
                            <b class="info_name" style="cursor: pointer;">이태욱</b>
                            날짜 시간 분 초
                        </h4>
                            <!-- 이름을 한번더 넣을거면 b -->
                            <!-- 댓글 -->
                            반반 프로젝트 가보자!
                    </div>
                </div>
                <!-- 회원들 댓글 -->
            </div>
        </div>
    </div>
    <div id="footerMain"></div>
</body>
</html>