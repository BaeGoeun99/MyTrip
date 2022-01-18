<%@page import="com.mytrip.member.service.MemberAdminDeleteService"%>
<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");

ExecuteService.execute(new MemberAdminDeleteService(), id);

response.sendRedirect("list.jsp");
%>