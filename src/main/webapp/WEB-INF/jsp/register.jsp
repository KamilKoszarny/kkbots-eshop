<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Register" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<div id="registerWrapper">
    		<h3>Hello! Please register</h3>
	    	<form method="post" action="register">
	    		<span class="registerCaption">Login*: </span>
	    		<input class="registerTextField" type="text" name="login" title="Login" required value="${userInfo.login}"
	    				pattern=".{6,}" oninvalid="this.setCustomValidity('6 characters minimum')"
	    				onchange="try{setCustomValidity('')}catch(e){}"/><br/>
	    		<span class="registerCaption">Password*: </span>
	    		<input class="registerTextField" type="password" name="password" title="password" required
	    				pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" oninvalid="this.setCustomValidity('6 characters, one number, one lowercase, one uppercase minimum')"
	    				onchange="try{setCustomValidity('')}catch(e){}"/><br/>
	    		<span class="registerCaption">First name*: </span>
	    		<input class="registerTextField" type="text" name="firstname" title="First name" required value="${userInfo.name}"/><br/>
	    		<span class="registerCaption">Last name*: </span>
	    		<input class="registerTextField" type="text" name="lastname" title="Last name" required/><br/>
	    		<span class="registerCaption">E-mail*: </span>
	    		<input class="registerTextField" type="email" name="email" title="E-mail" required value="${userInfo.surname}"
	    				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" oninvalid="this.setCustomValidity('not correct e-amil address')"
	    				onchange="try{setCustomValidity('')}catch(e){}"/><br/>
	    		<span class="registerCaption">Phone: </span>
	    		<input class="registerTextField" type="text" name="phone" title="Phone" value="${userInfo.email}"
	    				pattern=".{8,}" oninvalid="this.setCustomValidity('8 numbers minimum')"
	    				onchange="try{setCustomValidity('')}catch(e){}"
	    				onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 0'/><br/>
	    		<span class="registerCaption">Address: </span>
	    		<input class="registerTextField" type="text" name="address" title="Address" value="${userInfo.phone}"/><br/>
	    		<input class="logButton" id="registerButton" type="submit" value="Register" value="${userInfo.address}"/><br/><br/>
	    		Fields marked with * are required.
	    	</form>
    	</div>
    </jsp:attribute>
</t:genericpage>