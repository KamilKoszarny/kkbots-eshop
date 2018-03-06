<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
		response.sendRedirect("welcome");
	if(user.getRole().equals("customer"))
		response.sendRedirect("shop");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Robots list</title>
</head>
<body>
	<h3>${title}</h3>
	
	<div>
      <table border="1">
        <tr>
          <th>Id</th>
          <th>Model</th>
          <th>Status</th>
          <th>Order id</th>
	      <th>Edit</th>
	      <th>Delete</th>
        </tr>
        <c:forEach  items="${robotslist}" var ="robot">
	        <tr>
	          	<td onclick="document.location = '<%= request.getContextPath()%>/robots/${robot.id}';" style="color: #382b91; cursor: pointer;">${robot.id}</td>
	          	<td>${robot.robotModel.model}</td>
	          	<td>${robot.status}</td>
	          	<td>${robot.order.id}</td>
	          	<td>
					<form method="get" action="<%= request.getContextPath()%>/robots/${robot.id}/edit">
						<input type="submit" value="edit">
					</form>
				</td>
				<td>
					<%! String message = ""; %>
					<c:if test="${robot.order.id == NULL}">
						<% message = "Are you sure to delete this robot from database?"; %>
					</c:if>
					<c:if test="${robot.order.id != NULL}">
						<% message = "You can not delete orderer robot from database!"; %>
					</c:if>
					<form method="get" action="<%= request.getContextPath()%>/robots/${robot.id}"
							onsubmit="return confirm('<%= message%>')">
						<input type="hidden" name="delete" value="true">
						<input type="submit" value="delete">
					</form>
				</td>
	        </tr>
        </c:forEach>
      </table>
    </div>
    
    <h4><a href="<%= request.getContextPath()%>/addrobot">Add robot</a></h4>
    <h4><a href="<%= request.getContextPath()%>/adminpanel">My panel</a></h4>
	
</body>
</html>