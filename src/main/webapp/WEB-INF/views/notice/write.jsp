<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>

<style type="text/css">
.mkBtn:hover{
	background: #63c663;
	color: white;
}
.reBtn:hover{
	background: #ed3939e8;
	color: white;
}
.btnBox{
	width: 100%;
	padding: 5px;
	margin-top: 5px;
	float: left;
}
.back{
	padding: 3px;
	color: black;
	float: right;
}
.back:hover {
    color: black;
    font-weight: bold;
}
.formbox{
	width: 100%;
	margin-top: 15px;
	float: left;
}
</style>

</head>
<body>
<div class="container">
<div class="btnBox">
	<a href="list.do" class="pull-right back">
	<span class="glyphicon glyphicon-arrow-left" style="padding-right: 3px;"></span>뒤로가기</a>
</div>
<br>
<h3 style="padding: 5px; border-bottom: 1px solid;">공지사항 등록</h3>
<div class="formbox">
	<form action="write.do" method="post">
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" class="form-control" id="title" name="title">
		</div>
		
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" rows="10" id="content" name="content"></textarea>
		</div>
	
		<button type="submit" class="btn btn-default mkBtn">등록</button>
		<button type="reset" class="btn btn-default reBtn pull-right">새로고침</button>
	</form>
</div>
</div>
</body>
</html>