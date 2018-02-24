 package kkbots.jpa.robot.robotmodel;

import org.springframework.data.repository.CrudRepository;

public interface RobotModelRepository extends CrudRepository<RobotModel, Long>{

	RobotModel findByModel(String model);
}