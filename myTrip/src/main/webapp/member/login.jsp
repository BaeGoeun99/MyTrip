<%@page import="com.mytrip.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginVO vo = new LoginVO();
	vo.setId("test");
	vo.setName("홍길동");
	vo.setGradeNo(1);
	vo.setGradeName("일반회원");
	
	session.setAttribute("login", vo);
	
	response.sendRedirect("/board/list.do");
%>