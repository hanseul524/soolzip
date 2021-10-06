<%@page import="topqna.model.vo.TopQna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="qna.model.vo.Qna"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<Qna> qList = (List<Qna>) request.getAttribute("qList");
List<TopQna> tqList = (List<TopQna>) request.getAttribute("tqList");
%>
<!DOCTYPE html>
<html lang="en">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>servicecenter</title>
<link rel="stylesheet" href="/css/comm.css">
<!-- <link rel="stylesheet" href="/css/qna/qna.css"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
<head>
<style type="text/css">
.body {
	width: 100%;
	height: 100%;
	padding: 10px;
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	font-size: 14px;
	color: #000;
	margin: 0;
	padding: 0;
	text-align: right;
}

#x-button {
	float: right;
}

.main_layout {
	margin: 20px;
}

.amount {
	top: auto;
	display: flex;
	width: 1255px;
	margin: auto;
	line-height: 35px;
}

.amount>li {
	flex: 1;
	text-align: center;
	height: 90px;
	margin: 10px;
	border: none;
	padding: 10px;
	overflow: hidden;
	background-color: #918c0038;
	border-radius: 15px;
}

.liactive1:hover {
	background-color: #918c00;
	border: 2px solid #918c0038;
}

.liactive1.active {
	background-color: #918c00;
	border: 2px solid #918c00;
}

.liactive2:hover {
	background-color: #918c00;
	border: 2px solid #918c0038;
}

.liactive2.active {
	background-color: #918c00;
	border: 2px solid #918c00;
}

.contents1 {
	overflow: hidden;
}

.result {
	overflow: hidden;
}

#content {
	position: static;
	top: auto;
	width: 1000px;
	display: inline-block;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 30px 10px;
}

.close {
	float: right;
	cursor: pointer;
}

body {
	font-family: Arial; sans-serif;
	font-size: 2rem;
	font-weight: bolder;
}

.qnawrite {
	display: inline-block;
	background-color: blueviolet;
}

#btn {
	float: left;
}

.boxTitle {
	text-align: center;
}

.qnaList_div {
	background-color: brown;
}

.tab {
	overflow: hidden;
	border-bottom: 1px solid #ccc;
	background-color: #f1f1f1;
}

.tab button {
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 17px;
	background-color: #918c0038;
}

.tab button.active {
	background-color: #c2be5c;
}

.tabcontent {
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}

#topQnaContents {
	border: none;
	resize: none;
	background: transparent;
}

button, .full-bu-b {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: lighter;
	border-radius: 5px;
	border: 1px solid rgba(182, 181, 181, 0.849);
	padding: 5px 17px;
	margin-left: 5px;
	background-color: transparent;
}

button, .full-bu-b:hover {
	background-color: #c2be5c;
}

.tablinks {
	margin: 0px;
}
</style>

<script>
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
		
		$(".qnaRow").click(function() {
			$(this).closest("tr").next().toggle();
			$(this).closest("tr").next().next().toggle();
			$(this).closest("tr").next().next().next().toggle();
			
		});	
	});
</script>
</head>

