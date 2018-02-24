 package kkbots.jpa.robot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RobotRepository extends CrudRepository<Robot, Long>{

	//List<Robot> findRobotsByModel(String model);
}