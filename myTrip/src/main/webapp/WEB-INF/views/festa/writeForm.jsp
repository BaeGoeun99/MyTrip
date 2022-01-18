<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제 정보 작성</title>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		history.back();
	})
});
</script>

<style type="text/css">
.tihe{
	border-bottom: 2px solid;
}

</style>

</head>
<body>

<div class="container">
<div class="tihe">
	<h1>축제 정보 작성폼</h1>
	<b style="font-size: 13px; color: #666;"><span style="color: red;">*</span>모든 항목을 기입하셔야 합니다.</b>
</div>
<form action="write.do" method="post" enctype="multipart/form-data">
<table class="table">
	<tr>
		<th>제목</th>
		<td><input name="title" class="form-control" placeholder="지역 축제명 입력" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" rows="7" class="form-control" required ></textarea></td>
	</tr>
	<tr>
		<th>이미지 파일</th>
		<td><input name="photo" class="form-control" type="file" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input name="address" class="form-control" placeholder="축제가 열리는 주소 입력" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>지역</th>
		<td><input name="rgn" class="form-control" placeholder="ex) 경기 포천시, 서울 강남구" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>연락처</th>
		<td>
		<div class="form-inline">
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="3" pattern="[0-9]{2,3}" autocomplete="off" required>-
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="4" pattern="[0-9]{3,4}" autocomplete="off" required>-
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="4" pattern="[0-9]{4}" autocomplete="off" required>		
		</div>
		</td>
	</tr>
	<tr>
		<th>홈페이지</th>
		<td><input name="website" class="form-control" placeholder="축제 관할 공식 홈페이지 입력" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>주최</th>
		<td><input name="host" class="form-control" placeholder="축제 주최/주관 입력" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>축제 시작일</th>
		<td><input name="startDate" class="form-control" type="date" style="width:300px" required></td>
	</tr>
	<tr>
		<th>축제 종료일</th>
		<td><input name="endDate" class="form-control" type="date" style="width:300px" required></td>
	</tr>
</table>
<button class="btn btn-default">등록</button>
<button type="reset" class="btn btn-default">새로입력</button>
<button type="button" id="cancelBtn" class="btn btn-default">뒤로가기</button>

</form>
</div>
</body>
</html>