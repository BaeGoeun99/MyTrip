<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageObject2" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>맛집 리스트</title>
<style>
	.dataRow:hover {
		background: #ffff00;
		cursor: pointer;
	}
	.namePlace {
		font-size: 15px;
		font-weight: bold; 
	}
	.areaKindPlace {
		font-size: 12px;		 
	}
	.introPlace {
		color: #808080;
		font-weight: bold; 
	}
</style>

<script type="text/javascript">
$(function() {
	$(".dataRow").click(function(){
		var no = $(this).data("no");
		location = "view.do?no="+no+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
				+ "&key=${pageObject.key}&word=${pageObject.word}"
				+ "&area=${pageObject.area}&kind=${pageObject.kind}";
	});
	$("#area").val(${pageObject.area }).prop("selected", true);
	$("#kind").val(${pageObject.kind }).prop("selected", true);
// 	${"#test"}.click(funtion(){
// 		alert("test :" + no);
// 	});
});
// areaChange(), kindChange() 만들기 : 드롭박스 선택하면 리스트 새로고침
function areaChange() {
	var select = document.getElementById("area").options[document.getElementById("area").selectedIndex].value;
	if(select != 0) {
		location = "list.do?page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
				+ "&area="+select+"&kind=${pageObject.kind}";
	}
}
function kindChange() {
	var select2 = document.getElementById("kind").options[document.getElementById("kind").selectedIndex].value;
	if(select2 != 0) {
		location = "list.do?page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
				+ "&area=${pageObject.area}"+"&kind="+select2;
	}
}

</script>
</head>
<body>
	<div class="container">
		<h1 style="text-align: center;">MyTrip 추천 맛집 리스트</h1> 
<%-- 		${pageObject.area } // ${pageObject.kind } --%>
		<!-- **1. 가운데 정렬 -->
		<div>			
			<form action="list.do">
				<!-- 페이지 정보를 다시 그대로 보냄 -->
				<input name="page" type="hidden" value="1">
				<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
				
				<!-- 리스트 상단 -->
				<div class="input-group">
					<div class="input-group-addon">
						<select name="area" id="area" onchange="areaChange()">
							<option value="0">지역</option>
							<option value="1">서울특별시</option>
							<option value="2">경기도</option>
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
					</div>
					<div class="input-group-addon">
						<select name="kind" id="kind" onchange="kindChange()" >
							<option value="0">음식종류</option>
							<option value="1">한식</option>
							<option value="2">양식</option>
							<option value="3">중식</option>
							<option value="4">일식</option>
							<option value="5">향토음식</option>
							<option value="6">카페</option>
							<option value="99">기타</option>
						</select>
					</div>
					<span class="input-group-addon">
						<!-- 검색 종류 선택 (pulldown 메뉴 - select) : key -->
						<select name="key">
							<option value="n" ${(pageObject.key == "n")?"selected":"" }>맛집이름</option>
							<option value="a" ${(pageObject.key == "a")?"selected":"" }>위치</option>	
						</select>
					</span>
					<input id="word" type="text" class="form-control" name="word" placeholder="검색할 내용" value="${param.word }">
				    <div class="input-group-btn">
				      <button class="btn btn-default" type="submit">
				        <i class="glyphicon glyphicon-search"></i>
				      </button>
				    </div>
				</div>
			</form>
		</div>
		<div class="row">
		<!-- 이미지 데이터가 있는 만큼 반복처리 -->
			<c:forEach items="${list }" var="vo" varStatus="vs">
			<c:if test="${vs.index != 0 && vs.index % 4 == 0}">
				<!-- 이미지 인덱스가 0보다 크면서 4배의 배수이면 줄바꿈 코드를 추가한다. -->
				${"</div><div class='row'>"}
			</c:if>
			    <div class="col-md-3" >
			      <div class="thumbnail dataRow" data-no = "${vo. no }" >
			          <img src="${vo.mainImage }" alt="${vo.name }" style="width:100%; height: 250px;">
			          <div class="caption">
			            <div class="namePlace">${vo.name }</div>
			            <div class="areaKindPlace">${vo.areaName }>${vo.kindName }</div>
			            <br>
			            <div class="introPlace">${vo.introduce }</div>
			            <div class="container">
				            <button style="font-size:15px">좋아요 <i class="fa fa-thumbs-o-up"></i></button>
				            <button style="font-size:15px">찜하기 <i class="fa fa-bookmark-o"></i></button>
			            </div>
			          </div>
			      </div>
			  	</div>
			</c:forEach>
		</div>
		<!-- 페이징 -->
		<div>
			<pageObject2:pageNav2 listURI="list.do" pageObject2="${pageObject }" query="&key=${pageObject.key }&word=${pageObject.word }"></pageObject2:pageNav2>
		</div>
		<c:if test="${!empty login && login.gradeNo == 9 }">
			<a href="writeForm.do" class="btn btn-default">맛집 등록</a>
		</c:if>
	</div>
</body>
</html>