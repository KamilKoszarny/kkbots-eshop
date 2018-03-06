<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if(session.getAttribute("user") == null)
    	response.sendRedirect("welcome");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Robot</title>
</head>
<body>
	<h3>${title}</h3>
	<c:if test="${user.role == 'admin'}">
		<h4>Id: ${robot.id}</h4>
		<h4>Model: ${robot.robotModel.model}</h4>
		<h4>Status: ${robot.status}</h4>
		<h4>Status date: ${robot.statusDate}</h4>
		<h3>Robot model details:</h3>
	</c:if>
	
	<h4>Motion: ${robot.robotModel.motion}</h4>
	<h4>Function: ${robot.robotModel.function}</h4>
	<h4>Description: ${robot.robotModel.description}</h4>
	<h4>Price: ${robot.robotModel.price}</h4>
    <h4>Stock: ${robot.robotModel.stock}</h4>
    <h4>In production: ${robot.robotModel.inProduction}</h4>
    <h4>Available: ${robot.robotModel.whenReady}</h4>
    
    <c:if test="${user.role == 'admin'}">
	    <form method="get" action="<%= request.getContextPath()%>/robots/${robot.id}/edit">
			<input type="submit" value="EDIT">
		</form>
	    <form method="get" action="<%= request.getContextPath()%>/robots/${robot.id}"
				onsubmit="return confirm('Are you sure to delete this robot from database?')">
			<input type="hidden" name="delete" value="true">
			<input type="submit" value="DELETE">
		</form>
	</c:if>
	<c:if test="${user.role == 'customer'}">
		<c:choose>
        	<c:when test="${robot.robotModel.stock > 0}">
	      		<form method="post" action="addtobasket">
	      			<input type="hidden" name="robotmodel" value="${robotmodel}" />
	      			<input type="submit" value="Add to basket" />
	      		</form>
     		</c:when>
     		<c:when test="${robot.robotModel.inProduction > 0}">
	      		<form method="post" action="addtobasket">
	      			<input type="hidden" name="robotmodel" value="${robotmodel}" />
	      			<input type="submit" value="Reserve in basket" />
	      		</form>
     		</c:when>
     		<c:otherwise>
      			No robots available at this moment;
     		</c:otherwise>
    	</c:choose>
	</c:if>
					
    <h4><a href="<%= request.getContextPath()%>/robots">List of robots</a></h4>
    <h4><a href="<%= request.getContextPath()%>/adminpanel">My panel</a></h4>
	
</body>
</html>