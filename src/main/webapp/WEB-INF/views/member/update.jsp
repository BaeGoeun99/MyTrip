<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.id } 정보수정</title>

<!-- jQuery UI 관련 웹 라이브러리 등록 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
	.reqd{color: red;}
	.sign:hover{background: #42d142;color: white;}
	.container{margin-bottom: 20px;}
</style>

<script type="text/javascript">
	$( function() {
		$(".radio").checkboxradio();
	});

	$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();
	});

</script>

<script type="text/javascript">
	$(document).ready(function() {
	   // datepicker 클래스 이벤트 - 적정한 옵션을 넣어서 초기화 시켜 준다. 기본 datepicker()로 사용 가능
	   $(".datepicker").datepicker({
	   changeMonth: true,
	   changeYear: true,
	   dateFormat: "yy.mm.dd",
	   dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	   monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	   });
	});
</script>

</head>
<body>
<div class="container">
<h3 style="border-bottom: 3px solid; padding: 5px;">${vo.id } 님의 정보 수정 페이지</h3>
<p style="font-size: 12px;"><span class="reqd"> * </span> 표시는 필수 입력란입니다.</p>
<form action="update.do" method="post" >
	<input type="hidden" name="page" value="${pageObject.page }">
	<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
	<div class="form-group">
		<label for="id"><span class="reqd"> * </span>아이디</label>
		<input type="text" class="form-control" id="id" name="id" value="${vo.id }" data-toggle="tooltip" title="아이디는 수정이 불가합니다." readonly>
	</div>
		<div class="form-group">
			<label for="pw"><span class="reqd"> * </span>비밀번호</label>
			<input type="password" class="form-control" id="pw" name="pw" value="${vo.pw }" required data-toggle="tooltip" title="비밀번호는 4자 이상이어야 합니다.">
		</div>
		
		<div class="form-group">
			<label for="pw2"><span class="reqd"> * </span>비밀번호 확인</label>
			<input type="password" class="form-control" id="pw2" name="pw2" value="${vo.pw }" required data-toggle="tooltip" title="비밀번호를 동일하게 입력해주세요.">
		</div>
	<div class="form-group">
		<label for="name"><span class="reqd"> * </span>이름</label>
		<input type="text" class="form-control" id="name" name="name" value="${vo.name }" required>
	</div>
	
	<div class="form-group">
		<label><span class="reqd"> * </span>성별</label><br>
		<label for="woman">여성</label>
   		<input class="radio" type="radio" name="gender" id="woman" value="여자" checked>
		<label for="man">남성</label>
   		<input class="radio" type="radio" name="gender" id="man" value="남자">
	</div>
	
	<div class="form-group">
		<label for="tel">연락처</label>
		<input type="tel" class="form-control" id="tel" name="tel" value="${vo.tel }">
	</div>
	
	<div class="form-group">
		<label for="email"><span class="reqd"> * </span>이메일</label>
		<input type="email" class="form-control" id="email" name="email" value="${vo.email }" required>
	</div>
	
	<h5><span style="color: red;">*</span>회원 사진 변경은 회원 정보의 사진수정을 이용해주세요.</h5>
	
	<button type="submit" class="btn btn-default sign" style="text-align: center;">정보수정</button>
</form>
</div>
</body>
</html>