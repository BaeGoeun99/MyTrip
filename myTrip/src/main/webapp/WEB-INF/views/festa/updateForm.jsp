<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제 정보 수정</title>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		history.back();
	});
});
</script>
<style type="text/css">
.tihe{
	width: 100%;
	border-bottom: 2px solid #333;
}
.fse{
	display: inline-block;
}
.tel-be{
    display: inline-block;
    border: 1px solid #e6e6e6;
    padding: 6px;
    border-radius: 5px;
    background: #eee;
    color: #565656;
}
</style>

</head>
<body>
<div class="container">
<div class="tihe">
	<h1>축제 정보 수정폼</h1>
	<b style="font-size: 13px; color: #666;"><span style="color: red;">*</span>모든 항목을 기입하셔야 합니다.</b>
</div>

<form action="update.do" method="post">
<input name="page" type="hidden" value="${param.page }">
<input name="perPageNum" type="hidden" value="${param.perPageNum }">
<table class="table">
	<tr>
		<th>게시글 번호</th>
		<td><input name="no" class="form-control" value="${vo.no }" readonly></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input name="title" class="form-control" placeholder="지역 축제명 입력" value="${vo.title }" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" rows="8" class="form-control" required>${vo.content }</textarea></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input name="address" class="form-control" placeholder="축제가 열리는 주소 입력" value="${vo.address }" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>지역</th>
		<td><input name="rgn" class="form-control" placeholder="ex) 경기 포천시, 서울 강남구" value="${vo.rgn }" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>연락처</th>
		<td>
		<div class="form-inline">
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="3" pattern="[0-9]{2,3}" autocomplete="off" required>-
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="4" pattern="[0-9]{3,4}" autocomplete="off" required>-
		<input name="tel" class="form-control" style="width:100px" type="text" maxlength="4" pattern="[0-9]{4}" autocomplete="off" required>		
		<div class="tel-be">기존 번호 : ${vo.tel }</div>
		
		</div>
		</td>
	</tr>
	<tr>
		<th>홈페이지</th>
		<td><input name="website" class="form-control" placeholder="축제 관할 공식 홈페이지 입력" value="${vo.website }" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>주최(주관)</th>
		<td><input name="host" class="form-control" placeholder="축제 주최/주관 입력" value="${vo.host }" autocomplete="off" required></td>
	</tr>
	<tr>
		<th>축제 시작일</th>
		<td>
		<input name="startDate" class="form-control fse" value="${vo.startDate }" type="date" style="width:250px" required>
		<input name="startDate" class="form-control fse" value="기존 시작일 : ${vo.startDate }" type="text" style="width:200px" readonly>
		</td>
	</tr>
	<tr>
		<th>축제 종료일</th>
		<td>
		<input name="endDate" class="form-control fse" value="${vo.endDate }" type="date" style="width:250px" required>
		<input name="endDate" class="form-control fse" value="기존 종료일 : ${vo.endDate }" type="text" style="width:200px" readonly>
		</td>
	</tr>
</table>

<button class="btn btn-default">수정</button>
<button type="reset" class="btn btn-default">새로입력</button>
<button type="button" id="cancelBtn" class="btn btn-default">뒤로가기</button>

</form>

</div>
</body>
</html>