<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@page import="com.mytrip.diner.service.DinerViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 수정</title>
<script type="text/javascript">
// $(document).ready(function(){
// 	$("select option[value=값]").attr("selected","selected");
// }
$(function() {
	$("#cancelBtn").click(function(){
		history.back();
	});
	//select box에 원래 있던 값을 넣어서 selected
	$("#area").val(${vo.area }).prop("selected", true);
	$("#kind").val(${vo.kind }).prop("selected", true);
});
</script>
</head>
<body>
	<div class="container">
		<h1>맛집 수정</h1>
		<form action="update.do" method="post" enctype="multipart/form-data">
			<input name="page" type="hidden" value="${param.page }">
			<input name="perPageNum" type="hidden" value="${param.perPageNum }">
			<input name="key" type="hidden" value="${param.key }">
			<input name="word" type="hidden" value="${param.word }">
			<input name="area" type="hidden" value="${param.area }">
			<input name="kind" type="hidden" value="${param.kind }">
			<input name="no" type="hidden" value="${vo.no }">
			<table class="table">
				<tr>
					<th>가게명(*)</th>
					<td><input name="name" class="form-control" value="${vo.name }" required="required"></td>
				</tr>
				<tr>
					<th>지역(*)</th>
					<td>
						<select name="area" id="area" required="required">
<%-- 						 ${vo.area == '7' ? 'selected="selected"' : ''  --%>
							<option value="1">서울특별시</option>
							<option value="2" >경기도</option>
							<option value="3">강원도</option>
							<option value="4">충청북도</option>
							<option value="5">충청남도</option>
							<option value="6">전라북도</option>
							<option value="7">전라남도</option>
							<option value="8">경상북도</option>
							<option value="9">경상남도</option>
							<option value="10">제주도</option>
							<option value="11">부산특별시</option>
							<option value="12">대구광역시</option>
							<option value="13">인천광역시</option>
							<option value="14">광주광역시</option>
							<option value="15">대전광역시</option>
							<option value="16">울산광역시</option>
							<option value="17">세종특별시</option>
							<option value="99">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>음식 종류(*)</th>
					<td>
						<select name="kind" id="kind" required="required">
							<option value="1">한식</option>
							<option value="2">양식</option>
							<option value="3">중식</option>
							<option value="4">일식</option>
							<option value="5">향토음식</option>
							<option value="6">카페</option>
							<option value="99">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>주소(*)</th>
					<td><input name="address" class="form-control" value="${vo.address }" required="required"></td>
				</tr>
				<tr>
					<th>연락처(*)</th>
					<td><input name="tel" class="form-control" value="${vo.tel }" required="required"></td>
				</tr>
				<tr>
					<th>한줄소개(*)</th>
					<td><input name="introduce" class="form-control"value="${vo.introduce }" required="required"></td>
				</tr>
				<tr>
					<th>메인 이미지(*)</th>
					<td><input name="mainImage" class="form-control" type="file" value="${vo.mainImage }" required="required"></td>
				</tr>
				<tr>
					<th>내용(*)</th>
					<td><textarea rows="7" name="content" class="form-control" required="required">${vo.content }</textarea></td>
				</tr>
				<tr>
					<th>서브 이미지</th>
					<td><input name="subImage" class="form-control" type="file" value="${vo.subImage }"></td>
				</tr>
				<tr>
					<th>해시태그</th>
					<td><input name="tag" class="form-control" value="${vo.tag }"></td>
					<!-- **2. 여러개가 등어가도록 설정 -->
				</tr>				
				<tr>
					<th>영업시간</th>
					<td><input name="openTime" class="form-control" value="${vo.openTime }"></td>
					<!-- **3. 시간 입력 구현 -->
				</tr>
				<tr>
					<th>휴업일</th>
					<td><input name="restDay" class="form-control" value="${vo.restDay }"></td>
				</tr>
			</table>
			<button type="submit">수정완료</button>
			<button type="reset">새로입력</button>
			<button type="button" id="cancelBtn">취소</button>
		</form>
	</div>	
</body>
</html>