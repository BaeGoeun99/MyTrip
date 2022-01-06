<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>
<div class="container">
<h3 style="border-bottom: 3px solid; padding: 5px;">로그인</h3>

<form action="login.do" method="post">
	<div class="form-group">
		<label for="id">아이디</label>
		<input type="text" class="form-control" id="id" name="id" required>
	</div>
	
	<div class="form-group">
		<label for="pw">비밀번호</label>
		<input type="password" class="form-control" id="pw" name="pw" required>
	</div>
	
	<button type="submit" class="btn btn-default">로그인</button>
	<a href="/member/write.do" class="btn btn-default pull-right">회원가입</a>
</form>
	
</div>
</body>
</html>