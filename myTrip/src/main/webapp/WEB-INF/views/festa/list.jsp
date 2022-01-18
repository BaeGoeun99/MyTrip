<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제 정보</title>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no
				 + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
// 				 + "&key=${pageObject.key}&word=${pageObject.word}";
		
	});
});
</script>

<style type="text/css">
.container{
	width:1150px;
	font-family: 'IBM Plex Sans KR', sans-serif;
}
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
.fst-top{
 border-bottom: 2px solid black;
}
.cld_area{
	width:100%;
	text-align: center;
	margin: 10px 0 10px 0;
	border-bottom: 1px solid #e5e5e5;
}
.cld_area_ul ul{
	list-style: none;
	clear: both;	
}
ul{
	list-style: none;
}
.cld_area_ul li{
	float: none;
    display: inline-block;
    padding: 5px 13px 5px 13px;
    font-size: 16px;
    color: black;
}
.cld_a a{
color: #4d4d4d;
}
p{
	clear: both;
    margin: 0 0 5px;
}
</style>

</head>
<body>

<div class="container">
<div class="fst-top">
	<h2>축제</h2>
</div>
<div class="cld_area" >
	<ul class="cld_area_ul cld_a" >
		<li ><a href="#">전체</a></li>
		<li ><a href="#">1월</a></li>
		<li ><a href="#">2월</a></li>
		<li ><a href="#">3월</a></li>
		<li ><a href="#">4월</a></li>
		<li ><a href="#">5월</a></li>
		<li ><a href="#">6월</a></li>
		<li ><a href="#">7월</a></li>
		<li ><a href="#">8월</a></li>
		<li ><a href="#">9월</a></li>
		<li ><a href="#">10월</a></li>
		<li ><a href="#">11월</a></li>
		<li ><a href="#">12월</a></li>
	</ul>
</div>
<div class="row">
<!-- 이미지 데이터가 있는 만큼 반복 처리 -->
	<c:forEach items="${list }" var="vo" varStatus="vs">
		<c:if test="${vs.index != 0 && vs.index % 4 == 0 }">
		<!-- 이미지 인덱스가 0 보다 크고 4의 배수이면 줄바꿈 코드 추가 -->
			${"</div><div class='row'>" }
		</c:if>
		<!-- 하나의 이미지 표시 -->
		<div class="col-md-3">
	   		<div class="thumbnail dataRow" data-no = "${vo.no }" style="padding: unset;">
		        <img src="${vo.photo }" alt="${vo.title }" style="width:100%">
		        <div class="caption">
			          <p style="font-weight: bold; font-size: 15px;">
			          	<span class="no" style="display: none;">${vo.no }</span>
			         	 ${vo.title }
			          </p>
			          <p>${vo.rgn }</p>
			         <div>${vo.startDate } - ${vo.endDate }</div>
		        </div>
	    	</div>
	  	</div>
	</c:forEach>
</div>

<!-- 페이지네이션 -->
<div>
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" query="&key=${pageObject.key }&word=${pageObject.word }" />	
</div>
<c:if test="${!empty login && login.gradeNo == 9 }">
	<a href="writeForm.do" class="btn btn-default">글쓰기</a>
</c:if>
</div>
</body>
</html>