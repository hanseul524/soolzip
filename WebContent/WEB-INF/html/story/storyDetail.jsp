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
/*     좋아요버튼 */
.likebuble { 
        position: fixed;
        bottom: -60px; 
        width: 50px; 
        height: 50px; 
        background: url("/img/찬하트.png") no-repeat;
         background-size: cover; 
        } 
        .likebuble:first-of-type { 
            left: 10px; 
            animation: bubble 2s 2s linear infinite 
        } 
        .likebuble:nth-of-type(2) { 
            left: 50%; 
            animation: bubble 10s 1s linear infinite 
        } 
        .likebuble:nth-of-type(3) {
             left: 150px; 
             animation: bubble 8s 1s linear infinite 
            } 
            .likebuble:nth-of-type(4) { 
                right: 100px; 
                animation: bubble 3.5s 1s linear infinite 
            } 
            .likebuble:nth-of-type(5) { 
                right: 10px; 
                animation: bubble 3s 1s linear infinite 
            } 
            .likebuble:nth-of-type(6) { 
                right: 30px; 
                animation: bubble 5s 1s linear infinite 
            } 
            .likebuble:nth-of-type(7) {
                 right: 55px; 
                 animation: bubble 7s 1s linear infinite 
                } 
                .likebuble:nth-of-type(8) {
                     right: 50%; 
                     animation: bubble 9s 1s linear infinite 
                    } 
                    .likebuble:nth-of-type(9) { 
                        right: 70%; animation: bubble 3s 1s linear infinite 
                    } 
                    .likebuble:nth-of-type(10) { 
                        right: 10%; animation: bubble 2.5s 1s linear infinite 
                    } 
                    @keyframes bubble { 0% { bottom: -100px; opacity: 1 } 50% { opacity: 0 } to { bottom: 100%; opacity: 0 } }
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
                            <a href="/user/page?userId=${storyOne.userId }">
                                <img src="../../img/myPageLogo.png" alt="">${storyOne.userId }
                            </a>
                            <span class="story_view_top_date">${storyOne.storyEnrollDate}</span>
                        </div>
                        <div class="story_view_top_button">
                                <!-- 링크 달아줘야함 타입은 히든으로 회원가입한 사람만 볼 수 있게-->
                                <c:if test="${user.userId eq storyOne.userId }">
                                <a href="/story/modify?storyNo=${storyOne.storyNo }">
                                	<input type="hidden" name="storyNo" value="${storyOne.storyNo }">
                                	<input type="submit" value="수정">
<!-- 									스토리 수정 -->
                                </a>
                                </c:if>
                                <form action="/story/remove" method="post">
                                <c:if test="${user.userId eq storyOne.userId }">
                                <a href="/WEB-INF/html/story/storylist.jsp">
                                	<input type="hidden" name="storyNo" value="${storyOne.storyNo }">
                                	<input type="submit" value="삭제">
                                </a>
                                </c:if>
                                </form>
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
                    
                    <form action="/storReply/wirte" method="post"> 
                    	<input type ="hidden" name="storyNo" value="${requestScope.storyOne.storyNo }">
                        <textarea style="height: 100px; width: 80%; resize: none;" name="replyContents" id="text" class="reply_form"></textarea>
                    <c:if test="${user.userId eq null }">          
