<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="upload.jsp" method="post" enctype="multipart/form-data">
제목 : <input name = "tktle"><br>
첨부파일 : <input type="file" name="imageFile"><br>
<button> 파일 올리기 </button>
</form>
</body>
</html>