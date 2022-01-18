<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@page import="com.mytrip.member.service.MemberDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");

ExecuteService.execute(new MemberDeleteService(), id);

response.sendRedirect("list.jsp");
%>