<!--                 	css수정해야한다. -->
                    	<a class="btn" id="login_link" href="/index.jsp">
                    		<div style="margin-top:35px;">로그인</div>
                    	</a>
                    </c:if>
                    <c:if test="${user.userId ne null and userId ne '' }">
                        <span class="input-btn">
                            <button type="submit" name="button" class="btn" style="height:100px; width:100px;">등록</button>
                        </span>
                    </c:if>
                    </form>
                    
                    </div>
                    <!-- 좋아요 -->
                    <form action ="/story/like" method="post">
                    <input type="hidden" name="storyNo" value="${storyOne.storyNo}">
                    <input type="hidden" name="likeCheck" value ="${storyOne.likeCheck }">
                    <c:if test="${user.userId ne null and userId ne '' and user.userId ne storyOne.userId}">
                    <div class="btn-like like">
                    	<c:if test="${storyOne.likeCheck eq null or storyOne.likeCheck eq 0 }">
                    		<button id="likebtn" type="submit" class="btn" value="좋아요" >
                           		<img style="vertical-align: middle;" src="/img/빈하트.png" alt="" onclick="javascript:$('#likebtn').click();">
                       		</button>
                    	</c:if>
                    	<c:if test="${storyOne.likeCheck ne null and storyOne.likeCheck ne 0 }">
                        	<button id="likebtn" type="submit" class="btn" value="좋아요 취소" >
                            	<img style="vertical-align: middle;" src="/img/찬하트.png" alt="" onclick="javascript:$('#likebtn').click();">
                        	</button>
                    	</c:if>
                    </div>
                    </c:if>
                   </div>
                    </form>
                </div>
                <!-- 댓글 타이틀-->
                <div class="view_reply">
                
                    <div class="reply_title">
                        댓글
                        <!-- 댓글수 넣어야 댐 -->
                        <span></span>
                    </div>
 
                </div>
                <!-- 회원들 댓글 -->
                <c:forEach items="${storyOne.replies }" var="reply" varStatus="index">
	                	<div class="other reply_list" style="margin-top: 15px;">
		                    <div class="other_left">
		                        <!-- 회원 링크 -->
		                        <a href=""><img class="other_object" src="../../img/myPageLogo.png" alt=""></a>
		                    </div>
		                    <div class="other_mid" style="position: relative;">
		                        <h4 class="mid-title">
		                            <b class="info_name" style="cursor: pointer;">${reply.replyUserId }</b>
		                        <!-- 댓글 작성자 작성일 -->
		                            ${reply.replyDate }
		                            <c:if test="${user.userId eq reply.replyUserId }">
		                             <a class="modify_submit" href="javascript:void(0)" onclick="showModifyReply(this);">수정</a>
		                             <a class="modify_submit" href="/storyReply/delete?storyNo=${reply.storyNo }&replyNo=${reply.replyNo}">삭제</a>
		                            </c:if>
		                        </h4>
		                            <!-- 이름을 한번더 넣을거면 -->
		                            <!-- 댓글 -->
		                            ${reply.replyContents }
		                    </div>
	                    </div>
                 		<table>
                           <tr style="display:none;">
								<td>
									<div style="margin-left: 50px ">
										<img alt="" src="/img/myPageLogo.png" style="width:50px;height:50px">
									</div>
								</td>
								<td>
									<div style="margin-left: 10px ">
										${reply.replyUserId }
									</div>
								</td>
								<td><input style="margin-left: 10px " type="text" size="40" value="${reply.replyContents }" id="modifyReply"></td>
								<td >
									<div style="margin-left: 10px ">
										${reply.replyDate }
									</div>
								</td>
								<td>
									<a class="modify_submit" style="margin-left: 10px; " href="javascript:void(0)" onclick="modifyReply(this,${reply.replyNo},${reply.storyNo })">수정완료</a>
									
									<a class="modify_submit" style="margin-left: 10px; href="javascript:void(0)" onclick="hideModifyReply(this)">취소</a>
								</td>			
							</tr>
                       </table>
					</c:forEach>
					<form action="/storyReply/modify" method="post" id="modifyForm">
						<input type="hidden" name="replyContents" id="modifyReplyContents">
						<input type="hidden" name="replyNo" id="modifyReplyNo">
						<input type="hidden" name="storyNo" id="modifyStoryNo">
					</form>
                </div>
                
                <!-- 회원들 댓글 -->

                <!-- 회원들 댓글 -->
			<div class="bnr">
            <div style="margin-left: 50px" class="jackpots">ｓｏｏＬ</div>
<!--             <div style="margin-left: 100px" class="jackpots">ｏ</div> -->
<!--             <div style="margin-left: 100px" class="jackpots">ｏ</div> -->
<!--             <div style="margin-left: 100px" class="jackpots">Ｌ</div> -->
			<div style="margin-left: 100px" class="jackpots">story</div>
            <div style="margin-left: 115px" class="jackpots">.ＺＩＰ</div>
<!--             <div style="margin-left: 100px" class="jackpots">Ｉ</div> -->
<!--             <div style="margin-left: 100px" class="jackpots">Ｐ</div> -->
            <div style="margin-left: 100px" class="jackpots"></div>
			</div>
           
            
            </div>
        </div>
    </div>
<!--     <div class="likebuble"></div> -->
<!--      <div class="likebuble"></div> -->
<!--       <div class="likebuble"></div> -->
<!--        <div class="likebuble"></div> -->
<!--         <div class="likebuble"></div> -->
<!--          <div class="likebuble"></div> -->
<!--           <div class="likebuble"></div> -->
<!--            <div class="likebuble"></div> -->
<!--             <div class="likebuble"></div> -->
<!--              <div class="likebuble"></div> -->
<!--               <div class="likebuble"></div> -->
    <div id="footerMain"></div>
	<script>
		function modifyReply(obj, replyNo , storyNo){
			var contents = $(obj).parent().prev().prev().find("input").val(); // obj를 이용하여 값 찾기
			$("#modifyReplyContents").val(contents);
			$("#modifyReplyNo").val(replyNo);
			$("#modifyStoryNo").val(storyNo);
			$("#modifyForm").submit();
		}
		function showModifyReply(obj) {
			$(obj).parents(".reply_list").next().find("tr").css("display", "block");
			$(obj).closest("h4").hide();
		}
		function hideModifyReply(obj) {
			$(obj).parents("h4").prev().show();
			$(obj).closest("h4").hide();
		}
	</script>
</body>
</html>