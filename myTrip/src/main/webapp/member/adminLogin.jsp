<%@page import="com.mytrip.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginVO vo = new LoginVO();
	vo.setId("admin");
	vo.setName("이민구");
	vo.setGradeNo(9);
	vo.setGradeName("관리자");
	
	session.setAttribute("login", vo);
	
	response.sendRedirect("/board/list.do");
%>