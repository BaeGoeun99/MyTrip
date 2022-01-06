<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.title }</title>

<style type="text/css">
.table>tbody>tr>th{
	width: 100px;
	background: #f1f0f0;
}
#delBtn:hover{
	background: #ed3939e8;
	color: white;
}
.upBtn:hover, #MdRepWriteBtn:hover, #MdRepUpBtn:hover{
	background: #63c663;
	color: white;
}
.btnBox{
	float: right;
	display: inline-block;
}
.dataRow:hover {
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
$(function() {
	$("#delBtn").click(function() {
		if(!confirm("데이터가 전부 사라집니다. 정말 삭제하시겠습니까?")) return false;
	});
});
</script>

<style type="text/css">
.adminarea{
	width: 100%;
	background: #eee;
	padding: 5px;
}
.contentbox{
	padding: 5px;
	font-size: 15px;
	line-height: 1.5;
	min-height: 450px;
	padding-top: 15px;
}
.writerDatebox{
	font-size: 15px;
	text-align: center;
	padding: 5px;
	margin-top: 5px;
	margin-bottom: 5px;
}
.titlebox>h2{
	padding: 5px 5px 10px 5px;
	border-bottom: 1px solid #777;
}
.btnBox{
	width: 100%;
	padding: 5px;
	margin-top: 5px;
}
.back{
	padding: 3px;
/* 	border-bottom: 1px solid; */
	color: black;
}
.back:hover {
    color: black;
    font-weight: bold;
}
</style>

</head>
<body>
<div class="container">
<div class="btnBox">
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="pull-left back">
	<span class="glyphicon glyphicon-arrow-left" style="padding-right: 3px;"></span>뒤로가기</a>
</div>
<div class="box" style="margin-top: 30px; padding: 10px; min-height: 600px; border-bottom: 1px solid #777">
<!-- 관리자용 영역 -->
<c:if test="${!empty login && login.gradeNo == 9}">
	<div class="adminarea">
		<div>
			<span>No.</span>
			<span>${vo.no }</span>
			<div class="pull-right">
				<span>[${vo.id }] 작성</span>
			</div>
		</div>
	</div>
</c:if>
	
	<div class="titlebox">
		<h2>[공지]${vo.title }</h2>
	</div>
	<div class="contentbox">
		<span>${fn:replace(vo.content, replaceChar, "<br/>" )}</span>
	</div>
	<div class="writerDatebox">
		<span>등록일</span>
		<b><fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" /></b>
	</div>
	
</div>

<c:if test="${!empty login && login.gradeNo == 9}">
	<div class="btnBox">
		<a href="update.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="btn btn-default upBtn pull-left">수정</a>
		<a href="delete.do?no=${vo.no }" class="btn btn-default pull-right" id="delBtn">삭제</a>
	</div>
</c:if>
</div>
</body>
</html>