<%@page import="java.util.HashMap"%>
<%@page import="kkbots.jpa.robot.robotmodel.RobotModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kkbots.jpa.robot.Robot"%>
<%@page import="kkbots.jpa.order.Order"%>
<%@page import="java.util.List"%>
<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
		response.sendRedirect("welcome");
	
	double sumPrice = 0; int robotCount = 0;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<h1>${title}</h1>
		
	<c:forEach items="${user.orders}" var="order">
		<h3>Order nr ${order.id}</h3>
		<c:if test="${user.role == 'admin'}">
			<h4>Customer id: ${order.customer.id}</h4>
			<h5>	Full name: ${order.customer.name} ${order.customer.surname}</h5>
		</c:if>
		<h4>Placed: ${order.date}</h4>
		<h4>Status: ${order.status}</h4>
		<h4>Status date: ${order.statusDate}</h4>
		<c:set var="robots" value="${order.robots}" />
		<% List<Robot> robots = (List<Robot>)pageContext.getAttribute("robots"); %>
		<table border="1">
			<tr>
				<th>Model</th>
				<th>Price</th>
				<th>Qty</th>
				<th>Sum</th>
			</tr>
			<% 
			sumPrice = 0;
			Map<RobotModel, Integer> robotModelMap = new HashMap<>();
			for(Robot robot: robots){
				RobotModel robotModel = robot.getRobotModel();
				robotCount = 0;
				for(Robot nextRobot: robots)
					if(nextRobot.getRobotModel().getModel().equals(robotModel.getModel())) 
						robotCount++;
				if(!robotModelMap.keySet().contains(robotModel))
					robotModelMap.put(robotModel, robotCount);
			}
			for(Map.Entry<RobotModel, Integer> entry : robotModelMap.entrySet()) {
				RobotModel robotModel = entry.getKey();
			    robotCount = entry.getValue();
			    sumPrice += robotCount * robotModel.getPrice();
			%>
				<tr>
					<td onclick="document.location = 'shop/robots/<%= robotModel.getModel() %>';" style="color: #382b91; cursor: pointer;"><%= robotModel.getModel() %></td>
					<td><%= robotModel.getPrice() %></td>
					<td><%= robotCount %></td>
					<td><%= robotCount * robotModel.getPrice()%></td>
				</tr>
			<% } %>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><%= sumPrice %></td>
			</tr>
		</table >
		<c:if test="${order.status == 'READY' && user.role == 'customer'}" >
			<form method="post" action="pay">
				<input type="hidden" name="orderid" value="${order.id}" />
				<input type="submit" value="PAY" />
			</form>
		</c:if>
		<c:if test="${order.status == 'PAYMENT' && user.role == 'admin'}" >
			<form method="post" action="confirmpayment">
				<input type="hidden" name="orderid" value="${order.id}" />
				<input type="submit" value="CONFIRM PAYMENT" />
			</form>
		</c:if>
		<c:if test="${order.status == 'TO_SEND' && user.role == 'admin'}" >
			<form method="post" action="confirmsend">
				<input type="hidden" name="orderid" value="${order.id}" />
				<input type="submit" value="SEND" />
			</form>
		</c:if>
		<c:if test="${order.status == 'SEND' && user.role == 'customer'}" >
			<form method="post" action="confirmreceived">
				<input type="hidden" name="orderid" value="${order.id}" />
				<input type="submit" value="CONFIRM RECEIVED" />
			</form>
		</c:if>
		<br/>-----------------------------------------------------------------<br/>
	</c:forEach>
	
	

    
    <h4><a href="<%= request.getContextPath()%>/customerpanel">My panel</a></h4>
	<h4><a href="<%= request.getContextPath()%>/basket">My basket</a></h4>
	
</body>
</html>