<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>

<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
p{
	clear: both;
    margin: 0 0 5px;
}
.panel-default{
	border: none;
}

.panel-default>.panel-heading{
	font-size: 22px;
    font-weight: bold;
    background-color: white;
    border-color: #333;
}
</style>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		// tag안에 data-module ="데이터" 로 설정한 데이터 가져오기
		var module = $(this).data("module");
		var no = $(this).find(".no").text();
		// 글보기 URL
		var url = "/" + module + "/view.do?no=" + no;
		if(module == "festa" || module == "diner" || module == "tourist") { location = url; }
		if(module == "notice") {
			url += "&inc=1";
			location = url; 
		}
		
	});
});
</script>

</head>
<body>
<div class="container">

<!-- 관광지 게시판 -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
			  <div class="panel-heading">관광지</div>
			  <div class="panel-body">
				  <c:forEach items="${touristList }" var="vo">
				  <div class="col-md-3">
				    <div class="thumbnail dataRow" data-module = "tourist" style="padding: unset;">
				        <img src="${vo.touristImage }" alt="${vo.name }" style="width:100%">
				        <div class="caption">
				          <p><span class="no" style="display: none;">${vo.no }</span></p>
				          <p style="font-weight: bold; font-size: 15px;">${vo.name }</p>
				        </div>
				    </div>
				  </div>
				  </c:forEach>
			  
			  </div>
			</div>
		</div>
	</div>

<!-- 축제 게시판 -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
		  <div class="panel-heading">축제</div>
		  <div class="panel-body">
			  <c:forEach items="${festaList }" var="vo">
			  <div class="col-md-3">
			    <div class="thumbnail dataRow" data-module = "festa" style="padding: unset;">
			        <img src="${vo.photo }" alt="${vo.title }" style="width:100%">
			        <div class="caption">
			           <p style="font-weight: bold; font-size: 15px;">
				          <span class="no" style="display: none;">${vo.no }</span>
				         	 ${vo.title }
			          </p>
			          <p>${vo.rgn }</p>
			        </div>
			    </div>
			  </div>
			  </c:forEach>
		  </div>
		</div>
	</div>
</div>

<!-- 맛집 -->
<div class="row">
	<div class="col-md-9" style="width:100%">
		<div class="panel panel-default">
			<div class="panel-heading">맛집</div>
			<div class="panel-body">
				<c:forEach items="${dinerList }" var="vo">
					<div class="col-md-3">
						<div class="thumbnail dataRow" data-module = "diner" style="padding: unset;">
							<img src="${vo.mainImage }" alt="${vo.name }" style="width:100%">
						<div class="caption">
							<p><span class="no" style="display: none;">${vo.no }</span></p>
							<div class="namePlace"><p style="font-weight: bold; font-size: 15px;">${vo.name }</p></div>
			            	<div class="areaKindPlace"><p>${vo.areaName }>${vo.kindName }</p></div>
						</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<!-- 공지사항 -->
<div class="row">
	<div class="col-md-3" style="width:100%">
		<div class="panel panel-default">
			<div class="panel-heading">공지사항</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<th style="text-align: center;">번호</th>
						<th >제목</th>
						<th style="text-align: center;">작성일</th>
					</tr>
					<c:forEach items="${noticeList }" var="vo">
					<tr class="dataRow" data-module="notice">
						<c:if test='${vo.open == "Y" }'>
							<td class="no" style="text-align: center;">${vo.no }</td>
							<td >${vo.title }</td>
							<td style="text-align: center;">${vo.writeDate }</td>
						</c:if>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>

</div>
</body>
</html>