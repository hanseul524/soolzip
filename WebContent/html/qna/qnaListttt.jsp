
    
<%@page import="java.util.List"%>
<%@page import="qna.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="../../css/qna.css">
  <link rel="stylesheet" href="../../css/comm.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
   

</head>
    <script>
  	$(document).ready(function() {
  		$("#headerMain").load("/html/comm/header.html");
  		$("#footerMain").load("/html/comm/footer.html");
  	});
     </script>
<body>
<div id="headerMain"></div>
<h1>공지사항</h1>
	 <div id="qnalist" class="tabcontent">
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
		
		<c:forEach items="${requestScope.qList }" var="student" varStatus="index">
		 <tbody>
	                    <tr class="qnaRow">
	                        <td scope="row"><a href="/qna/detail?qnaNo=${qOne.getQnaNo}">${qOne.getQnaTitle}</a></td>
	                        <td scope="row">${qOne.getQnaNo}</td>
	                        <td scope="row">${qOne.getQnaWriteDate}</td>
	                        <td scope="row">${qOne.getQnaStatus}</td>
	                    </tr>
	                </tbody>
	                </c:forEach>
	             
		
	            </table>
		<div id="footerMain"></div>
</body>
</html>