<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Robots" name="title"/>
</jsp:include>
<t:genericpage>
    <jsp:attribute name="main">
		<h3>${title}</h3>
		
		<div>
	      <table border="1">
	        <tr>
	          <th>Id</th>
	          <th>Model</th>
	          <th>Status</th>
	          <th>Order id</th>
	          <c:if test="${user.role == 'admin'}" >
			      <th>Edit</th>
			      <th>Delete</th>
		      </c:if>
	        </tr>
	        <c:forEach  items="${robotslist}" var ="robot">
		        <tr>
		          	<td onclick="document.location = '/robots/${robot.id}';" style="color: #382b91; cursor: pointer;">${robot.id}</td>
		          	<td>${robot.robotModel.model}</td>
		          	<td>${robot.status}</td>
		          	<td>${robot.order.id}</td>
		          	<c:if test="${user.role == 'admin'}" >
			          	<td>
							<form method="get" action="/robots/${robot.id}/edit">
								<input type="submit" value="edit">
							</form>
						</td>
						<td>
							<c:set var="message" value=""/>
							<c:if test="${robot.order.id == NULL}">
								<c:set var="message" value="Are you sure to delete this robot from database?" />
							</c:if>
							<c:if test="${robot.order.id != NULL}">
								<c:set var="message" value="You can not delete ordered robot from database!" />
							</c:if>
							<form method="get" action="/robots/${robot.id}" onsubmit="return confirm('${message}')">
								<input type="hidden" name="delete" value="true">
								<input type="submit" value="delete">
							</form>
						</td>
					</c:if>
		        </tr>
	        </c:forEach>
	      </table>
	    </div>
	    
	    <h4><a href="addrobot">Add robot</a></h4>
	    <h4><a href="panel">My panel</a></h4>
    	
    	<script>
		    window.onload = activeMenuItem(2, 1, '');
		</script>	
    </jsp:attribute>
</t:genericpage>