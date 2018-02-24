<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
    	response.sendRedirect("welcome");
	if(user.getRole().equals("customer"))
		response.sendRedirect("robots");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add robot</title>
</head>

<body>

	<h2>Add robot</h2>
	<h3>Insert values:</h3>
	<form method="post" action="addrobot">
		Model:<br/>
		<select name="model">
			<c:forEach items="${robotmodels}" var="robotmodel" >
		  		<option value="${robotmodel}">${robotmodel.model}</option>
		  	</c:forEach>
		</select>
		<br/>Status:<br/>
		<select name="status">
		  	<c:forEach items="${robotstatuses}" var="robotstatus" >
		  		<option value="${robotstatus}">${robotstatus}</option>
		  	</c:forEach>
		</select>
		<br />
		<input type="submit" value="ADD" />
	</form>
	
	<h4><a href="<%= request.getContextPath()%>/robots">List of robots</a></h4>

</body>
</html>