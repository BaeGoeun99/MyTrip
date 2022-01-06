<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<style type="text/css">
.dataRow:hover {
	cursor: pointer;
	font-weight: bold;
	border-left: 1px solid;
	border-right: 1px solid;
}
.table, .table>thead>tr>th{
	text-align: center;
}
.nowrarea{
	padding: 5px;
/* 	margin-top: 15px; */
/* 	float: right; */
}
.sing:hover{
	background: #63c663;
	color: white;
}
</style>

<script type="text/javascript">
$(function() {
	$(".dataRow").click(function() {
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&inc=1" + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
	});
});
</script>


</head>
<body>
<div class="container">
<h3 style="border-bottom: 1px solid #777; padding: 5px;">공지사항</h3>

<table class="table">
	<thead style="background: #eee;">
		<tr>
			<th>번호</th>
<!-- 		<th style="text-align: left;">제목</th> -->
			<th>제목</th>
			<th>등록일</th>
		</tr>
	</thead>	
	<tbody style="font-size: 16px;">
		<c:forEach items="${list }" var="vo">
			<tr class="dataRow">
				<td class="no" style="padding: 10px;">${vo.no }</td>
				<td style="text-align: left; padding: 10px;">${vo.title }</td>
				<td style="padding: 10px;"><fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" /></td>
			</tr>
		</c:forEach>
	</tbody>	
</table>
<!-- 관리자용 공지 등록버튼 -->
<c:if test="${!empty login && login.gradeNo == 9}">
	<div class="nowrarea">
		<a href="write.do" class="btn btn-default sing">공지등록</a>
	</div>	
</c:if>
<!-- 페이지네이션 -->
<div style="text-align: center;">
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" ></pageObject:pageNav>
</div>

</div>
</body>
</html>