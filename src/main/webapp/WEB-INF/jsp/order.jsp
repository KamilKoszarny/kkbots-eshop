<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Order" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<c:if test="${basketbymodels != null}">
    		<h2>Your current order: </h2>
	    	<c:forEach items="${basketbymodels}" var ="robotmodel">
		    	<section class="shopItem">
					<div class="shopItemIMGDiv">
						<img class="shopItemIMG" src="/resources/img/${robotmodel.model}.jpg" />
						<div class="shopItemIMGCaption">${robotmodel.model}</div>
					</div>
					<div class="shopItemText">
						<h4 class="shopItemTitle">${robotmodel.model}</h4>
						<div class="shopItemDescription">${robotmodel.description}</div>
					</div>
					<div class="shopItemPricing">
						<b>$${robotmodel.price} each</b> <br/>
			          	<b>${robotmodel.robotCount}</b> in order <br/>
			          	<b>Total: $${robotmodel.price * robotmodel.robotCount}</b>
			          	<form method="post" action="removeone">
							<input type="hidden" name="robotmodel" value="${robotmodel}" />
							<input class="shopItemB" type="submit" value="Remove one"/>
						</form>
					</div>
					<div style="clear: both;"> </div>
				</section>
	    	</c:forEach>
	    	<div style="clear: both;"> </div>
	    	<h2>Total: $${sumPrice}</h2>
	    	<form method="post" action="order">
	    		<input class="basketButton" type="submit" value="Confirm order"/>
	    	</form>
	    	<form method="post" action="cancelorder">
	    		<input class="basketButton" id="logoutButton" type="submit" value="Cancel order"/>
	    	</form>
    	</c:if>
    	<c:if test="${basketbymodels == null}">
    		No items in your basket. <br/>
    		<form method="get" action="shop">
	    		<input class="basketButton" type="submit" value="Go to shop"/>
	    	</form>
    	</c:if>
    	
    	<script>
		    window.onload = activeMenuItem(1, 1, '');
		</script>	
		
    </jsp:attribute>
</t:genericpage>