package kkbots.jpa.robot.robotmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotModelService {

	@Autowired
	private RobotModelRepository robotModelRepository;
	
	
	public List<RobotModel> getAllRobotModels() {
		List<RobotModel> robotModels = new ArrayList<>();
		robotModelRepository.findAll().forEach(robotModels::add);
		return robotModels;
	}
	
	public void increaseStock(RobotModel robotModel) {
		changeStock(robotModel, 1);
	}
	
	public void decreaseStock(RobotModel robotModel) {
		changeStock(robotModel, -1);
	}
	
	private void changeStock(RobotModel robotModel, int change) {
		RobotModel model = robotModelRepository.findByModel(robotModel.getModel());
		int stock = model.getStock();
		stock += change;
		model.setStock(stock);
		updateRobotModel(model, model.getModel());
	}
	

	public void updateRobotModel(RobotModel robotModel, String model) {
		robotModelRepository.save(robotModel);
	}
	
	public RobotModel getRobotModel(String model) {
		return robotModelRepository.findByModel(model);
	}
	
	public void addRobotModel(RobotModel robotModel) {
		robotModelRepository.save(robotModel);
	}
	
	
}
