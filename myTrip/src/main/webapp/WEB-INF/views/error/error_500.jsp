<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!-- isErrorPage="true" - 아래 HTML이 catch 부분으로 처리가 된다. -->
<!-- isErrorPage="false" - 아래 HTML이 try 부분으로 처리가 된다. 기본 값 -->

<%
// JAVA 입니다.
// 내마음 대로 예외 처리를 하고 싶다면 사용자 예외를 작성해라. 예외코드, 예외 메시지
// 그렇지 않으면 나름대로의 데이터 포맷을 만들어서 넣어라. 
// - 메시지 - Exception:에러코드::메시지
System.out.println("error_500.jsp - msg : " + exception.getMessage());
String [] msgs = exception.getMessage().split("::");
String msg = "오류가 발생 했습니다.";
String[] codes;
String code = "500";
String eClass = "java.lang.Exception";
if(msgs.length >= 2){
	msg = msgs[1];
	codes = msgs[0].split(":");
	if(codes.length >= 2){
		code = codes[1];
		eClass = codes[0];
	} else {
		code = codes[0];
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 내부 처리 오류</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

  <!-- Icon : Awesome 5 Icons 라이브러리 등록 -->
  <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

</head>
<body>
<div class="container">
	<div class="panel panel-default" style="margin-top : 80px;">
      <div class="panel-heading" style="background: #732626; color: white;">
      	<h2>
			<i class='fas fa-exclamation-circle' 
			 style='font-size:36px;color:white;margin: 0 15px 0 15px;'></i>
      		서버 내부 처리 오류
      	</h2>
      </div>
      <div class="panel-body">
	      <ul class="list-group">
			  <li class="list-group-item">
			  	에러코드 : <%= code %> - JRE 처리 오류
			  </li>
			  <li class="list-group-item">메시지 : <%= msg %></li>
			  <li class="list-group-item">예외클래스 : <%= eClass %></li>
			  <li class="list-group-item">
			  	조치 : 다시 한번 시도해 보세요.<br>
			  	계속 오류가 나면 전산 담당자에게 연락해 주세요.
			  </li>
			</ul>
	  </div>
      <div class="panel-footer" style="padding: 10px;">
      	<a href="/" class="btn btn-success">main으로</a>
      </div>
	</div>
</div>
</body>
</html>