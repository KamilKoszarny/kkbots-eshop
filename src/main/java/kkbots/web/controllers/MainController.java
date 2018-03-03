package kkbots.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.robotmodel.RobotModel;

@Controller
public class MainController {
	
	@RequestMapping(value={"/", "/welcome"})
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value={"/index"})
	public String index(HttpSession session, Model model) {
		double sumPrice = 0; 
		int robotCount = 0;
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket"); 
			if(basket == null) basket = new ArrayList<Robot>();
		
			sumPrice = 0;
			Map<RobotModel, Integer> robotModelMap = new HashMap<>();
			for(Robot robot: basket){
				RobotModel robotModel = robot.getRobotModel();
				robotCount = 0;
				for(Robot nextRobot: basket)
					if(nextRobot.getRobotModel().getModel().equals(robotModel.getModel())) 
						robotCount++;
				if(!robotModelMap.keySet().contains(robotModel))
					robotModelMap.put(robotModel, robotCount);
			}
			
			List<RobotModel> basketByModels = new ArrayList<>();
			for(Map.Entry<RobotModel, Integer> entry : robotModelMap.entrySet()) {
				RobotModel robotModel = entry.getKey();
			    robotCount = entry.getValue();
			    sumPrice += robotCount * robotModel.getPrice();
			    robotModel.setRobotCount(robotCount);
			    basketByModels.add(robotModel);
			}
			
			model.addAttribute("basketbymodels", basketByModels);
			model.addAttribute("sumprice", sumPrice);
		return "index";
	}
}
