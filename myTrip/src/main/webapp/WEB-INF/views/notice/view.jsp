<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글보기</title>
<script type="text/javascript">
$(function(){
	$("#updateBtn").click(function(){
		location = "updateForm.do?no=${vo.no}&page=${param.page}&perPageNum=${param.parpageNum}";
	});
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제하시겠습니까?"))
			location = "delete.do?no=${vo.no}";
	});
	$("#listBtn").click(function(){
		location = "list.do?page=${param.page}&perPageNum=${param.parpageNum}";
	});
});
</script>
</head>
<body>
<div class="container">
<h1>게시판 글보기</h1>
<table class="table">
<tr>
	<th>번호</th>
	<td>${vo.no }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${vo.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td><pre style="background: none;border: none;">${vo.content }</pre></td>
</tr>
<tr>
	<th>공지 작성일</th>
	<td>${vo.writeDate }</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${vo.hit }</td>
</tr>
<c:if test="${login.gradeNo == 9 }">
<tr>
	<th>공개여부</th>
	<td>${vo.open }</td>
</tr>
</c:if>
</table>
<c:if test="${login.gradeNo == 9 }">
<button id="updateBtn" class="btn btn-default">수정</button>
<button id="deleteBtn" class="btn btn-default">삭제</button>
</c:if>
<button id="listBtn" class="btn btn-default">리스트</button>
</div>
</body>
</html>