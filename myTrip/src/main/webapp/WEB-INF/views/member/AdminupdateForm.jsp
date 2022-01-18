<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		history.back();
	});
});
</script>

</head>
<body>
<div class="container">
<h1>회원정보 수정</h1>
<form action="Adminupdate.do" method="post">
<table class= "table">
<tr>
	<th>아이디</th>
	<td><input name="id" class="form-control" value="${vo.id }" readonly="readonly"></td>
</tr>
<tr>
	<th>패스워드</th>
	<td><input name="pw" type="password" class="form-control" value="${vo.pw }" ></td>
</tr>
<tr>
	<th>이름</th>
	<td><input name="name" class="form-control" value="${vo.name }" readonly="readonly"></td>
</tr>
<tr>
	<th>성별</th>
	<td><input name="gender" class="form-control" value="${vo.gender }" readonly="readonly"></td>
</tr>
<tr>
	<th>생년월일</th>
	<td><input name="birth" class="form-control" value="${vo.birth }" readonly="readonly"></td>
</tr>
<tr>
	<th>전화번호</th>
	<td><input name="tel" class="form-control" value="${vo.tel }"></td>
</tr>
<tr>
	<th>이메일</th>
	<td><input name="email"  type="email" class="form-control" value="${vo.email }"></td>
</tr>
<tr>
	<th>지역</th>
	<td><input name="region" class="form-control" value="${vo.region }"></td>
</tr>
</table>
<button>수정</button>
<button type="reset">새로입력</button>
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>