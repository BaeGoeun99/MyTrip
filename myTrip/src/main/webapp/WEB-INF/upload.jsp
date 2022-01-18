<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = "/upload";
String savePath = request.getServletContext().getRealPath(path);
int maxSize = 1024 * 1024 *1024;
String encodingType = "utf-8";

MultipartRequest multi 
= new MultipartRequest(request, savePath, maxSize, encodingType, new DefaultFileRenamePolicy());

String originalFileName = multi.getOriginalFileName("imageFile");
String serverFileName = multi.getFilesystemName("imageFile");

//multipart 객체가 있는 순간부터 모든 데이터를 multipart에서부터 찾는다. request는 사용하지 않는다
String title = multi.getParameter("title");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
서버의 저장위치: <%= path %><br>
컴퓨터의 저장위치:<%= savePath %><br>
원래의 파일명 : <%= originalFileName %><br>
올라 파일명 : <%= serverFileName %><br>

제목 : <%= title %> <br>
올라간 이미지<br>
<!-- path(/upload) + "/" + 서버의 파일명 -->
<img alt="이미지입니다" src="<%= path + "/" + serverFileName %>">
<button>파일올리기</button>
</body>
</html>