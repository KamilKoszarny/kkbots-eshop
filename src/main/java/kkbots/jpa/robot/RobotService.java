package kkbots.jpa.robot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkbots.jpa.robot.robotmodel.RobotModel;

@Service
public class RobotService {

	@Autowired
	private RobotRepository robotRepository;
	
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
		return robotRepository.findByRobotModelAndStatus(model, RobotStatus.READY).get(0);
	}
	
	public void addRobot(Robot robot) {
		robotRepository.save(robot);
	}
	
	public void updateRobot(Robot robot, Long id) {
		robotRepository.save(robot);
	}
	
	public void deleteRobot(Long id) {
		robotRepository.delete(id);
	}
}
