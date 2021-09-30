<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    <%@page import="qna.model.vo.Qna"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%
    List<Qna> qList = (List<Qna>)request.getAttribute("qList");
    %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>semiProjectMain</title>
	   <link rel="stylesheet" href="../../css/qna.css">
	   <link rel="stylesheet" href="../../css/comm.css">
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
	   <link rel="stylesheet" href="css/comm.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(document).ready(function (){
		$("#headerMain").load("/html/comm/header.html");
		$("#footerMain").load("/html/comm/footer.html");
		
		//ajax qna list call
		
		
		
	});
</script>
<body>
	<div id="headerMain"></div>
    <!-- contents -->
    <div class="main_layout">
    <div id="content" class="contents" >
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
                        <input size="62" name="qnaTitle"  class="inputTypeText" placeholder="제목입력" value="" type="text">
                    </label><br>
                    <label class="password ePlaceholder">
                        <p> <textarea name="qnaContent" rows="15" cols="64" placeholder="내용을 입력해주세요." style="resize:none;"></textarea></p>
                    </label>
                    
                    <p class="login-btn">
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
		
    			<%for(Qna qOne : qList){ %>		
  				   <tbody>
	                    <tr class="qnaRow">
 	                        <td scope="row"><a href="/qna/detail?qnaNo=<%=qOne.getQnaNo()%>"><%=qOne.getQnaTitle()%></a></td> 
	                        <td scope="row"><%=qOne.getQnaNo()%></td> 
	                        <td scope="row"><%=qOne.getQnaWriteDate()%></td>
	                        <td scope="row"><%=qOne.getQnaStatus()%></td>
	                    </tr>
		
		
	                </tbody>
	               <%} %>
	             
		
	            </table>
            </div>
        </div>
<!--     </div> -->
<!--     </div> -->
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
       // $("#modifyForm").submit();
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
    });
    $(".qnaRow").click(function(event){
    let selectedIndex = $(this).index();
    $("#qnaNo").val(selectedIndex);
   // $("#qnaDetailRequestForm").submit();


    //조회 
    $("#qnaDetail").show();
 });
    </script>
</body>
</html>