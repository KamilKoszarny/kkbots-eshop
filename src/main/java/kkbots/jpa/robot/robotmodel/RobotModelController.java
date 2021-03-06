package kkbots.jpa.robot.robotmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RobotModelController {

	@Autowired
	RobotModelService robotModelService;
	

	@RequestMapping("/shop")
	public String shop(Model model) {
		robotModelService.updateStockAndInProduction();
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		model.addAttribute("robotmodels", robotModels);
		
		return "shop";
	}
	
	
	@RequestMapping("/robotmodels")
	public List<RobotModel> getAllRobotModels() {
		return robotModelService.getAllRobotModels();
	}
	
	
	@RequestMapping("/robotmodels/{model}")
	public RobotModel getRobotModelByModel(@PathVariable String model) {
		return robotModelService.getRobotModel(model);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robotmodels")
	public void addRobotModel(@RequestBody RobotModel robotModel) {
		robotModelService.addRobotModel(robotModel);
	}
}
