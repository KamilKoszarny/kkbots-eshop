package kkbots;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kkbots.jpa.order.Order;
import kkbots.jpa.order.OrderService;
import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotStatus;

public class TestOrderServiceModule {
	
	OrderService orderService = new OrderService();

	@Test
	public void testGetLeastReadyRobotStatus1() {
		List<Robot> robots = new ArrayList<>();
		robots.add(new Robot(RobotStatus.READY));
		robots.add(new Robot(RobotStatus.TESTS));
		robots.add(new Robot(RobotStatus.PRODUCTION));
		robots.add(new Robot(RobotStatus.DESIGN));
		robots.add(new Robot(RobotStatus.CONCEPT));
		Order order = new Order(robots);
		
		assertEquals(RobotStatus.CONCEPT, orderService.getLeastReadyRobotStatus(order));
	}
	
	@Test
	public void testGetLeastReadyRobotStatus2() {
		List<Robot> robots = new ArrayList<>();
		robots.add(new Robot(RobotStatus.PRODUCTION));
		Order order = new Order(robots);
		
		assertEquals(RobotStatus.PRODUCTION, orderService.getLeastReadyRobotStatus(order));
	}
}
