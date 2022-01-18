<%@page import="com.mytrip.diner.service.DinerDeleteService"%>
<%@page import="com.mytrip.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String noStr = request.getParameter("no");
Long no = Long.parseLong(noStr);

ExecuteService.execute(new DinerDeleteService(), no);

response.sendRedirect("list.jsp");


%>