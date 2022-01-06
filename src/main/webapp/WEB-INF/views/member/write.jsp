<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 작성자 : 배고은 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<!-- jQuery UI 관련 웹 라이브러리 등록 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
	.reqd{color: red;}
	.sign:hover{background: #42d142;color: white;}
	.container{margin-bottom: 20px;}
	.clouse{height: 150px; overflow: auto; color: gray;}
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
<h3 style="border-bottom: 3px solid; padding: 5px;">회원가입</h3>
<p style="font-size: 12px;"><span class="reqd"> * </span> 표시는 필수 입력란입니다.</p>
<form action="write.do" method="post" enctype="multipart/form-data" autocomplete="off">
	<div class="form-group">
		<label for="id"><span class="reqd"> * </span>아이디</label>
		<input type="text" class="form-control" id="id" name="id" required data-toggle="tooltip" title="아이디는 4자 이상이어야 합니다.">
	</div>
	
	<div class="form-group">
		<label for="pw"><span class="reqd"> * </span>비밀번호</label>
		<input type="password" class="form-control" id="pw" name="pw" required data-toggle="tooltip" title="비밀번호는 4자 이상이어야 합니다.">
	</div>
	
	<div class="form-group">
		<label for="pw2"><span class="reqd"> * </span>비밀번호 확인</label>
		<input type="password" class="form-control" id="pw2" name="pw2" required data-toggle="tooltip" title="비밀번호를 동일하게 입력해주세요.">
	</div>
	
	<div class="form-group">
		<label for="name"><span class="reqd"> * </span>이름</label>
		<input type="text" class="form-control" id="name" name="name" required>
	</div>
	
	<div class="form-group">
		<label><span class="reqd"> * </span>성별</label><br>
		<label for="woman">여성</label>
   		<input class="radio" type="radio" name="gender" id="woman" value="여자" checked>
		<label for="man">남성</label>
   		<input class="radio" type="radio" name="gender" id="man" value="남자">
	</div>
	
	<div class="form-group">
		<label for="birth"><span class="reqd"> * </span>생년월일</label>
		<input type="text" class="form-control datepicker" id="birth" name="birth" required style="width: 230px;">
	</div>
	
	<div class="form-group">
		<label for="tel">연락처</label>
		<input type="tel" class="form-control" id="tel" name="tel">
	</div>
	
	<div class="form-group">
		<label for="email"><span class="reqd"> * </span>이메일</label>
		<input type="email" class="form-control" id="email" name="email" required>
	</div>
	
	<div class="form-group">
		<label for="imageFile">회원이미지</label>
		<input type="file" class="form-control" id="imageFile" name="imageFile">
	</div>
	
	<div class="form-group">
		<label><span class="reqd"> * </span>서비스 이용 약관</label>
		<div class="well clouse">
			<b>제 1조 목적</b><br>
			이 약관은 애니탑(이하 “회사”)에서 제공하는 회사 및 회사에서 제공하는 제반 서비스(이하 "서비스")에 접속과 사용자에 의해서<br>
			업로드 및 다운로드 되어 표시되는 모든 정보, 텍스트, 이미지 및 기타 자료를 이용하는 이용자(이하 "회원")와 서비스 이용에 관한<br>
			권리 및 의무와 책임사항, 기타 필요한 사항을 규정하는 것을 목적으로 합니다.<br><br>
			
			<b>제 2조 약관의 게시와 효력, 개정</b><br>
			1. 회사는 서비스의 가입 과정에 본 약관을 게시합니다.<br>
			2. 회사는 관련법에 위배되지 않는 범위에서 본 약관을 변경할 수 있습니다.<br>
			3. 회원은 회사가 전항에 따라 변경하는 약관에 동의하지 않을 권리가 있으며,<br>
			이 경우 회원은 회사에서 제공하는 서비스 이용 중단 및 탈퇴 의사를 표시하고 서비스 이용 종료를 요청할 수 있습니다.<br>
			 다만, 회사가 회원에게 변경된 약관의 내용을 통보하면서 회원에게 "7일 이내 의사 표시를 하지 않을 경우 <br>
			의사 표시가 표명된 것으로 본다는 뜻"을 명확히 통지하였음에도 불구하고, <br>
			거부의 의사표시를 하지 아니한 경우 회원이 변경된 약관에 동의하는 것으로 봅니다.<br><br>
			
			<b>제 3조 약관의 해석과 예외 준칙</b><br>
			1. 회사는 제공하는 개별 서비스에 대해서 별도의 이용약관 및 정책을 둘 수 있으며,<br>
			해당 내용이 이 약관과 상충할 경우 개별 서비스의 이용약관을 우선하여 적용합니다.<br>
			2. 본 약관에 명시되지 않은 사항이 관계법령에 규정되어 있을 경우에는 그 규정에 따릅니다.<br>
		</div>
		<!-- 체크박스 -->
		<label><input type="checkbox" title="약관에 동의하셔야 회원가입이 가능합니다." required> 약관에 동의합니다.</label><br>
		<label><span class="reqd"> * </span>개인정보 수집 및 이용에 대한 안내</label>
		<div class="well clouse">
			<b>개인정보 수집 이용 목적</b><br>
			회원 가입 의사의 확인, 이용자 식별, 이용자 보호 및 서비스 운영<br><br>
			
			<b>수집 항목</b><br>
			이메일 주소, 비밀번호<br><br>
			
			<b>이용 보유 기간</b><br>
			탈퇴시 즉시 파기<br><br>
			
			<b>개인정보 수집 및 이용 동의를 거부할 권리</b><br>
			이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가 있습니다. <br>
			회원가입 시 수집하는 최소한의 개인정보, <br>
			즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우, 회원가입이 어려울 수 있습니다.<br>
		</div>
		<!-- 체크박스 -->
		<label><input type="checkbox" title="약관에 동의하셔야 회원가입이 가능합니다." required> 약관에 동의합니다.</label><br>
	</div>
	
	<button type="submit" class="btn btn-default sign">회원가입</button>
<!-- 	<a href="/main/list.do" class="btn btn-default pull-right">홈</a> -->
</form>
	
</div>
</body>
</html>