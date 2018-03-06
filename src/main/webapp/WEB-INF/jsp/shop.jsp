<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../tags/header.jsp" >
	<jsp:param value="Shop" name="title"/>
</jsp:include>

<t:genericpage>
    <jsp:attribute name="main">
    	<c:forEach items="${robotmodels}" var ="robotmodel">
	    	<section class="shopItem">
				<div class="shopItemIMGDiv">
					<a href="shop/robots/${robotmodel.model}">
						<img class="shopItemIMG" src="/resources/img/${robotmodel.model}.jpg" />
					</a>
						<div class="shopItemIMGCaption">${robotmodel.model}</div>
				</div>
				<div class="shopItemText">
					<h4 class="shopItemTitle"><a href="shop/robots/${robotmodel.model}">${robotmodel.model}</a></h4>
					<div class="shopItemDescription">${robotmodel.description}</div>
				</div>
				<div class="shopItemPricing">
					<div class="shopItemPriceSale">$${robotmodel.price + 100}</div>
					<div class="shopItemPrice">$${robotmodel.price}</div>
						<c:choose>
		          			<c:when test="${robotmodel.stock > 0}">
		          				<div class="shopItemStackIs">Available:</div>
		          				<b>${robotmodel.stock}</b> in stock
		          			</c:when>
		          			<c:when test="${robotmodel.inProduction > 0}">
		          				<div class="shopItemStackIsInProduction">Available:</div>
		          				<b>${robotmodel.inProduction}</b> in production
		          			</c:when>
		          			<c:otherwise>
		          				<div class="shopItemStackIsNot">No robots available :(</div>
		          			</c:otherwise>
		          		</c:choose>
					<form method="post" action="addtobasket">
						<input type="hidden" name="robotmodel" value="${robotmodel}" />
						<c:choose>
							<c:when test="${user == null && robotmodel.stock > 0}">
								Log in to order
							</c:when>
							<c:when test="${user == null && robotmodel.inProduction > 0}">
								Log in to reserve
							</c:when>
		          			<c:when test="${robotmodel.stock > 0}">
								<input class="shopItemB" type="submit" value="Add to basket"/>
							</c:when>
		          			<c:when test="${robotmodel.inProduction > 0}">
		          				<input class="shopItemB" type="submit" value="Reserve in basket"/>
		          			</c:when>
		          			<c:otherwise>
		          			</c:otherwise>
		          		</c:choose>
					</form>
				</div>
				<div style="clear: both;"> </div>
			</section>
    	</c:forEach>
    	<div style="clear: both;"> </div>	
    	<script>
	    	window.onload = activeMenuItem(1, 0, '');
		</script>	
    </jsp:attribute>
</t:genericpage>