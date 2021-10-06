<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 등록</title>
    <link rel="stylesheet" href="../../css/story/storyRegister.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
       <style>
        img:hover {
            cursor: pointer;
        }

        table tr {
            line-height: 3em;
        }

        table th {
            font-size: 20px;
            text-align: left;
            margin-right: 30px;
        }

        table td input {
            width: 170px;
            height: 30px;
        }
    </style>
    <script>
        //선택 input:file 태그 연결
        function fnFileChange(input) {
            $(input).closest('div').find('input:file').click();
        }

        //파일읽어서 등록이미지 뿌려주기
        function readURL(input, id) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $("#" + id).attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        function fnValidate() {
            return false;
        }
        //등록
        function fnSave(type) {
            //제출일 경우 필수값 체크필요
            if (type == 'A') {
                $("input[name='StorySave']").val("1");
                $("input[name='storyNo']").val();
                if (fnValidate()) return;
            }
            $("#test").submit();
        }
    </script>
    <script>
        $(document).ready(function () {
        	$("#headerMain").load("/html/comm/header.jsp");
            $("#footerMain").load("/html/comm/footer.html");
        });
    </script>
</head>

<body>
        <div id="headerMain"></div>
         <br>
      <hr style="border: 0.2px solid rgb(236, 236, 236);">
    <div class="container">
        <div class="contents">
            <form action="/story/modify" method="post" id="test" style="margin: 0 auto; width: 1000px;"enctype="multipart/form-data">
                <input type="hidden" name="storySave" value="0" />
                <input type="hidden" name="storyNo" value="${storyOne.storyNo }" /> 
                <input type="hidden" name="fileNo" value="${storyOne.fileNo }" /> 
                <h1 class="h1text" align="center" style="margin-top: 2em;">스토리 수정</h1>
                <table style="margin-left: 250px;">
                    <thead></thead>
                    <tbody>
                        <tr>
                            <div>
                                <td width="200px" align="center">
                                    <div class="img_title">
                                        <input id="storyFile" name="storyFile" type="file" style="display: none;"onchange="readURL(this, 'mainImg')">
                                        <img class="preview" id="mainImg" src="
                                        <c:if test="${empty storyOne.fileName }">/img/photoImg.png</c:if><c:if test="${not empty storyOne.fileName }"></c:if>/story-upload/${storyOne.fileName}"onclick="javascript: $('#storyFile').click();">
                                    </div>
                                    <center><b>POTO</b></center>
                                </td>
                            </div>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="process">
                                    <div id="process1" style="height: 150px; margin-bottom: 1em;">
                                        <b>내용</b>
                                        <textarea placeholder="내용을 입력해주세요." name="story-contents" style="resize:none;vertical-align: middle; width: 500px; height: 150px;" value="${storyOne.storyContents }"></textarea>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="tag">
                    <h4>태그</h4>
                    <input type="text" name="story-tag" placeholder="태그를 입력해주세요.(#ㅈㅈ)"style="width: 300px; height: 40px;" value="${storyOne.storyTag }" />
                </div>
                <div id="buttonArea" align="center">
                    <!-- <input type="button" value="삭제"> -->
                    <input class="btn_color" type="reset" value="취소">
                    <input class="btn_color" type="button" onclick="fnSave('A');" value="등록">
                </div>
            </form>
        </div>
    </div>
    <div id="footerMain"></div>
</body>
</html>