<body>
	<div id="headerMain"></div>
	<br>
	<hr style="border: 0.2px solid rgb(236, 236, 236);">
	<div style="width: 1300px; margin: 0 auto;">
		<div class="main_layout" style="text-align: center;">
			<div
				style="text-align: center; margin-bottom: 50px; margin-top: 50px;">
				<span
					style="font-family: 'Sunflower', sans-serif; font-size: 4rem; font-weight: bold;">자주하는
					질문</span>
			</div>
			<div style="width: 1200px;">

				<ul class="amount">

					<c:forEach items="${requestScope.tqList }" var="topqna" begin="0"
						end="4">
						<li class="liactive1"
							onclick="onDisplay(this , ${topqna.topQnaNo})">
							<div class="contents1">${topqna.topQnaTitle}</div>
							<div class="result">${topqna.topQnaContent}</div>
						</li>
					</c:forEach>

				</ul>

			</div>
			<div id="content" style="display: none;">

				<form action="/top/qna/update" method="post" id="modifyForm">
					<input type="hidden" name="topQnaNo" id="topQnaNo" value="1">
					<div class="mb-3">
						<input class="form-control" name="topQnaTitle" id="topQnaTitle"
							value="" disabled style="border: none; background: transparent;">
					</div>
					<hr>
					<div class="mb-3">
						<textarea class="form-control" name="topQnaContents"
							id="topQnaContents" rows="5" disabled>
                </textarea>
					</div>
				</form>
				<c:if test="${user.userAdmin eq 'Y' }">
					<div style="display: show;">
						<button onclick="topQnaModify()">수정</button>
						<button onclick="topQnaModifySave()">저장</button>
						<button onclick="topQnaModifyCancle()">취소</button>
					</div>
				</c:if>
			</div>
			<div style="width: 1200px;">
				<ul class="amount">

					<c:forEach items="${requestScope.tqList }" var="topqna" begin="5"
						end="9">
						<li class="liactive2"
							onclick="onDisplay(this , ${topqna.topQnaNo})">
							<div class="contents1">${topqna.topQnaTitle}</div>
							<div class="result">${topqna.topQnaContent}</div>
						</li>
					</c:forEach>

				</ul>
			</div>
		</div>

		<div class="main_layout" style="margin-top: 50px;">
			<div class="tab" style="background-color: white; border:"0";>
				<button class="tablinks">문의등록</button>
				<button class="tablinks">문의내역</button>
			</div>
			<div id="qnawrite" class="tabcontent">

				<h3 class="boxTitle">
					<span
						style="text-align: center; font-family: 'Sunflower', sans-serif; font-size: 3rem; font-weight: bold;">문의사항</span>
				</h3>

				<div id="member" class="qnawrite_content" style="display: block;">
					<form action="/qna/write" method="post">
						<label class="id ePlaceholder"> <input name="qnaTitle"
							class="inputTypeText" placeholder="제목입력" value="" type="text"
							style="margin-top: 50px; width: 1200px; height: 30px; border-top: none; border-left: none; border-right: none; font-size: 2rem; font-weight: bold;">
						</label><br> <label class="password ePlaceholder"> <textarea
								name="qnaContent" placeholder="내용을 입력해주세요."
								style="resize: none; width: 1200px; height: 400px; font-size: 2rem; border-left: none; border-right: none; border-top: none; outline: none;"></textarea>
						</label>

						<div class="write-btn" style="text-align: center;">
							<input class="full-bu-b" type="submit" value="등록"
								style="width: 100px"> <input class="full-bu-b"
								type="reset" value="취소" style="width: 100px">
						</div>
					</form>
				</div>
			</div>
			<div id="qnalist" class="tabcontent"
				style="display: none; width: 1260px;">
				<div style="text-align: center;">
					<span
						style="font-family: 'Sunflower', sans-serif; font-size: 3rem; font-weight: bold;">문의내역</span>
				</div>
				<c:if test="${!empty qList }">
					<table class="table table-hover"
						style="margin-top: 50px; text-align: center;">



						<thead>
							<tr>
								<th scope="col" style="width: 450px; text-align: center;">제목</th>
								<th scope="col" style="text-align: center;">문의접수번호</th>
								<th scope="col" style="text-align: center;">등록일</th>
								<th scope="col" style="text-align: center;">상태</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.qList }" var="qOne"
							varStatus="index">

							<tbody>
								<tr class="qnaRow">
									<td scope="row">${qOne.qnaTitle }</td>
									<td scope="row">${qOne.qnaNo }</td>
									<td scope="row">${qOne.qnaWriteDate }</td>
									<td scope="row">${qOne.qnaStatus }</td>
								</tr>

								<tr id="qnaDetail" style="display: none;">

									<td colspan="4" style="text-align: center;">
										<div style="display: inline-block;">
											<div
												style="width: 750px; border: 2px solid #ccc; border-bottom: dotted #ccc;">문의
												내용</div>
											<div
												style="width: 750px; border: 2px solid #ccc; border-top: none">
												<br>${qOne.qnaContent }</div>
										</div>
									</td>

								</tr>
								<c:if
									test="${qOne.replyContent ne null and qOne.replyContent ne '' }">
									<tr style="display: none;">
										<td colspan="4" style="text-align: center;">
											<div style="display: inline-block;">
												<div
													style="width: 750px; border: 2px solid #ccc; border-bottom: dotted #ccc;">문의
													답변</div>
												<div
													style="width: 750px; border: 2px solid #ccc; border-top: none">
													<br>${qOne.replyContent }</div>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</c:forEach>
					</table>
				</c:if>
				<div>
					<c:if test="${empty qList }">
						<table class="table table-hover">

							<thead>
								<tr>
									<th scope="col" style="width: 450px; text-align: center;">제목</th>
									<th scope="col" style="text-align: center;">문의접수번호</th>
									<th scope="col" style="text-align: center;">등록일</th>
									<th scope="col" style="text-align: center;">상태</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4" style="text-align: center;"><img
										style="margin-left: 50px;" src="/upload/story_none.png" alt="">
									<td>
								</tr>
							</tbody>
						</table>
					</c:if>

				</div>
			</div>
		</div>

		<div id="footerMain"></div>

		<script type="text/javascript">
    
    function onDisplay(obj , no){

        let title = $(obj).children().eq(0).text();
        let contents = $(obj).children().eq(1).text();
        console.log(title);
        console.log(contents);
        $("#topQnaNo").val(no);
        $("#topQnaTitle").val(title);
        $("#topQnaContents").val(contents);
        
    }
    
    //topQna 1번째 array click event 
	$(".liactive1").click(function(event) {
        let selectedIndex = $(this).index();
	   
		$(".liactive1").each(function(index, item) {
			
	    	if (index != selectedIndex) {
		  		$(item).removeClass("active");
		  		
		  		$(".liactive2").each(function(index, item) {
				  	$(item).removeClass("active");
				 }); 
		  	} else {
		  		if($(item).hasClass('active')){
		  			$(item).removeClass("active");
		  			$("#content").hide();
		  		}else{
			    	$(item).addClass('active');
			    	$("#content").show();
		  		}
		  }
	
		});
		
	 });
	    
	//topQna 2번째 array click event 
	$(".liactive2").click(function(event) {
	    let selectedIndex = $(this).index();
	  
		$(".liactive2").each(function(index, item) {
		   	if (index != selectedIndex) {
		  		$(item).removeClass("active");
		  		
			  		$(".liactive1").each(function(index, item) {
				  	$(item).removeClass("active");
				 }); 
		  	} else {
		  		if($(item).hasClass('active')){
		  			$(item).removeClass("active");
		  			$("#content").hide();
		  		}else{
			    	$(item).addClass('active');
			    	$("#content").show();
		  		}
		  }
		
		});
	
	});
	function topQnaModify() {

		  document.getElementById("topQnaTitle").focus();

		}
		function topQnaModifySave() {

		  document.getElementById("topQnaTitle").blur();

		}   
    function topQnaModify(){
        $("#topQnaTitle").attr('disabled', false);
        $("#topQnaContents").attr('disabled', false);
        $("#topQnaTitle").focus();
		
    }
    function topQnaModifySave(){
        $("#modifyForm").submit();
        $("#topQnaTitle").blur();

    }
    function topQnaModifyCancle(){
        $("#topQnaTitle").attr('disabled', true);
        $("#topQnaContents").attr('disabled', true);
        $("#topQnaTitle").blur();

    }
     function qnaList(){
        $("#qnaListbtn").show();

    }
    function qnaCreate(){
        $("#qnaCreatebtn").show();

    } 
    $(".tablinks:eq(0)").addClass("active");

    $("#qnawrite").css('display', 'block');
    $(".tablinks").click(function(event) {
        let selectedIndex = $(this).index();
        console.log(selectedIndex);
        $(".tabcontent").each(function(index, item) {
            if (index != selectedIndex) {
                $(item).css('display', 'none');
            } else {
                $(item).css('display', 'block');
            }

        });
        $(".tablinks").each(function(index, item) {
            if (index != selectedIndex) {
                $(item).removeClass("active");

            } else {

                $(this).addClass('active');
            }

        });
    
    	
    });
    
   
//     $(".qnaRow").click(function(event){
//     let selectedIndex = $(this).index();
//     $("#qnaNo").val(selectedIndex);
   // $("#qnaDetailRequestForm").submit();


    //조회 
//     $(this).next().show();
//     $(this).next().next().show();
//     $("#qnaDetail").show();
//  });
    </script>
</body>
</html>