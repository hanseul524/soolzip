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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
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
            height: 350px;
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
     .box .legend-recipe{
   	 --borderWidth: 3px;
	  position: relative;
	  border-radius: var(--borderWidth);
	  background-color:white;
    }
    .legend-recipe { --borderWidth: 1px; position: relative;}
    .legend-recipe:after { content: ''; position: absolute; top: calc(-1 * var(--borderWidth)); left: calc(-1 * var(--borderWidth)); height: calc(100% + var(--borderWidth) * 2); width: calc(100% + var(--borderWidth) * 2); background: linear-gradient(60deg, #f79533, #f37055, #ef4e7b, #a166ab, #5073b8, #1098ad, #07b39b, #6fba82); border-radius: calc(1.5 * var(--borderWidth)); z-index: -1; animation: animatedgradient 2s ease alternate infinite; background-size: 300% 300%; } 
    @keyframes animatedgradient { 0% { background-position: 0% 50%; } 50% { background-position: 100% 50%; } 100% { background-position: 0% 50%; } }

    .glory img{
    	width:30px;
    	height:30px;
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
	 <br>
     <hr style="border: 0.2px solid rgb(236, 236, 236);">
	<div style="text-align: center; margin-bottom:50px;margin-top:50px;">
    <span style="font-family:'Sunflower', sans-serif;font-size:3rem;font-weight:bold;">명예의 전당</span><br><br>
    <span style="font-family: 'Sunflower', sans-serif;font-size:1.5rem;">사용자들의 투표로 선정된 믿음과 신뢰의 레시피를 만나보세요 </span>
    </div>
    <!-- 레시피 리스트 -->
    <ul class="box">
    	<c:if test="${empty lList }">
    	 <center>
			<img style="margin: 100px 0 100px 0;" src="/upload/story_none.png"
				alt="">
		</center>
    	</c:if>
    	<c:forEach items="${requestScope.lList }" var="rOne" varStatus="index">
        <li class="legend-recipe">
            <div class="glory"style="width:30px;height:30px;">
            	<img src="data:image/jpeg;base64,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">
            </div>
            <div class="box-thumb">
                <a href="/recipe/detail?recipeNo=${rOne.recipeNo }">
                    <img style="width:250px; height:230px;object-fit:cover;" src="/upload/${rOne.fileName }" alt="" >
                </a>
            </div>
            <div class="box-caption">
				<div class="recipe_List_contents">
			        <!-- 스토리 내용 -->
			        <div class="recipe_List_contents_title">${rOne.recipeTitle }</div>
			        <div class="recipe_List_contents_content">
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
			                <span>
			                	<img style="width:20px; height:20px;" src="data:image/png;base64,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">
			                	${rOne.recipeViewCount }
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
			<td colspan="4" align="center" style="font-size:1.2rem;">
				${requestScope.pageNavi}
			</td>
		</tr>
    </table>
    <div id="footerMain"></div>
    
</body>
</html>