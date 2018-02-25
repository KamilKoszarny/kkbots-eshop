 package kkbots.jpa.robot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kkbots.jpa.robot.robotmodel.RobotModel;

public interface RobotRepository extends CrudRepository<Robot, Long>{

	List<Robot> findAllByRobotModel(RobotModel model);
	List<Robot> findByRobotModelAndStatus(RobotModel model, RobotStatus status);
}