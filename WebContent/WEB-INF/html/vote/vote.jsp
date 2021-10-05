<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/css/comm.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>
        .box li{
            width: 200px;
            height: 400px;
            list-style: none;
            padding: 0;
            margin: 0 12px 40px 0;
            display: inline-block;
            vertical-align: top;
            position: relative;
            border: 1px solid black;
			
        }
        
        .box-caption{
            padding: 10px 2px;
        }
        .box-thumb{
            position: relative;
            border-radius: 4px;
            overflow: hidden;
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
        .box-title2{
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-wrap: break-word;
            white-space: normal;
            line-height: 1.5;
            background-color: blanchedalmond;
        }
        .box-name{
            /* text-align: center; */
            overflow: hidden;
        }
        .box-name a >img{
            width: 30px;
            height: 30px;
        }
        #box-thumb a > img{
            width: 180px;
            height: 180px;
            object-fit: cover;
        }
        .location-image img{width: 100%;height: auto;}
		.grid-container{display: grid; grid-template-columns: repeat(auto-fill,minmax(200px, 1fr));grid-gap:1em}
		.location-listing{position: relative;}
		.location-image{line-height: 0;overflow: hidden;}
		.location-image img{filter:blur(0px);transition: filter 0.3s ease-in;transform: scale(1.1);}
		.location-title{font-size: 1.5em; font-weight: bold; text-decoration: none; z-index:1;position: absolute;width: 180px;height: 180px;top: 0;left: 0;opacity: 0;transition: opacity .5s; background: rgba(145, 140, 0, 0.4);color: white; display: flex;align-items: center;justify-content: center;}
		.location-listing:hover .location-title{opacity: 1; color: white;}
		.location-listing:hover .location-image img{filter:blur(2px)}
		.content-size img{width:15px;height:15px;margin:-2px 3px 0 0; border-bottom:0!important}
		.update-btn{width: 90px;height: 40px; background:#fff; border:none; position:relative; transition:800ms ease all;}
		.update-btn:hover{background:#fff; color:#1AAB8A;}
		.update-btn:before,.update-btn:after{content:'';position:absolute;top:0;right:0;height:2px;width:0;background:#1AAB8A;transition:400ms ease all;}
		.update-btn:after{right:inherit; top:inherit;left:0;bottom:0;}
		.update-btn:hover:before,.update-btn:hover:after{width:100%;transition:800ms ease all;}
    </style>
<script>
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
	});
</script>
</head>

<body>
<div id="headerMain"></div>
  <div style="text-align: center;">
    <h2>투표현황</h2>
 	<div style="width: 800px;  display: inline-block; margin-top: 50px;">
    
     <canvas id="myChart"></canvas>
   	</div>
  </div>
    <hr>

    <ul class="box" style="text-align: center;" >
        <div style="display: inline-block;">
        <c:forEach  items="${requestScope.cList }" var="cOne" varStatus="index">
         <li>
            <article class="location-listing" style="float: left; margin: 10px;">
            
                <a href="/recipe/detail?recipeNo=${rOne.recipeNo }" class="location-title">Click!</a>
                <div class="location-image" id="box-thumb">
                     <a href="" class="sendNo"><img style="width: 180px; height: 180px;"
                        src="/upload/${cOne.fileName }"   alt="">
                    </a>
                </div>
            </article>
            <div>
                <div style="padding:30px" >
                    <div style="width: 150px; overflow: hidden; text-overflow: ellipsis; ">
                    	${cOne.recipeTitle }
                    </div>
                </div>                              
                <div class="box-name">
                    <a href="/user/page?userId=${rOne.userId }">
                        <img src="/img/myPageLogo.png" alt="">
                        ${cOne.recipeUserId }
                    </a>
                </div> 
                <div class="option" style="margin:20px;" >
                    <form action="">
                       <button type="submit" class="update-btn" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">투표하기</button>
                       <button type="submit" class="update-btn" style="border-top:1px solid #e6e7e8;border-bottom:1px solid #e6e7e8;">투표취소</button>
                     </form>
            </div>
            </div>
          </li>
        </c:forEach>
    	</div>
    </ul>



      <script>
        var ctx = document.getElementById('myChart');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [
                	 'Red',
                     'Blue',
                     'Yellow',
                     'Yellow',
                     'Yellow',
                     'Yellow',
                     'Yellow',
                     'Yellow',
                     'Yellow',
                     'Yellow'
                ],
                datasets: [{
                    label: 'Voting status.',
                    data: [65, 59, 80, 81, 56, 55, 40, 50, 60, 20],
                    backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)',
                    'rgba(201, 203, 207, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)',
                    'rgb(201, 203, 207)',
                    'rgb(75, 192, 192)',
                    'rgb(255, 159, 64)'

                    ],
                    borderWidth: 1
                }]
            }
        });
        </script>
         

<div id="footerMain"></div>

</body>
</html>