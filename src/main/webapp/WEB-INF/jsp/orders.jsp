<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Orders" name="title"/>
</jsp:include>
<t:genericpage>
    <jsp:attribute name="main">
    	<c:if test="${user.role == 'admin'}">
    		<h1>All orders: </h1>
    	</c:if>
    	<c:if test="${user.role == 'customer'}">
    		<h1>Your orders history: </h1>
    	</c:if>
    	<c:forEach items="${user.orders}" var="order">
    		<div class="order">
	    		<h2>Order id ${order.id}</h2>
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
		    	
				<c:if test="${order.status == 'READY' && user.role == 'customer'}" >
					<form method="post" action="pay">
						<input type="hidden" name="orderid" value="${order.id}" />
						<input type="submit" value="PAY" class="basketButton" />
					</form>
				</c:if>
				<c:if test="${order.status == 'PAYMENT' && user.role == 'admin'}" >
					<form method="post" action="confirmpayment">
						<input type="hidden" name="orderid" value="${order.id}" />
						<input type="submit" value="CONFIRM PAYMENT" class="basketButton" />
					</form>
				</c:if>
				<c:if test="${order.status == 'TO_SEND' && user.role == 'admin'}" >
					<form method="post" action="confirmsend">
						<input type="hidden" name="orderid" value="${order.id}" />
						<input type="submit" value="SEND" class="basketButton" />
					</form>
				</c:if>
				<c:if test="${order.status == 'SEND' && user.role == 'customer'}" >
					<form method="post" action="confirmreceived">
						<input type="hidden" name="orderid" value="${order.id}" />
						<input type="submit" value="CONFIRM RECEIVED" class="basketButton" />
					</form>
				</c:if>
		    	
		    	
	    	</div>
    	</c:forEach>
    	
    	<script>
		    window.onload = activeMenuItem(2, 2, '');
		</script>	
    </jsp:attribute>
</t:genericpage>