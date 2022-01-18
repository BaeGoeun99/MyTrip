<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>

<script type="text/javascript">
$(function(){
	$("#idCheckBtn").click(function(){
		idCheckWindowOpen();
	});
	
	$("#id").click(function(){idCheckWindowOpen();});
	
	function idCheckWindowOpen(){
		window.open("idCheck.do", "idCheck", "width=480, height=500, top=100, left=400");
	}
});
</script>

</head>
<body>
	<div class="container">
		<form action="write.do" method="post" enctype="multipart/form-data">
			<h1>회원 가입</h1>
			<div>* 항목은 필수입력 사항입니다.</div>
			<div class="form-group">
				<label for="id">아이디(*)</label>
				<div class="input-group">
					<input name="id" id="id" required="required" class="form-control"
						readonly="readonly" autocomplete="off" placeholder="아이디 중복체크를 이용하세요. 팝업을 허용하셔야 합니다.">
					<div class="input-group-btn">
						<button type="button" id="idCheckBtn" class="btn btn-default">아이디
							중복체크</button>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="pw">비밀번호(*)</label> <input name="pw" type="password"
					class="form-control" id="pw">
			</div>
			 <div class="form-group">
				<label for="pw2">비밀번호 확인(*)</label> <input type="password"
					class="form-control" id="pw2">
			</div> 
			<div class="form-group">
				<label for="name">이름(*)</label> <input name="name" type="text"
					class="form-control" id="name" autocomplete="off">
			</div>
			<div class="form-group">
				<label>성별(*)</label>
				<div class="form-inline">
					<div class="form-group" style="margin: 0 10px">
						<label> <input name="gender" type="radio"
							class="form-control" id="gender_man" value="남자" checked="checked">
							남자
						</label>
					</div>
					<div class="form-group">
						<label> <input name="gender" type="radio"
							class="form-control" id="gender_woman" value="여자"> 여자
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="birth">생년월일(*)</label> <input name="birth" type="date"
					class="form-control" id="birth">
			</div>
			<div class="form-group">
				<label>연락처</label>
				<div class="form-inline">
					<input name="tel" class="form-control" style="width: 60px"
						type="text" maxlength="3" pattern="[0-9]{2,3}"
						title="숫자 2 ~ 3자리를 입력할 수 있습니다.">- <input name="tel"
						class="form-control" style="width: 80px" type="text" maxlength="4"
						pattern="[0-9]{4}" title="숫자 4자리를 입력할 수 있습니다.">- <input
						name="tel" class="form-control" style="width: 80px" type="text"
						maxlength="4" pattern="[0-9]{4}" title="숫자 4자리를 입력할 수 있습니다.">
				</div>
			</div>
			<div class="form-group">
				<label for="email">이메일(*)</label> <input name="email"
					class="form-control" type="email" id="email">
			</div>
			<div class="form-group">
				<label for="region">지역(*)</label> <input name="region"
					class="form-control" id="region">
			</div>
			<div class="form-group">
				<label for="imageFile">사진</label> <input name="imageFile" type="file"
					id="imageFile" class="form-control">
			</div>
			<button class="btn btn-default">가입</button>
			<button class="btn btn-default" type="reset">초기화</button>
			<button class="btn btn-default" type="button" id="cancelBtn">취소</button>
		</form>
	</div>
</body>
</html>