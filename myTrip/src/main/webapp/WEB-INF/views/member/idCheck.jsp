<%@page import="com.mytrip.member.service.MemberIDCheckService"%>
<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%-- <% String id = request.getParameter("id");
			request.setAttribute("id", ExecuteService.execute(new MemberIDCheckService(), id));
		%>  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>

<script type="text/javascript">
$(function(){
	$("#useIdBtn").click(function(){
		$(opener.document).find("#id").val("${param.id}");
		window.close();
		
	});
});
</script>
</head>
<body>
<div class="container">
<h1>아이디 중복 체크</h1>
<hr>
<form action="idCheck.do" method="post">
<div class="form-group">
	<label>아이디 입력</label>
	<input name="id" class="form-control" autocomplete="off">
</div>
<button class="btn btn-default">체크</button>
</form>
<hr>
<div id="result">
	<c:if test="${empty param.id }">
	<!-- 데이터 아이디가 안 넘어온 경우 -->
	아이디를 입력해 주세요. 그리고 체크 버튼을 클랙하세요.
	</c:if>
	<c:if test="${!empty param.id }">
		<c:if test="${!empty id }">
			검색한 아이디 [<span style="color:red">${id }</span>]는 중복된 아이디 입니다. 다시 검색해 주세요.
		</c:if>
		<c:if test="${empty id }">
			검색한 아이디 [<span style="color:red">${param.id }</span>]는 사용가능한 아이디 입니다. 
			<button class="btn btn-default" id="useIdBtn">아이디 사용하기</button>
		</c:if>
	</c:if>
</div>
</div>
</body>
</html>