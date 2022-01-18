<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글수정</title>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		// JS - 이전 페이지
		history.back();
	});
});
</script>
</head>
<body>
<div class="container">
<h1>공지사항 수정 폼</h1>
<form action="update.do" method="post">
<input name="page" type="hidden" value="${param.page }"> 
<input name="perPageNum" type="hidden" value="${param.perPageNum }"> 
<table class= "table">
<tr>
	<th>글번호</th>
	<td><input name="no" class="form-control" value="${vo.no }" readonly="readonly"></td>
</tr>
<tr>
	<th>제목</th>
	<td><input name="title" class="form-control" value="${vo.title }"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="10" name="content" class="form-control">${vo.content }</textarea></td>
</tr>
<tr>
	<th>공개여부</th>	
	<td><input type="radio" name="open" id="open" value="Y" class="radio" checked="checked" ><span class="ml_10">공개</span></td>
	<td><input type="radio" name="open" id="open" value="N" class="radio" ><span class="ml_10">비공개</span></td>
</tr>

</table>
<button>수정</button>
<button type="reset">새로입력</button>
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>