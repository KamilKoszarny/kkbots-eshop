<%@page import="kkbots.jpa.robot.robotmodel.RobotModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="kkbots.jpa.robot.Robot"%>
<%@page import="java.util.List"%>
<%@page import="kkbots.jpa.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	User user = (User)session.getAttribute("user");
	if(user == null)
		response.sendRedirect("welcome");
%>
<%! double sumPrice = 0; int robotCount = 0;%>
<% List<Robot> basket = (List<Robot>)session.getAttribute("basket"); 
	if(basket == null) basket = new ArrayList<Robot>();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basket</title>
</head>
<body>
	<h2>My basket:</h2>
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
		for(Robot robot: basket){
			RobotModel robotModel = robot.getRobotModel();
			robotCount = 0;
			for(Robot nextRobot: basket)
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
	
	<form method="post" action="order">
		<input type="submit" value="Place order" />
	</form>
	<h4><a href="<%= request.getContextPath()%>/shop">Back to shop</a></h4>
		
		
</body>
</html>