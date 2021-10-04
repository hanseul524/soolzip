<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>전체 레시피 조회</title>
        <link rel="stylesheet" href="../../css/comm.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
        a {
            text-decoration: none;
        }

        ul,li{
            padding: 0;
            list-style: none;
        }
        .kategoire{
            margin: 0 auto;
            width: 1000px;
        }
        .kategoire li{
            display: inline-block;
            margin: 0 10px 25px;
            text-align: center;
            box-sizing: border-box;
        }
      	 .kategoire li > a:hover{
           background-color: #918c0038;
      	 }
        .kategoire li > a{
            display: table;
            width: 115px;
            height: 115px;
            font-size: 16px;
            color: #1c1c1c;
            background: #f5f5f5;
            border-radius: 50%;
        }
        .kategoire li > a span{
            display: inline-block;
            margin-top: 40px;
            padding: 0 10px;
            vertical-align: middle;
            word-break: keep-all;
        }
        .box{
         margin: 0 auto;
         width: 1300px;
        }
        
        .box li{
         	border:1px solid #8080807a; 
            width: 282px;
            list-style: none;
            padding: 0;
            margin: 0 37px 40px 0;
            display: inline-block;
            vertical-align: top;
            position: relative;
        }
        .box li:hover{
        border:1px solid #918c00;
        }
        
        
        .box-thumb{
            position: relative;
            border-radius: 4px;
            overflow: hidden;
            text-align: center;
            margin: 15px 0 15px 0;
        }
        .box-title{
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-wrap: break-word;
            white-space: normal;
            line-height: 1.5;
        }
        .box-name{
            /* text-align: center; */
            overflow: hidden;
        }
        .box-name a >img{
            width: 22px;
            height: 22px;
        }
        .option .like button{
            display: block;
            width: 100%;
            height: 27px;
            text-align: right;
            background-color: transparent;
            border: none;
            color: #999;
            box-sizing: border-box;
            padding-right: 10px;
            line-height: 25px;
            line-height: 32px\0;
        }
         .recipe_List_contents {
            padding: 10px 20px;
        }
        .recipe_List_contents_title {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-wrap: break-word;
            white-space: normal;
            line-height: 140%;
            font-size: 16px;
            margin-bottom: 8px;
            padding-bottom: 1px;
        }
        .recipe_List_contents_content{
         	overflow: hidden;
            text-overflow: ellipsis;
             display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-wrap: break-word;
            white-space: normal;
            line-height: 140%;
            font-size: 8px;
            color: #1c1c1cd4;
            margin-bottom: 8px;
            padding-bottom: 1px;
        }
        .recipe_List_contents_l {
            display: inline-block;
            color: #777;
            font-weight: 300;
        }   
        .recipe_List_contents_r {
            float: right;
            color: #999;
            font-size: 14px;
            vertical-align: top;
            margin-right: 2px;
        }
/*     	.kategoire li .active{ */
/*      		background-color: #918c00; */
/*     	} */
    
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
    <h1 style="text-align: center;">명예의 전당</h1>
    <!-- 정렬 nav -->
    <ul></ul>
    
    <!-- 레시피 리스트 -->
    <ul class="box">
    	<c:if test="${empty rList }">
    	 <center>
			<img style="margin: 100px 0 100px 0;" src="/upload/story_none.png"
				alt="">
		</center>
    	</c:if>
    	<c:forEach items="${requestScope.rList }" var="rOne" varStatus="index">
        <li>
            <div class="box-thumb">
                <a href="/recipe/detail?recipeNo=${rOne.recipeNo }">
                    <img style="width:250px; height:200px;" src="/upload/${rOne.fileName }" alt="" >
                </a>
            </div>
            <div class="box-caption">
				<div class="recipe_List_contents">
			        <!-- 스토리 내용 -->
			        <div class="recipe_List_contents_title">${rOne.recipeTitle }[${rOne.recipeViewCount }]</div>
			        <div class="recipe_List_contents_content">
			        	${rOne.recipeContents }
			        </div>
			        <!-- 스토리 푸터 -->
			        <div class="recipe_List_contents_name">
			            <!-- 좋아요/댓글 왼쪽-->
			            <div class="recipe_List_contents_l">
			                <span>
			                    <img style="width:20px; height:20px;" src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2764.png" alt="좋아요 수">
			                    ${rOne.recipeLikeCount }
			                </span>
			                <span>
			                    <img style="width:20px; height:20px;" src="/img/댓글 아이콘.png" alt="댓글 수">
			                    ${rOne.recipeReplyCount }
			                </span>
			            </div>
			            <!-- 작성자 오른쪽 -->
			            <div class="recipe_List_contents_r">
			                <!-- a태그에 회원정보로 이동 img 링크삽입 -->
			                <a href="/user/page?userId=${rOne.userId }"><img style="width:20px; height:20px;" src="/img/myPageLogo.png" alt="이미지">${rOne.userId }</a>
			                
			            </div>
			        </div>
			    </div>
            </div>
        </li>
    	</c:forEach>
    
        
    </ul>
    <table align="center">
		<!-- 내비게이션 바 -->
		<tr>
			<td colspan="4" align="center">
				${requestScope.pageNavi}
			</td>
		</tr>
    </table>
    <div id="footerMain"></div>
    
</body>
</html>