package kkbots.jpa.robot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkbots.jpa.order.Order;
import kkbots.jpa.order.OrderRepository;
import kkbots.jpa.robot.robotmodel.RobotModel;
import kkbots.jpa.user.User;

@Service
public class RobotService {

	@Autowired
	private RobotRepository robotRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Robot> getAllRobots() {
		List<Robot> robots = new ArrayList<>();
		robotRepository.findAll().forEach(robots::add);
		return robots;
	}
	
	public Robot getRobot(Long id) {
		return robotRepository.findOne(id);
	}
	
	public Robot getRobotByModel(String strModel) {
		RobotModel robotModel = new RobotModel(strModel);
		return robotRepository.findAllByRobotModel(robotModel).get(0);
	}
	
	public Robot getAvailableRobot(RobotModel model) {
		List<Robot> robots;
		RobotStatus[] statuses = RobotStatus.values();
		RobotStatus status;
		for(int i = statuses.length - 1; i >= 0; i--) {
			status = statuses[i];
			robots = robotRepository.findAllByRobotModelAndStatusAndOrderAndInBasket(model, status, null, false);
			if (!robots.isEmpty())
				return robots.get(0);
		}
		return null;
	}
	
	public void putInBasket(Robot robot) {
		robot.setInBasket(true);
		updateRobot(robot);
	}
	
	public void removeFromBasket(Robot robot) {
		robot.setInBasket(false);
		updateRobot(robot);
	}
	
	public void setOrdered(Robot robot, Order order) {
		robot.setOrder(order);
		updateRobot(robot);
	}
	
	public List<Robot> getRobotsByOrder(Order order) {
		return robotRepository.findAllByOrder(order);
	}
	
	public List<Robot> getRobotsByUser(User user) {
		List<Order> orders = orderRepository.findAllByCustomer(user);
		List<Robot> robots = new ArrayList<>();
		orders.forEach(order->{
			robots.addAll(robotRepository.findAllByOrder(order));
		});
		return robots;
	}
	
	public void addRobot(Robot robot) {
		robotRepository.save(robot);
	}
	
	public void updateRobot(Robot robot) {
		robotRepository.save(robot);
	}
	
	public void deleteRobot(Long id) {
		robotRepository.delete(id);
	}
}
