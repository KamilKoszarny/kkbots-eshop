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
						<div class="shopItemPrice">$${robotmodel.price} each</div>
			          	<b>${robotmodel.robotCount}</b> in order <br/>
			          	<b>Total: $${robotmodel.price * robotmodel.robotCount}</b>
					</div>
					<div style="clear: both;"> </div>
				</section>
	    	</c:forEach>
	    	<div style="clear: both;"> </div>
	    	<h2>Total: ${sumPrice}</h2>
	    	<form method="post" action="order">
	    		<input class="basketButton" type="submit" value="Confirm order"/>
	    	</form>
    	</c:if>
    	<c:if test="${basketbymodels == null}">
    		No items in your basket. <br/>
    		<a href="shop">Go to shop</a>
    	</c:if>
    	
    	<script>
		    window.onload = activeMenuItem(1, 0, '');
		</script>	
		
    </jsp:attribute>
</t:genericpage>