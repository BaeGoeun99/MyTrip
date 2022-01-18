<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 회원정보 보기</title>

<script type="text/javascript">
// jquery() - html 문서가 로딩이 되면 함수를 호출해서 처리해 주세요~.
$(function(){
	$("#updateBtn").click(function(){
		location = "updateForm.do?id=${vo.id}";
	});
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제하시겠습니까?"))
			location = "delete.do?id=${vo.id}";
	});
	$("#listBtn").click(function(){
		location = "/main.do";
	});
});
</script>

</head>
<body>
<div class="container">
<h1>회원 상세정보</h1>
<table class="table">
<tr>
	<th>아이디</th>
	<td>${vo.id }</td>
</tr>
<tr>
	<th>이름</th>
	<td>${vo.name }</td>
</tr>
<tr>
	<th>성별</th>
	<td>${vo.gender }</td>
</tr>
<tr>
	<th>생년월일</th>
	<td>${vo.birth }</td>
</tr>
<tr>
	<th>전화번호</th>
	<td>${vo.tel }</td>
</tr>
<tr>
	<th>이메일</th>
	<td>${vo.email }</td>
</tr>
<tr>
	<th>지역</th>
	<td>${vo.region }</td>
</tr>

<tr>
	<th>사진</th>
	<td>${vo.photo }
	<img alt="내 사진" src="${vo.photo }" width="300px"></td>
</tr>
<tr>
	<th>가입일</th>
	<td>${vo.regDate }</td>
</tr>
<tr>
	<th>최근 접속일</th>
	<td>${vo.conDate }</td>
</tr>
<tr>
	<th>상태</th>
	<td>${vo.status }</td>
</tr>
<tr>
	<th>등급번호</th>
	<td>${vo.gradeNo }</td>
</tr>
<tr>
	<th>등급이름</th>
	<td>${vo.gradeName }</td>
</tr>
</table>
<button id="updateBtn" class="btn btn-default">수정</button>
<button id="deleteBtn" class="btn btn-default">탈퇴</button>
<button id="listBtn" class="btn btn-default">리스트</button>
</div>
</body>
</html>