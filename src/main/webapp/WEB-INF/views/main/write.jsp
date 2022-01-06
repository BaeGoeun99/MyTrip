<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨텐츠 등록</title>

<style type="text/css">
.wtBtn:hover{
	background: #42d142;
	color: white;
}
</style>

</head>
<body>
<div class="container">
<br><br>
<form action="write.do" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label for="title">제목</label>
		<input type="text" class="form-control" id="title" name="title" required>
	</div>

	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content" required></textarea>
	</div>
	
	<div class="form-group">
		<label for="imageFile">첨부파일</label>
		<input type="file" class="form-control" id="imageFile" name="imageFile" required>
	</div>
	
	<div class="form-group">
		<label for="vod">VOD </label>
		<a data-toggle="modal" data-target="#myModal" style="margin-left: 10px;"> vod 링크 등록 가이드</a>
		<input type="text" class="form-control" id="vod" name="vod" required>
	</div>
		
	<div class="form-group">
		<label for="launchDate">개봉일</label>
		<input type="text" class="form-control" id="launchDate" name="launchDate">
	</div>
		
	<div class="form-group">
		<label for="company">제작사/배급사</label>
		<input type="text" class="form-control" id="company" name="company">
	</div>
		
	<div class="form-group">
		<label for="author">작가/그림</label>
		<input type="text" class="form-control" id="author" name="author">
	</div>
	
	<button type="submit" class="btn btn-default wtBtn">등록</button>
	<a href="list.do" class="btn btn-default pull-right">리스트</a>
</form>
	
</div>

<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">vod 링크 등록 가이드</h4>
			</div>
		    <div class="modal-body">
		    	<div class="form-group">
			    	<img src="/upload/videoURLdown.jpg" style="border: 1px solid;"><br>
			    	<p>1.유튜브 영상 공유</p>
			    	<p>2.퍼가기</p>
			    	<p>3.이미지의 색칠된 부분만 복사하여 입력</p>		    	
		   		</div>
		   	</div>
		</div>
	</div>
</div>

</body>
</html>