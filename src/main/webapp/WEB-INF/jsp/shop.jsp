<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
		response.sendRedirect("welcome");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop</title>
</head>
<body>
	<h1>Shop</h1>
	
	<h3>Offered models</h3>
	
	<div>
      <table border="1">
        <tr>
          <th>Model</th>
          <th>Details</th>
	      <th>Price</th>
	      <th>Stock</th>
	      <th>Available</th>
	      <th>Order</th>
	      <th>In basket</th>
        </tr>
        <c:forEach items="${robotmodels}" var ="robotmodel">
	        <tr>
	          	<td>${robotmodel.model}</td>
	          	<td onclick="document.location = 'shop/robots/${robotmodel.model}';" style="color: #382b91; cursor: pointer;">Details</td>
	          	<td>${robotmodel.price}</td>
	          	<td>${robotmodel.stock}</td>
	          	<td>${robotmodel.when_ready}</td>
	          	<td>
	          		<c:choose>
		          		<c:when test="${robotmodel.stock > 0}">
			          		<form method="post" action="addtobasket">
			          			<input type="hidden" name="robotmodel" value="${robotmodel}" />
			          			<input type="submit" value="Add to basket" />
			          		</form>
		          		</c:when>
		          		<c:otherwise>
			          		<form method="post" action="addtobasket">
			          			<input type="hidden" name="robotmodel" value="${robotmodel}" />
			          			<input type="submit" value="Add to reservation" />
			          		</form>
		          		</c:otherwise>
	          		</c:choose>
	          	</td>
	        </tr>
        </c:forEach>
      </table>
    </div>
    
    <h4><a href="<%= request.getContextPath()%>/customerpanel">My panel</a></h4>
	<h4><a href="<%= request.getContextPath()%>/basket">My basket</a></h4>
	
</body>
</html>