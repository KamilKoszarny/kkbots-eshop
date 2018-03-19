package kkbots.jpa.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkbots.jpa.user.User;
import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotStatus;
import kkbots.jpa.robot.robotmodel.RobotModel;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}
	
	public Order getOrder(Long id) {
		return orderRepository.findOne(id);
	}
	
	public List<Order> getOrdersByCustomer(User user) {
		return orderRepository.findAllByCustomer(user);
	}
	
	public void addOrder(Order order) {
		orderRepository.save(order);
	}
	
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}
	
	public void updateOrdersStatusByRobotsAvailability(List<Order> orders) {
		orders.forEach(order->{
			OrderStatus orderStatusBefore = order.getStatus();
			
			if(orderStatusBefore.equals(OrderStatus.COMPLETING)) {
				RobotStatus lRRStatus = getLeastReadyRobotStatus(order);
				if(!lRRStatus.equals(RobotStatus.READY))
					order.setStatus(OrderStatus.COMPLETING);
				else
					order.setStatus(OrderStatus.READY);
			}
			
			if (!order.getStatus().equals(orderStatusBefore))
				order.setStatusDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			updateOrder(order);
		});
	}
	
	public RobotStatus getLeastReadyRobotStatus(Order order) {
		List<Robot> robots = order.getRobots();
		RobotStatus lRRStatus = RobotStatus.values()[RobotStatus.values().length - 1];
		List<RobotStatus> statuses = new ArrayList<>();
		
		robots.forEach(robot->{
			statuses.add(robot.getStatus());
		});
		
		for(RobotStatus status: statuses) {
			if (status.ordinal() < lRRStatus.ordinal())
				lRRStatus = status;
		}
		
		return lRRStatus;
	}
	
	public void updateBasket(HttpSession session) {
		double sumPrice = 0; 
		int robotCount = 0;
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket"); 
		if(basket == null) basket = new ArrayList<Robot>();
	
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
		
		List<RobotModel> basketByModels = new ArrayList<>();
		for(Map.Entry<RobotModel, Integer> entry : robotModelMap.entrySet()) {
			RobotModel robotModel = entry.getKey();
		    robotCount = entry.getValue();
		    sumPrice += robotCount * robotModel.getPrice();
		    robotModel.setRobotCount(robotCount);
		    basketByModels.add(robotModel);
		}
		
		session.setAttribute("basketbymodels", basketByModels);
		session.setAttribute("sumprice", sumPrice);
	}
	
	public void deleteOrder(Long id) {
		orderRepository.delete(id);
	}
}
