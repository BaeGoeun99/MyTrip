<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>존재하지 않는 페이지</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="panel panel-danger" style="margin-top : 80px;">
      <div class="panel-heading"><h2>존재하지 않는 페이지 요청</h2></div>
      <div class="panel-body">
      	예외코드 : 404<br>
      	예외메시지 : 요청한 페이지가 존재하지 않습니다. URL이 *.do 이외에 페이지를 처리한다.
      </div>
      <div class="footer">
      	<a href="/board/list.do" class="btn btn-success">게시판(main으로 바꾼다.)</a>
      </div>
	</div>
</div>
</body>
</html>