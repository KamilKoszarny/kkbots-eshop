package kkbots.jpa.robot.robotmodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotRepository;
import kkbots.jpa.robot.RobotService;
import kkbots.jpa.robot.RobotStatus;

@Service
public class RobotModelService {

	@Autowired
	private RobotRepository robotRepository;
	@Autowired
	private RobotService robotService;
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
		updateRobotModel(model);
	}
	
	public void updateStockAndInProduction() {
		List<RobotModel> robotModels = getAllRobotModels();
		for(RobotModel model: robotModels) {
			int stock = robotRepository.findAllByRobotModelAndStatusAndOrderAndInBasket(model, RobotStatus.READY, null, false).size();
			int inProduction = 0;
			Date whenReady;
			Robot mostReadyRobot = robotService.getAvailableRobot(model);
			if (mostReadyRobot != null) {
				whenReady = mostReadyRobot.getStatusDate();
				int waitingDays = 0;
				boolean daysCounting = false;
				for(RobotStatus status: RobotStatus.values()) {
					if(status.equals(mostReadyRobot.getStatus()))
						daysCounting = true;
					if(daysCounting)
						waitingDays += status.getDuration();
				}
				Calendar c = Calendar.getInstance();
				c.setTime(whenReady);
				c.add(Calendar.DATE, waitingDays);
				whenReady = new Date(c.getTimeInMillis());
			} else 
				whenReady = null;
			
			for(RobotStatus status: RobotStatus.values()) 
				if (!(status == RobotStatus.READY))
					inProduction += robotRepository.findAllByRobotModelAndStatusAndOrderAndInBasket(model, status, null, false).size();
			
			model.setStock(stock);
			model.setInProduction(inProduction);
			model.setWhenReady(whenReady);
			updateRobotModel(model);
		}
	}
	

	public void updateRobotModel(RobotModel robotModel) {
		robotModelRepository.save(robotModel);
	}
	
	public RobotModel getRobotModel(String model) {
		return robotModelRepository.findByModel(model);
	}
	
	public void addRobotModel(RobotModel robotModel) {
		robotModelRepository.save(robotModel);
	}
	
	
}
