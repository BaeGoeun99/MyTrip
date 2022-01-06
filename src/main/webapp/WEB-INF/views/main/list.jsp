<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>

<style type="text/css">
h2{
	text-align: center;
	width: 100%;
	height: 80px;
	padding: 20px;
	border: 1px solid;
}
.main-b{
	margin-bottom: 10px;
}
.top{
	width: 100%;
	padding: 10px;
}
</style>

<script type="text/javascript">
$(function() {
	$(".dataRow").click(function() {
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key }&word=${pageObject.word }"
	});
});
</script>

</head>
<body>

<div class="main-b">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active" style="border: 1px solid #777;"></li>
			<li data-target="#myCarousel" data-slide-to="1" style="border: 1px solid #777;"></li>
			<li data-target="#myCarousel" data-slide-to="2" style="border: 1px solid #777;"></li>
			<li data-target="#myCarousel" data-slide-to="3" style="border: 1px solid #777;"></li>
		</ol>
	
	<div class="carousel-inner">
		<div class="item active">
			<img alt="메인배너0" src="/upload/slide/noticeImage.jpg" width="100%">
		</div>
		<div class="item ">
			<img alt="메인배너1" src="/upload/slide/mainimage1.jpg" width="100%">
		</div>
		<div class="item">
			<img alt="메인배너2" src="/upload/slide/mainimage2.jpg" width="100%">
		</div>
		<div class="item">
			<img alt="메인배너3" src="/upload/slide/mainimage3.jpg" width="100%">
		</div>
	</div>
	
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#myCarousel" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right"></span>
		<span class="sr-only">Next</span>
	</a>
	</div>
</div>

<div class="container">

<!-- 관리자에게만 보이는 컨텐츠 등록 버튼 -->
<%-- <c:if test="${!empty login && login.gradeNo == 9}"> --%>
<!-- 	<div style="display: inline-block; width: 100%;"> -->
<!-- 		<a href="write.do" class="btn btn-default" style="float: right;">글쓰기</a> -->
<!-- 	</div> -->
<%-- </c:if> --%>

<div class="top">
<!-- 게시판 검색 -->
<div style="margin-bottom: 10px; width: 60%; float: right;">
<form action="list.do">
	<!-- 페이지 정보를 다시 그대로 보내준다 -->
	<input name="page" type="hidden" value="1">
	<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
	<div class="input-group">
		<span class="input-group-addon" style="border-radius: unset; background: white; padding: unset;">
			<select name="key" style="border: none; padding: 5px;">
				<!-- 검색 종류 선택 (pulldown 메뉴 - select) : key -->
				<option value="a" ${(pageObject.key == "a")?"selected":"" }>전체</option>
				<option value="t" ${(pageObject.key == "t")?"selected":"" }>제목</option>
				<option value="c" ${(pageObject.key == "c")?"selected":"" }>내용</option>
			</select>
		</span>
		<input id="word" type="text" class="form-control" name="word" placeholder="검색" value="${param.word }">
		<div class="input-group-btn">
			<button class="btn btn-default" type="submit" style="border-radius: unset;">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
</form>
</div>

<div class="totalCount">
	<p>총 <span style="font-size: 20px; font-weight: bold; color: #bb58e7;">${pageObject.totalRow }</span>
		개의 컨텐츠가 있어요!</p>
</div>

</div>

<div class="content">
<div class="row">
<c:forEach items="${list }" var="vo" varStatus="vs">
${(vs.index != 0 && (vs.index % 4 == 0))?"</div><div class='row'>":""}
	<div class="col-md-3 dataRow">
		<div class="thumbnail" style="padding: unset; border-radius: unset; border: unset;">
			<img src="${vo.fileName }" alt="${vo.fileName }" style="width:100%">
			<div class="caption">
				<strong><span class="no" style="display: none;">${vo.no }</span> ${vo.title }</strong>
<%-- 				${vo.name }(${vo.id }) |  --%>
<%-- 				<span><fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" /></span> --%>
			</div>
		</div>
	</div>
</c:forEach>
</div>

</div>
<div class="bottom">

</div>

<div style="text-align: center;">
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" query="&key=${pageObject.key }&word=${pageObject.word }"></pageObject:pageNav>
</div>

</div>
</body>
</html>