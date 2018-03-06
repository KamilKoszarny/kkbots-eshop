<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Robot ${robot.robotModel.model}" name="title"/>
</jsp:include>
<t:genericpage>
    <jsp:attribute name="main">
		<h2>${title}</h2><img class="shopItemIMG" src="/resources/img/${robot.robotModel.model}.jpg" />
		<c:if test="${user.role == 'admin'}">
			<h4>Id: ${robot.id}</h4>
			<h4>Status: ${robot.status}</h4>
			<h4>Status date: ${robot.statusDate}</h4>
			<h3>Robot model details:</h3>
		</c:if>
		
		<h3>Model: ${robot.robotModel.model}</h3>
		<h4>Motion: ${robot.robotModel.motion}</h4>
		<h4>Function: ${robot.robotModel.function}</h4>
		<h4>Description: ${robot.robotModel.description}</h4>
		<h4>Price: $${robot.robotModel.price}</h4>
	    <h4>Stock: ${robot.robotModel.stock}</h4>
	    <h4>In production: ${robot.robotModel.inProduction}</h4>
	    <h4>Available: ${robot.robotModel.whenReady}</h4>
	    
	    <c:if test="${user.role == 'admin'}">
		    <form method="get" action="../robots/${robot.id}/edit">
				<input type="submit" value="EDIT">
			</form>
			<c:set var="message" value=""/>
			<c:if test="${robot.order.id == NULL}">
				<c:set var="message" value="Are you sure to delete this robot from database?" />
			</c:if>
			<c:if test="${robot.order.id != NULL}">
				<c:set var="message" value="You can not delete ordered robot from database!" />
			</c:if>
		    <form method="get" action="../robots/${robot.id}"
					onsubmit="return confirm('Are you sure to delete this robot from database?')">
				<input type="hidden" name="delete" value="true">
				<input type="submit" value="DELETE">
			</form>
		</c:if>
		<c:if test="${user.role == 'customer'}">
			<c:choose>
	        	<c:when test="${robot.robotModel.stock > 0}">
		      		<form method="post" action="../addtobasket">
		      			<input type="hidden" name="robotmodel" value="${robotmodel}" />
		      			<input type="submit" value="Add to basket" />
		      		</form>
	     		</c:when>
	     		<c:when test="${robot.robotModel.inProduction > 0}">
		      		<form method="post" action="../addtobasket">
		      			<input type="hidden" name="robotmodel" value="${robotmodel}" />
		      			<input type="submit" value="Reserve in basket" />
		      		</form>
	     		</c:when>
	     		<c:otherwise>
	      			No robots available at this moment;
	     		</c:otherwise>
	    	</c:choose>
		</c:if>
						
	    <h4><a href="../robots">List of robots</a></h4>
	    <h4><a href="../panel">My panel</a></h4>
    	
    	<script>
		    window.onload = activeMenuItem(2, 1, '');
		</script>	
    </jsp:attribute>
</t:genericpage>