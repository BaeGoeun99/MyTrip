<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지오류</title>
</head>
<body>
<div class="container">

	<div class="panel panel-danger">
		<p class="panel-heading"><span style="color: red;">Error500</span> - 오류 발생</p>
		<p class="panel-body">요청하신 페이지에 접근이 불가합니다</p>
	</div>
	
	<div class="well">
		<h4>${exception.message }</h4>
		<ul>
			<c:forEach items="${exception.stackTrace }" var="stack">
				<li>${stack }</li>
			</c:forEach>
		</ul>
	</div>
	
</div>
</body>
</html>