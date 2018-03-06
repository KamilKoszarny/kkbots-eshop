<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Edit robot" name="title"/>
</jsp:include>
<t:genericpage>
    <jsp:attribute name="main">
	<h2>Edit robot</h2>
	<h3>Robot id: ${robot.id}</h3>
	<h3>Insert values to change:</h3>
	<form method="post" action="edit">
		Model:<br/>
		<select name="model">
			<c:forEach items="${robotmodels}" var="robotmodel" >
				<c:if test="${robot.robotModel == robotmodel}">
		  			<option value="${robotmodel}" selected>${robotmodel.model}</option>
		  		</c:if>
		  		<c:if test="${robot.robotModel != robotmodel}">
		  			<option value="${robotmodel}">${robotmodel.model}</option>
		  		</c:if>
		  	</c:forEach>
		</select>

		<br/>Status:<br/>
		<select name="status">
		  	<c:forEach items="${robotstatuses}" var="robotstatus" >
		  		<c:if test="${robotstatus == robot.status}">
			  		<option value="${robotstatus}" selected>${robotstatus}</option>
			  	</c:if>
			  	<c:if test="${robotstatus != robot.status}">
			  		<option value="${robotstatus}">${robotstatus}</option>
		  		</c:if>
		  	</c:forEach>
		</select>
		<br />
		<input type="submit" value="CHANGE" />
	</form>
	
	<h4><a href="../robots">List of robots</a></h4>
    	
    	<script>
		    window.onload = activeMenuItem(2, 1, '');
		</script>	
    </jsp:attribute>
</t:genericpage>