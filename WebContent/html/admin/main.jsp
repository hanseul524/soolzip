<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminpage</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/main.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
$(document).ready(function () {
	$("#headerMain").load("/html/comm/header.jsp");
	$("#footerMain").load("/html/comm/footer.html");
	
	$('div > ul > li').click(function() {
        if ( $(this).hasClass('active') ) {
          $(this).find(' > ul').stop().slideUp(300);
          $(this).removeClass('active');
        }
        else {
          $(this).find(' > ul').stop().slideDown(300);
          $(this).addClass('active');
        }
	});
});
</script>
</head>
<body>
<div id="headerMain"></div>
  <div id="contnet" class="contents">
    <div id="menu-bar">
      <br>
      <p style="text-align: center;">관리자님, <br> 안녕하세요.</p>
      <br>
      <ul>
        <li>
          <i class="fas fa-list"></i><a href="#">문의사항</a>
            <ul>
                <li><a href="main.jsp">문의사항 관리</a></li>
                <li><a href="#">답변</a></li>
            </ul>
        </li>
        <li>
          <i class="far fa-user-circle"></i><a href="/user/list">회원 관리</a>
        </li>
        <li>
          <i class="fas fa-user-circle"></i><a href="/admin/list">관리자 관리</a>
        </li>
    </ul>
    </div>
  </div>
  <div class="menu-contents">
    <div id="contents-header">
      <h2>문의사항 관리</h2>
    </div>
    <div id="contents-list">
    <table class="table table-hover">
      <tr>
        <th>접수번호</th>
        <th>제목</th>
        <th>작성자 ID</th>
        <th>등록일</th>
        <th>상태</th>
      </tr>
      <tr>
        <td>001</td>
        <td>회원탈퇴는 어떻게 하나요?</td>
        <td>user01</td>
        <td>2021-09-18</td>
        <td>미해결</td>
      </tr>
      <tr>
        <td>002</td>
        <td>문의사항 페이지에서 리스트 넘겨받기</td>
        <td>user02</td>
        <td>2021-09-18</td>
        <td>미해결</td>
      </tr>
      <tr>
        <td>003</td>
        <td>부트스트랩 써서 스타일 변경하기</td>
        <td>user03</td>
        <td>2021-09-18</td>
        <td>해결</td>
      </tr>
      <tr>
        <td>004</td>
        <td>네번째 문의사항</td>
        <td>user04</td>
        <td>2021-09-18</td>
        <td>미해결</td>
      </tr>
      <tr>
        <td>005</td>
        <td>다섯번째 문의사항</td>
        <td>user05</td>
        <td>2121-09-18</td>
        <td>미해결</td>
      </tr>
    </table>
    <hr>
    <div class="text-center"></div>
      <ul class="pagination">
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
      </ul>
    </div>
  </div>
</div>
<div id="footerMain"></div> 
</body>
</html>