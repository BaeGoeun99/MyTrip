<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>

<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
h3{
	border-bottom: 2px solid #999;
	padding: 5px;
}
.table, .table>thead>tr>th{
	text-align: center;
}
</style>

<script type="text/javascript">
$(function() {
	$(".dataRow").click(function() {
		var id = $(this).find(".id").text();
		location = "view.do?id=" + id + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
	});
});
</script>

</head>
<body>
<div class="container">
<h3>회원 리스트<small class="pull-right">총 회원수 : <span style="font-weight: bold;">${pageObject.totalRow }</span></small></h3>
<!-- 검색 -->
<div style="margin-bottom: 10px; width: 100%; float: right;">
<form action="list.do">
	<input name="page" type="hidden" value="1">
	<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
	<div class="input-group">
		<span class="input-group-addon" style="border-radius: unset; background: white; padding: unset;">
			<select name="key" style="border: none; padding: 5px;">
				<option value="i" ${(pageObject.key == "i")?"selected":"" }>아이디</option>
				<option value="n" ${(pageObject.key == "n")?"selected":"" }>이름</option>
				<option value="s" ${(pageObject.key == "s")?"selected":"" }>상태</option>
				<option value="g" ${(pageObject.key == "g")?"selected":"" }>등급명</option>
				<option value="r" ${(pageObject.key == "w")?"selected":"" }>가입일</option>
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
<table class="table" style="border-top: 1.5px solid #ddd;">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>상태</th>
			<th>등급명</th>
			<th>등급번호</th>
			<th>가입일</th>
		</tr>
	</thead>	
	<tbody>
		<c:forEach items="${list }" var="vo">
			<tr class="dataRow">
				<td class="id">${vo.id }</td>
				<td>${vo.name }</td>
				<td>${vo.status }</td>
				<td>${vo.gradeName }</td>
				<td>${vo.gradeNo }</td>
				<td><fmt:formatDate value="${vo.regDate }" pattern="yyyy.MM.dd / hh:mm:ss" /></td>
			</tr>
		</c:forEach>
	</tbody>	
</table>

<div style="text-align: center;">
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"></pageObject:pageNav>
</div>

</div>
</body>
</html>