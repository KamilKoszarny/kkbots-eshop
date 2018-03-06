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
		<h3>Please log in to see your panel</h3>
		<script>
		    window.onload = activeMenuItem(2, 0, '');
		</script>		
    </jsp:attribute>
</t:genericpage>