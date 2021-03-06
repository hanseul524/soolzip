<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>레시피 수정</title>
<link rel="stylesheet" href="../../css/comm.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.filebox label {
	display: block;
	padding: .5em .75em;
	color: #fff;
	font-size: inherit;
	line-height: normal;
	background-color: #918c00;
	cursor: pointer;
	border: 1px solid #918c00;
	border-radius: .25em;
}

.filebox input[type="file"] {
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

img:hover{
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
  width: 230px;
  height: 35px;
  margin: 3px;
  border: 1px solid rgb(219, 219, 219);
  border-radius: 5px;
  background-color: rgb(246, 246, 246);
}
table td input:focus {
  outline: none;
  background-color: rgba(246, 246, 246, 0.856);
}
p {
  font-weight: normal;
  padding-right: 20px;
  width: 155px;
}
table {
  border: 1px solid lightgrey;
  border-radius: 30px;
  padding: 20px;
}
textarea {
  resize: none;
  vertical-align:text-bottom;
  width: 400px;
  height: 130px;
  border: 1px solid rgb(219, 219, 219);
  border-radius: 5px;
  background-color: rgb(246, 246, 246);
}
textarea:focus {
  outline: none;
}
select {
  width: 80px;
  height: 35px;
  border: 1px solid rgb(219, 219, 219);
  border-radius: 5px;
  background-color: rgb(246, 246, 246);
}
select:focus {
  outline: none;
}
button {
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: lighter;
  border-radius: 5px;
  border: 1px solid rgba(182, 181, 181, 0.849);
  padding: 5px 17px;
  margin-left: 5px;
  background-color:transparent;
}
button:hover {
  background-color: #c2be5c;
}
#buttonArea input {
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: lighter;
  border-radius: 5px;
  border: 1px solid rgba(182, 181, 181, 0.849);
  padding: 5px 17px;
  margin-left: 5px;
  margin-top: 10px;
  background-color:transparent;
}
#buttonArea input:hover {
  background-color: #c2be5c;
}
span {
  font-size: 12px;
  font-weight: nomal;
}
</style>
<script>
	$(document).ready(function() {
		$("#headerMain").load("/html/comm/header.jsp");
		$("#footerMain").load("/html/comm/footer.html");
	});
	
	//선택 input:file 태그 연결
	function fnFileChange(input){
		$(input).closest('div').find('input:file').click();
	}
	
	//파일읽어서 등록이미지 뿌려줌
	function readURL(input, id){
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#" + id).attr('src', e.target.result); 
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	
	//재료 추가부분
	function addRow1() {
		//현재 총 재료 개수 구하기
		var size = $("#ingredient").find('div').length + 1;
		
		//추가할 html 태그 만들기
		//추가할 부분 
		var addHtml = "<div id=\"" + "ingredient" + size + "\" >";
		addHtml += "<input type=\"hidden\" name=\"ingrNo"+ size +"\">";
		addHtml += "<input type=\"text\" name=\"ingredient-name\" placeholder=\"재료명을 입력해주세요.\" />&nbsp;";
		addHtml += "<input type=\"text\" name=\"ingredient-gram\" placeholder=\"예) 300ml\" />&nbsp;";
		addHtml += "<button type=\"button\" onclick=\"rmRow1(this)\" />삭제</button>";
		addHtml += "</div>";
		$("#ingredient").append(addHtml);
	}
	
	//재료 추가부분 삭제
	function rmRow1(id, order){
		if(order != undefined){
			if($("input[name='rmIngredientId']").val() == ""){
				$("input[name='rmIngredientId']").val(order);
			}else{
				$("input[name='rmIngredientId']").val($("input[name='rmIngredientId']").val() + ',' + order);
			}	
		}
		$(id).closest('div').remove();
		$("input[name^=ingrNo]").each(function(i,v){
			$(this).attr("name", "ingrNo"+(i+1));
		});
	}
	
	//제조과정 추가부분
	function addRow2() {
		
		//현재 총 제고과정 개수 구하기
		var size = $("#process").find('div').length +1;
		
		//추가할 html 태그 만들기
		
		var addHtml = "<div id=\"process" + size + "\" style=\"height: 150px; margin-bottom: 1em;\">";
		addHtml += "<input type=\"hidden\" name=\"fileNo" + size + "\" />";
		addHtml += "<input type=\"hidden\" name=\"makeNo" + size + "\" />";
		addHtml += "<b> STEP" + size +"</b>&nbsp;";
		addHtml += "<textarea placeholder=\"내용을 입력해주세요.\" name=\"fileContents\" style=\"resize:none;vertical-align: middle;\"></textarea>&nbsp;";	
		addHtml += "<input style=\"display: none;\" type=\"file\" name=\"processFile"+size+"\" accept=\"image/*\" onchange=\"readURL(this, 'processImg"+size+"')\" />&nbsp;";
		addHtml += "<img id=\"processImg"+size+"\" style=\"width: 150px; height: 150px; vertical-align: middle; margin-left: 30px;\" src=\"/img/recipephoto.png\" onclick=\"fnFileChange(this);\" > &nbsp;";
		addHtml += "<button type=\"button\" onclick=\"rmRow2(this)\" >삭제</button>";
		addHtml += "</div>";
		$("#process").append(addHtml);
	}
	
	//제조과정 삭제부분
	function rmRow2(id, order){
		if(order != undefined){
			if($("input[name='rmMakeId']").val() == ""){
				$("input[name='rmMakeId']").val(order);
			}else{
				$("input[name='rmMakeId']").val($("input[name='rmMakeId']").val() + ',' + order);
			}	
		}
		//삭제버튼 클릭 시 영역의 부모태그 삭제
		$(id).closest('div').remove();
		//현재 제조과정 개수만큼 반목문을 돌려서 step명 재조정
		$("#process").find('div').find('b').each(function(i, v){ //i = index, v = value
			var stepLevel = i + 1;
			$(this).html("STEP" + stepLevel);
		});
		
		$("#process").find('input:file').each(function(i, v){
			var stepLevel = i + 1;
			$(this).attr("onchange", "readURL(this, 'processImg"+ stepLevel +"')");
		});
		
		$("#process").find('img').each(function(i, v){
			var stepLevel = i + 1;
			$(this).attr("id", "processImg"+ stepLevel);
		});
		
		$("input[name^=processFile]").each(function(i, v){
			var level = i+1;
			$(this).attr("name", "processFile"+ level);
		});
		$("input[name^=fileNo]").each(function(i, v){
			var level = i+1;
			$(this).attr("name", "fileNo"+ level);
		});
		$("input[name^=makeNo]").each(function(i, v){
			var level = i+1;
			$(this).attr("name", "makeNo"+ level);
		});
	}
	
	//제출 시 필수체크
	function fnValidate(){
		//무엇이 값이 ''일때
// 		var el = $("#test");
// 		if(el.val() == ''){
// 			el.focus();
// 			return true;
// 		}
		
		
		for(var i = 0; i < $("input[name='ingredient-name']").length; i++){
			if(i < 3){
				if($("input[name='ingredient-name']").get(i).value == ""){
					alert("추가된 재료의 재료명을 입력하세요.");
					$("input[name='ingredient-name']").get(i).focus();
					return true;
				}
				if($("input[name='ingredient-gram']").get(i).value == ""){
					$("input[name='ingredient-gram']").get(i).focus();
					alert("추가된 재료의 그램을 입력하세요.");
					return true;
				}				
			}
		}
		
 		return false;
	}
	
	//저장
	function fnSave(type){
		//제출일 경우 필수값 체크필요
		if(type=='A'){
			$("input[name='recipeSaveState']").val("1"); 
			if(fnValidate()) return;
		}
		var ingredientName = [];
		var ingredientGram = [];
		for(var i = 0; i < $("input[name='ingredient-name']").length; i++){
			if(i > 2){
				if($("input[name='ingredient-name']").get(i).value == ""){
					alert("추가된 재료의 재료명을 입력하세요.");
					$("input[name='ingredient-name']").get(i).focus();
					return;
				}
				if($("input[name='ingredient-gram']").get(i).value == ""){
					$("input[name='ingredient-gram']").get(i).focus();
					alert("추가된 재료의 그램을 입력하세요.");
					return;
				}				
			}
			ingredientName.push($("input[name='ingredient-name']").get(i).value);
			ingredientGram.push($("input[name='ingredient-gram']").get(i).value);
		}
		
		$("input[name='ingredientName']").val(ingredientName);
		$("input[name='ingredientGram']").val(ingredientGram);
		
		$("#test").submit();
	}
</script>
</head>
<body>
	<div id="headerMain"></div>
	 <br>
      <hr style="border: 0.2px solid rgb(236, 236, 236);">
	<form action="/recipe/modify" method="post" id="test" style="margin: 0 auto; width: 1000px;" enctype="multipart/form-data">
		<input type="hidden" name ="rmMakeId" />
		<input type="hidden" name="rmIngredientId" />
		<input type="hidden" name="recipeNo" value="${recipeOne.recipeNo }">
		<input type="hidden" name="mainFileNo" value="${recipeOne.fileNo }">
		<input type="hidden" name="ingredientName" />
		<input type="hidden" name="ingredientGram" />
		<input type="hidden" name="recipeSaveState" value="0"/>
		<h1 align="center" style="margin-top: 2em; margin-bottom: 1em;">레시피 수정</h1>
		<table>

			<thead></thead>
			<tbody>
				<tr>
					<th><p>주류명</p></th>
					<td><input type="text" placeholder="예) 고진감래주" name="recipe-title" value="${recipeOne.recipeTitle }" /></td>
					<td rowspan="3" width="200px">
						<input id="mainFile" name="mainFile" type="file" style="display: none;" onchange="readURL(this, 'mainImg')">
						<img id="mainImg" style="width: 150px; height: 150px;"
						src="<c:if test="${empty recipeOne.fileName }">/img/recipephoto.png</c:if><c:if test="${not empty recipeOne.fileName }">/upload/${recipeOne.fileName }</c:if>"
						onclick="javascript: $('#mainFile').click();">
						<br/>
						<center><p>대표사진</p></center>
					</td>
					
				</tr>
				<tr>
					<th><p> 레시피 한줄설명</p></th>
					<td><input type="text" placeholder="내용을 입력하세요." name="recipe-contents" value="${recipeOne.recipeContents }"/></td>
				</tr>

				<tr>
					<th><p>메인 주류 정보</p></th>
					<td>
						<select name="recipe-mainDrink" id="" value="${recipeOne.recipeMainDrink }">
							<option value="소주">소주</option>
							<option value="맥주">맥주</option>
							<option value="막걸리">막걸리</option>
							<option value="와인">와인</option>
							<option value="보드카">보드카</option>
							<option value="럼">럼</option>
							<option value="진">진</option>
							<option value="데킬라">데킬라</option>
							<option value="위스키">위스키</option>
							<option value="브랜디">브랜디</option>
							<option value="전통주">전통주</option>
							<option value="기타">기타</option>
						</select>
						<select name="recipe-alcohol" id="" value="${recipeOne.recipeAlcohol }">
							<option value="5">0~5</option>
							<option value="10">5~10</option>
							<option value="15">10~15</option>
							<option value="20">15이상</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th><p>재료</p></th>
					<td>
						<div id="ingredient">
							<!-- if iList 사이즈가 0이다 -->
							<c:if test="${fn:length(iList) > 0 }">
								<c:forEach items="${iList }" var="ingr" varStatus="index">
									<div id="ingredient${index.count }">
											<input type="hidden" name="ingrNo${index.count }" value="${ingr.ingredientNo }">
											<input type="text" name="ingredient-name" placeholder="재료명을 입력해주세요." value="${ingr.ingredientName }" />
											<input type="text" name="ingredient-gram" placeholder="예) 300ml"  value="${ingr.ingredientGram }" />
											<c:if test="${index.count>3 }">
												<button type="button" onclick="rmRow1(this, <c:out value='${ingr.ingredientNo }' />)" />삭제</button>
											</c:if>
									</div>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(iList) eq 0 }">
								<div id="ingredient1">
									<input type="hidden" name="ingrNo1" >
									<input type="text" name="ingredient-name" placeholder="재료명을 입력해주세요." />
									<input type="text" name="ingredient-gram" placeholder="예) 300ml" />
								</div>
								<div id="ingredient2">
									<input type="hidden" name="ingrNo2" >
									<input type="text" name="ingredient-name" placeholder="재료명을 입력해주세요." />
									<input type="text" name="ingredient-gram" placeholder="예) 300ml" />
								</div>
								<div id="ingredient3">
									<input type="hidden" name="ingrNo3" >
									<input type="text" name="ingredient-name" placeholder="재료명을 입력해주세요." />
									<input type="text" name="ingredient-gram" placeholder="예) 300ml" />
								</div>
							</c:if>
							
						</div>
						<button type="button" onclick='addRow1()'>재료추가</button>
					</td>
				</tr>
				<tr>
					<th><p>제조과정</p></th>	
					<td colspan="2">
						<div id="process">
							<c:forEach items="${mList }" var="mkProcess" varStatus="index">
								<div id="process${index.count }" style="height: 150px; margin-bottom: 1em;">
									<b>STEP${index.count }</b>
									<input type="hidden" name="fileNo${index.count }" value="${mkProcess.fileNo }">
									<input type="hidden" name="makeNo${index.count }" value="${mkProcess.makeNo }">
									<textarea placeholder="내용을 입력해주세요." name="fileContents" style="resize:none;vertical-align: middle;">${mkProcess.makeContents }</textarea>
									<input style="display:none;" type="file" name="processFile${index.count }" accept="image/*" onchange="readURL(this, 'processImg${index.count }')" />
									<img id="processImg${index.count }" style="width: 150px; height: 150px; vertical-align: middle; margin-left: 30px;" 
									src="<c:if test="${empty mkProcess.fileNo }">/img/recipephoto.png</c:if><c:if test="${not empty mkProcess.fileNo }">/upload/${mkProcess.fileName }</c:if>"
									onclick="fnFileChange(this);">
									<c:if test="${index.count>2 }">
										<button type="button" onclick="rmRow2(this, <c:out value='${mkProcess.makeNo }' />)" />삭제</button>
									</c:if>
								</div>
							</c:forEach>
						</div>
						<button type="button" style="margin-top: 1em;"  onclick='addRow2()'>순서추가</button>
					</td>
				</tr>
				<tr>
					<th><p>태그 추가</p></th>
					<td><input type="text" name="recipe-tag" placeholder="태그를 추가해주세요." style="width: 400px;" value="${recipeOne.recipeTag }"/></td>
				</tr>
			</tbody>
		</table>
		<div id="buttonArea" align="center">
			<input type="button" onclick="fnSave('A');" value="저장">
			<input type="button" onclick="fnSave('B');" value="비공개 저장">
			<!-- <input type="submit" value="저장후 공개" id="test1"> -->
		</div>
	</form>
	
	<script>
		
	</script>
	<div id="footerMain"></div>
	<!-- <script>
		$(function() {
			var modify = $("#test1");

			modify.click(function() {
				const forElement = $("#test");
				forElement.attr("action", "./test3.html");
			});
		});
	</script> -->
</body>
</html>