 package kkbots.jpa.robot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kkbots.jpa.order.Order;
import kkbots.jpa.robot.robotmodel.RobotModel;

public interface RobotRepository extends CrudRepository<Robot, Long>{

	List<Robot> findAllByRobotModel(RobotModel model);
	List<Robot> findAllByRobotModelAndStatusAndOrderAndInBasket(RobotModel model, RobotStatus status, Order order, boolean inBasket);
	List<Robot> findAllByOrder(Order order);
}