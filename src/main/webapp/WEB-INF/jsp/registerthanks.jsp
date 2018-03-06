<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Register" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<div id="registerWrapper">
    		<h3>Thank you for your registration!</h3>
    		<h3>You can log in now.</h3>
    	</div>
    </jsp:attribute>
</t:genericpage>