<%@page import="kkbots.jpa.user.User"%>
<%@page import="org.springframework.web.util.UrlPathHelper"%>
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
<title>Edit robot</title>
</head>

<body>
	<h1>${user.role}</h1>

	<h2>Edit robot</h2>
	<h3>Robot id: ${id}</h3>
	<h3>Insert values to change:</h3>
	<form method="post" action="edit">
		Model:<br/>
		<select name="model">
			<c:forEach items="${robotmodels}" var="robotmodel" >
				<% String thisRobotModel = request.getAttribute("thisrobotmodel").toString();
				String robotModel = pageContext.getAttribute("robotmodel").toString();
				if(robotModel.equals(thisRobotModel)) { %>
		  		<option value="${robotmodel}" selected>${robotmodel.model}</option>
		  		<% } else { %>
		  		<option value="${robotmodel}">${robotmodel.model}</option>
		  		<% } %>
		  	</c:forEach>
		</select>

		<br/>Status:<br/>
		<select name="status">
		  	<c:forEach items="${robotstatuses}" var="robotstatus" >
		  		<% String thisRobotStatus = request.getAttribute("thisrobotstatus").toString();
				String robotStatus = pageContext.getAttribute("robotstatus").toString();
				if(robotStatus.equals(thisRobotStatus)) { %>
		  		<option value="${robotstatus}" selected>${robotstatus}</option>
		  		<% } else { %>
		  		<option value="${robotstatus}">${robotstatus}</option>
		  		<% } %>
		  	</c:forEach>
		</select>
		<br />
		<input type="submit" value="CHANGE" />
	</form>
	
	<h4><a href="<%= request.getContextPath()%>/robots">List of robots</a></h4>

</body>
</html>