<%@page import="topqna.model.vo.TopQna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    <%@page import="qna.model.vo.Qna"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%
    List<Qna> qList = (List<Qna>)request.getAttribute("qList");
  List<TopQna> tqList = (List<TopQna>)request.getAttribute("tqList");

    %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>servicecenter</title>
	   <link rel="stylesheet" href="/css/comm.css">
	   <link rel="stylesheet" href="/css/qna/qna.css">
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
	
 
</head>
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
<body>
	
	<div id="headerMain"></div>
    <!-- contents -->
    <hr>
 <div class="main_layout"> 
    <h1 style="text-align: center;">자주하는질문</h1>
    <ul class="amount">
    
    
    
        <c:forEach items ="${requestScope.tqList }" var="topqna"  begin="0" end="4" >
        <li onclick="onDisplay(this , ${topqna.topQnaNo})">
            <div class="contents1"> ${topqna.topQnaTitle} </div>
            <div class="result"> ${topqna.topQnaContent}</div>
        </li> 
      
        </c:forEach>
        
    </ul>
    </div>
    <div  id="content" onclick="onDisplay" style="display: none;"  >

<!--         <span onclick="this.parentElement.style.display='none'"="close">X</span>  -->
		<button onclick="this.parentElement.style.display='none'" id="x-button">X</button> 
        <form action="/top/qna/update" method="post" id="modifyForm">
            <input type="hidden" name="topQnaNo" id="topQnaNo" value="1">
            <div class="mb-3">
                <input  class="form-control" name="topQnaTitle" id="topQnaTitle" value="" disabled >

            </div>
            <hr>
            
            <div class="mb-3">
                <textarea class="form-control" name="topQnaContents" id="topQnaContents" rows="5" disabled >

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
 <ul class="amount">
    
    
    
        <c:forEach items ="${requestScope.tqList }" var="topqna"  begin="5" end="9" >
        <li onclick="onDisplay(this , ${topqna.topQnaNo})">
            <div class="contents1"> ${topqna.topQnaTitle} </div>
            <div class="result"> ${topqna.topQnaContent}</div>
        </li> 
      
        </c:forEach>
    </ul>
<br><br>
<hr>
<div onclick="qnaCreate()" style="display: none; display: inline-block;">
    <form action="/qna/write" method="post">

        <div class="qna"> 
            <h3 class="title">문의사항</h3>  
            <div>
                <fieldset>
                <label class="qna_title">
                    <input id="member_id" name="member_id"  class="inputTypeText" placeholder="제목입력" value="" type="text">
                </label>
                <label class="qna_cintents">
                    <p> <textarea rows="15" cols="64" placeholder="내용을 입력해주세요."></textarea></p>
                </label> <br>
                <p class="qna_btn">
        <input class="btn" type="submit" value="등록">
        <input class="btn" type="reset" value="취소">
    </p>
</fieldset>
    </div>
    </div>

    </form>
</div>
<div>
<!-- <div class="qnaList_div" onclick="qnaList()" style="width: 1000px; display: inline-block; display: none;"> --> 
<!--      <table class="table table-hover"> -->
      	<div id="headerMain"></div>
    <!-- contents -->
    <div class="main_layout">
<!--     <div id="content" class="contents" > -->
    <div class="tab">
        <button class="tablinks" >문의등록</button>
        <button class="tablinks">문의내역</button>
    </div>
 <div id="qnawrite" class="tabcontent">
<!--         <div class="row" style=" margin: 30px 0px 50px 0px; padding: 80px 10px 80px 10px;"> -->
<!--             background-color: #918c00; -->
<!--             <div class="card" > -->
 <h3 class="boxTitle">문의사항</h3>
        <div id="member" class="qnawrite_content" style="display: block;">
            <form action="/qna/write" method="post">
                <fieldset>
              
                    <label class="id ePlaceholder">
                        <input size="62" name="qnaTitle"  class="inputTypeText" placeholder="제목입력" value="" type="text" style="background-color: white;">
                    </label><br>
                    <label class="password ePlaceholder">
                        <p> <textarea name="qnaContent" rows="15" cols="64" placeholder="내용을 입력해주세요." style="resize:none;"></textarea></p>
                    </label>
                    
                    <p class="write-btn">
                        <input class="full-bu-b" type="submit" value="등록">
                        <input class="full-bu-b" type="reset" value="취소">
                    </p>
                
                </fieldset>
            </form>    
        </div>              
        </div>
	 <div id="qnalist" class="tabcontent" style="display: none;>
	            <!-- style="width: 1000px; display: inline-block;" style="display: none;" -->
	           
	            <table class="table table-hover">
	                <h3>문의내역</h3>
	                <thead>
	                <tr>
	                    <th scope="col" style="width: 500px; text-align: center;" >제목</th>
	                    <th scope="col" style="text-align: center;" >문의접수번호</th>
	                    <th scope="col" style="text-align: center;">등록일</th>
	                    <th scope="col" style="text-align: center;">상태</th>
	                </tr>
	                </thead>
		
    			<c:forEach items="${requestScope.qList }" var="qOne" varStatus="index">
    			
  				   <tbody>
	                    <tr class="qnaRow" >
 	                        <td scope="row">${qOne.qnaTitle }</td> 
	                        <td scope="row">${qOne.qnaNo }</td> 
	                        <td scope="row">${qOne.qnaWriteDate }</td>
	                        <td scope="row">${qOne.qnaStatus }</td>
	                    </tr>
	                    
	                    <tr id="qnaDetail" style="display:none;"  >
	                    	<td colspan="4">
	                    	문의 내용 : ${qOne.qnaContent }
	                    	</td>
	                    </tr>
	                   	<c:if test="${qOne.replyContent ne null and qOne.replyContent ne '' }">
		                    <tr style="display:none;">
		                    	<td colspan="4">
		                    	문의 답변 : ${qOne.replyContent } 
		                    	</td>
		                    </tr>
	                   	</c:if>
	                </tbody>
    			</c:forEach>
	            </table>
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
        $("#content").show();
    }
    function topQnaModify(){
        $("#topQnaTitle").attr('disabled', false);
        $("#topQnaContents").attr('disabled', false);
    }
    function topQnaModifySave(){
        $("#modifyForm").submit();
    }
    function topQnaModifyCancle(){
        $("#topQnaTitle").attr('disabled', true);
        $("#topQnaContents").attr('disabled', true);
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
    
   
    $(".qnaRow").click(function(event){
    let selectedIndex = $(this).index();
    $("#qnaNo").val(selectedIndex);
   // $("#qnaDetailRequestForm").submit();


    //조회 
    //$(this).next().show();
    //$(this).next().next().show();
//     $("#qnaDetail").show();
 });
    </script>
</body>
</html>