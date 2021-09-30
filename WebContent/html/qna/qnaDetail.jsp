<%@page import="qna.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%Qna qnaOne = (Qna)request.getAttribute("qnaOne"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>제목 : <%=qnaOne.getQnaTitle() %></h2>
<h6>글 내용 : <%=qnaOne.getQnaContent() %></h6>
</body>
</html>