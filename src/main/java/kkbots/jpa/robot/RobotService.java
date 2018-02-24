package kkbots.jpa.robot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
