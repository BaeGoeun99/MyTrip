<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 등록 폼</title>

<script type="text/javascript">
// jquery()
$(function(){
	// 이벤트 처리
	// jquery 에서 선택 - tr : tag, .dataRow : class, #cancelBtn : id
	$("#cancelBtn").click(function(){
		// alert("취소 클릭");
		// history - 방문한 페이지의 정보가 저장되어 있는 객체 : JS
		// history.back() - 이전 페이지로 이동. histoty.go(-1)
		history.back();
	})
});
</script>

</head>
<body>
<div class="container">
<h1>이미지 등록 폼</h1>
<!-- html로 데이터 입력 받기 
	 파일은 전송하기 위해서 form안에 method="post", enctype="multipart/form-data" 속성을 반디스 지정해야만 한다. -->
<form action="write.do" method="post" enctype="multipart/form-data">
<table class= "table">
<tr>
	<th>제목</th>
	<td><input name="title" class="form-control"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="7" name="content" class="form-control"></textarea></td>
</tr>
<tr>
	<th>이미지 파일</th>
	<!-- 넘어가는 파일의 이름을 찾는 key가 imageFile -->
	<td><input name="imageFile" class="form-control" type="file"></td>
</tr>
</table>
<!-- button의 기본 tpye - submit ==> button을 클리하면 submit()를 호출해서 데이터와 함께 연결된 페이지로 이동 -->
<button>등록</button>
<button type="reset">새로입력</button>
<!-- type="button" - 버튼으로 보이지만 동작을 하지 않는다. 동작을 JS로 작성한다. -->
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>