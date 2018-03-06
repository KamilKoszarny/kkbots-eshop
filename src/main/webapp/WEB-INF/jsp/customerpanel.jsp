<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Panel" name="title"/>
</jsp:include>
<t:genericpage>
    <jsp:attribute name="main">
		<h2>Customer panel</h2>
		<h3>Login: ${user.login}</h3>
		<h3>Full name: ${user.name} ${user.surname}</h3>
		<h4><a href="shop">Shop with robots</a></h4>
		<h4><a href="orders">My orders</a></h4>
		<form method="get" action="logout">
			<input type="submit" value="Log out" />
		</form>
		
		<script>
		    window.onload = activeMenuItem(2, 0, '');
		</script>		
    </jsp:attribute>
</t:genericpage>