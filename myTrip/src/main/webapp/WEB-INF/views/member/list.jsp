<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 리스트</title>

<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>


<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var id = $(this).find(".uid").text();
		location = "view.do?id=" + id;
	});
});
</script>

</head>
<body>
<h2>멤버 리스트입니다.</h2>
<div class="container">


<!-- 회원정보 검색 -->
<div>
	<form action="list.do">
		<!-- 페이지 정보를 다시 그대로 보내준다. -->
		<input name="page" type="hidden" value="1">
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
		<div class="input-group">
			<span class="input-group-addon">
				<select name="key">
					<option value="i" ${(pageObject.key == "i")?"selected":"" }>아이디</option>
					<option value="n" ${(pageObject.key == "n")?"selected":"" }>이름</option>
					<option value="g" ${(pageObject.key == "g")?"selected":"" }>성별</option>
					<option value="b" ${(pageObject.key == "b")?"selected":"" }>생년월일</option>
					<option value="r" ${(pageObject.key == "r")?"selected":"" }>지역</option>
					<option value="st" ${(pageObject.key == "st")?"selected":"" }>상태</option>
					<option value="gn" ${(pageObject.key == "gn")?"selected":"" }>등급</option>
				</select>
			</span>
			<input id="word" type="text" class="form-control" name="word" placeholder="검색 문장"
			value="${param.word }">
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		</div>
		
	</form>
</div>

<table class="table">
<tr>
	<th>아이디</th>
	<th>이름</th>
	<th>성별</th>
	<th>생일</th>
	<th>지역</th>
	<th>상태</th>
	<th>등급번호</th>
</tr>

<c:if test="${empty list }">
<tr>
<td>데이터가 존재하지 않습니다.</td>
</tr>
</c:if> 
<c:if test="${!empty list }">
	<c:forEach items="${list }" var="vo">
	<tr class="dataRow">
			<td class="uid" >${vo.id }</td>
			<td >${vo.name }</td>
			<td >${vo.gender }</td>
			<td >${vo.birth }</td>
			<td >${vo.region }</td>
			<td >${vo.status }</td>
			<td >${vo.gradeNo }</td>
		</tr>
	</c:forEach>
</c:if>
</table>
<div>
<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" 
 query="&key=${pageObject.key }&word=${pageObject.word }" />
</div>

</div>
</body>
</html>