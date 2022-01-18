<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성 폼</title>

<script type="text/javascript">
$(function(){
	$("#cancleBtn").click(function(){
		history.back();
	})
})
</script>
</head>
<body>
<div class="container">
<h1>공지사항 작성 폼</h1>
<form action="write.do" method="post">
<table class="table">
<tr>
	<th>제목</th>
	<td><input name="title" class="form-control"></td>
</tr>
<tr>	
	<th>내용</th>
	<td><textarea rows="10" name="content" class="form-control"></textarea></td>
</tr>

<tr>
	<th>공개여부</th>	
	<td><input type="radio" name="open" id="open" value="Y" class="radio" checked="checked" ><span class="ml_10">공개</span></td>
	<td><input type="radio" name="open" id="open" value="N" class="radio" ><span class="ml_10">비공개</span></td>
</tr>

</table>
<button>등록</button>
<button type="reset">새로입력</button>
<button type="button" id="cancleBtn">취소</button>
</form>
</div>
</body>
</html>