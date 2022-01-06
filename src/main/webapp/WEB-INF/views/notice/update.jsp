<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[수정]${vo.title }</title>

<style type="text/css">
.upBtn:hover{
	background: #63c663;
	color: white;
}
.reBtn:hover{
	background: #ed3939e8;
	color: white;
}
</style>

</head>
<body>
<div class="container">
<h3 style="padding: 5px; border-bottom: 1px solid;">공지사항 수정</h3>
<form action="update.do" method="post">
	<input type="hidden" name="page" value="${pageObject.page }">
	<input type="hidden" name="perPageNum=" value="${pageObject.perPageNum}">
	<div class="form-group">
		<label for="no">No.</label>
		<input type="text" class="form-control" id="no" name="no" value="${vo.no }" readonly>
	</div>
	
	<div class="form-group">
		<label for="title">제목</label>
		<input type="text" class="form-control" id="title" name="title" value="${vo.title }">
	</div>
	
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="10" id="content" name="content">${vo.content }</textarea>
	</div>
	
	<button type="submit" class="btn btn-default upBtn">수정</button>
	<button type="reset" class="btn btn-default reBtn pull-right">새로고침</button>
</form>
	
</div>
</body>
</html>