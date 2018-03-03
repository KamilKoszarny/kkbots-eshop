<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Orders" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<h2>Your orders history: </h2>
    	<c:forEach items="${user.orders}" var="order">
    		<div class="order">
	    		<h3>Order id ${order.id}</h3>
				<c:if test="${user.role == 'admin'}">
					<h4>Customer id: ${order.customer.id}</h4>
					<h5>	Full name: ${order.customer.name} ${order.customer.surname}</h5>
				</c:if>
				<h4>Placed: ${order.date}</h4>
				<h4>Status: ${order.status}</h4>
				<h4>Status date: ${order.statusDate}</h4>
				
		    	<c:if test="${order.orderByModels != null}">
			    	<c:forEach items="${order.orderByModels}" var ="robotmodel">
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
			    	<h2>Total: ${order.orderSumPrice}</h2>
		    	</c:if>
	    	</div>
    	</c:forEach>
    </jsp:attribute>
</t:genericpage>