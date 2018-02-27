<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
    	response.sendRedirect("welcome");
	if(user.getRole().equals("admin"))
		response.sendRedirect("adminpanel");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer panel</title>
</head>
<body>
	<h2>Customer panel</h2>
	<h3>Login: ${user.login}</h3>
	<h3>Full name: ${user.name} ${user.surname}</h3>
	<h4><a href="<%= request.getContextPath()%>/orders">My orders</a></h4>
	<h4><a href="shop">Shop with robots</a></h4>
	<form method="get" action="logout">
		<input type="submit" value="Log out" />
	</form>
</body>
</html>