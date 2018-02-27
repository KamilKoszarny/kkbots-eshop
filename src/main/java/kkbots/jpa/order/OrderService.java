package kkbots.jpa.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkbots.jpa.user.User;
import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotStatus;

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
	
	public void updateOrdersStatus(User user) {
		List<Order> orders = getOrdersByCustomer(user);
		orders.forEach(order->{
			OrderStatus orderStatusBefore = order.getStatus();
			RobotStatus lRRStatus = getLeastReadyRobotStatus(order);
			order.setStatus(getOrderStatusByLeastReadyRobotStatus(lRRStatus));
			if (!order.getStatus().equals(orderStatusBefore))
				order.setStatusDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			updateOrder(order);
		});
	}
	
	private RobotStatus getLeastReadyRobotStatus(Order order) {
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
	
	private OrderStatus getOrderStatusByLeastReadyRobotStatus(RobotStatus lRRStatus) {
		if(!lRRStatus.isOrderSpecific() && !lRRStatus.equals(RobotStatus.READY))
			return OrderStatus.COMPLETING;
		else if (lRRStatus.equals(RobotStatus.READY))
			return OrderStatus.PAYMENT;
		else if (lRRStatus.equals(RobotStatus.TO_SEND))
			return OrderStatus.TO_SEND;
		else if (lRRStatus.equals(RobotStatus.SEND))
			return OrderStatus.SEND;
		else if (lRRStatus.equals(RobotStatus.SOLD))
			return OrderStatus.SOLD;
		return null;
	}
	
	public void deleteOrder(Long id) {
		orderRepository.delete(id);
	}
}
