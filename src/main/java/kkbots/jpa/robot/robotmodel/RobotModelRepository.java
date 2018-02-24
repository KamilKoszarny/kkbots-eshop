 package kkbots.jpa.robot.robotmodel;

import org.springframework.data.repository.CrudRepository;

public interface RobotModelRepository extends CrudRepository<RobotModel, String>{

	RobotModel findByModel(String model);
}