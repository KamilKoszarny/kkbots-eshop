<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Register" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<div id="registerWrapper">
	    	<form method="post" action="register">
	    		<span class="registerCaption">Login*: </span>
	    		<input class="registerTextField" type="text" name="login" title="Login" required="required"/><br/>
	    		<span class="registerCaption">Password*: </span>
	    		<input class="registerTextField" type="password" name="password" title="password" required="required"/><br/>
	    		<span class="registerCaption">First name*: </span>
	    		<input class="registerTextField" type="text" name="firstname" title="First name" required="required"/><br/>
	    		<span class="registerCaption">Last name*: </span>
	    		<input class="registerTextField" type="text" name="lastname" title="Last name" required="required"/><br/>
	    		<span class="registerCaption">E-mail*: </span>
	    		<input class="registerTextField" type="text" name="email" title="E-mail" required="required"/><br/>
	    		<span class="registerCaption">Phone: </span>
	    		<input class="registerTextField" type="text" name="phone" title="Phone" /><br/>
	    		<span class="registerCaption">Address: </span>
	    		<input class="registerTextField" type="text" name="address" title="Address" /><br/>
	    		<input class="logButton" id="registerButton" type="submit" value="Register" /><br/><br/>
	    		Fields marked with * are required.
	    	</form>
    	</div>
    </jsp:attribute>
</t:genericpage>