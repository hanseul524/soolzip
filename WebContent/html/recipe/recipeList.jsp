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
         border:1px solid black; 
            width: 282px;
            list-style: none;
            padding: 0;
            margin: 0 37px 40px 0;
            display: inline-block;
            vertical-align: top;
            position: relative;
        }
        
        .box-caption{
            padding: 10px 2px;
            text-align: center;
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
    <h1 style="text-align: center;">레시피</h1>
    <!-- 카테고리 -->
    <ul class="kategoire" style="border:solid 1px gray; padding: 20px 0 0 20px;">
        <li><a href="#"><span>전체</span></a></li>
        <li><a href="#"><span>소주</span></a></li>
        <li><a href="#"><span>맥주</span></a></li>
        <li><a href="#"><span>막걸리</span></a></li>
        <li><a href="#"><span>와인</span></a></li>
        <li><a href="#"><span>보드카</span></a></li>
        <li><a href="#"><span>럼</span></a></li>
        <li><a href="#"><span>진</span></a></li>
        <li><a href="#"><span>데킬라</span></a></li>
        <li><a href="#"><span>위스키</span></a></li>
        <li><a href="#"><span>브랜디</span></a></li>
        <li><a href="#"><span>전통주</span></a></li>
        <li><a href="#"><span>명예의전당</span></a></li>
        <li><a href="#"><span>기타</span></a></li>
    </ul>
    <br>
    <br>

    <!-- 정렬 nav -->
    <ul></ul>
    
    <!-- 레시피 리스트 -->
    <ul class="box">
    	<c:forEach items="${requestScope.rList }" var="rOne" varStatus="index">
        <li>
            <div class="box-thumb">
                <a href="/recipe/detail?recipeNo=${rOne.recipeNo }">
                    <img style="width:250px; height:200px;" src="/upload/${rOne.fileName }" alt="" >
                </a>
            </div>
            <div class="box-caption">
                <div class="box-title">${rOne.recipeTitle }[${rOne.recipeReplyCount }]</div>
                <div class="box-name"  style="overflow: hidden;">
                    <a href="#"> 
                        <img src="/img/myPageLogo.png" alt="">
                        ${rOne.userId }
                    </a>
                    <br>
                <span>조회수 : ${rOne.recipeReplyCount } </span> <span>좋아요수 : ${rOne.recipeLikeCount } </span>
                </div>
            </div>
        </li>
    	</c:forEach>
    
        
    </ul>
    <table align="center">
    	<tr>
			<td colspan="3" align="center">
				<form action="/notice/search" method="get">
					<input type="text" name="searchKeyword">
					<input type="submit" value="검색">
				</form>
			</td>
			<td>
				<a href="/html/recipe/recipeReg.html">글쓰기</a>
			</td>
		</tr>
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