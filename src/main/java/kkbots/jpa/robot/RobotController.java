package kkbots.jpa.robot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kkbots.jpa.robot.robotmodel.RobotModel;
import kkbots.jpa.robot.robotmodel.RobotModelService;

@Controller
public class RobotController {

	@Autowired
	RobotService robotService;
	@Autowired
	RobotModelService robotModelService;
	
	@RequestMapping("/robots")
	public String getAllRobots(Model model) {
		List<Robot> robotsList = new ArrayList<>();
		robotsList = robotService.getAllRobots();
		
		model.addAttribute("robotsList", robotsList);
		model.addAttribute("title", "All robots:");
		
		return "robots";
	}
	
	@RequestMapping("/robots/{id}")
	public String getRobotById(@PathVariable Long id, Model model, @RequestParam(name="delete", required=false) String delete) {
		
		if (delete != null) {
			robotService.deleteRobot(id);
			return getAllRobots(model);
		} else {
			Robot robot = robotService.getRobot(id);
			
			model.addAttribute("title", "This robot:");
			model.addAttribute("robot", robot);
			
			return "robot";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/addrobot")
	public String addRobot(Model model) {
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		RobotStatus[] robotStatuses = RobotStatus.values();
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		return "addrobot";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots")
	public ModelAndView addRobot(@RequestParam(name="model") String robotModel, @RequestParam(name="status") String robotStatus, Model model) {
		model.addAttribute("title", "Added robot:");
		Robot robot = new Robot(0l, robotModel, robotStatus);
		robotService.addRobot(robot);
		
		return new ModelAndView(new RedirectView("/robots"));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/robots/{id}/edit")
	public String updateRobot(Model model, @PathVariable Long id) {
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		RobotStatus[] robotStatuses = RobotStatus.values();
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		model.addAttribute("thisrobotstatus", robotService.getRobot(id).getStatus());
		model.addAttribute("id", id);
		
		return "editrobot";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots/{id}/edit", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void updateRobot(Robot robot, Model model, @PathVariable Long id, HttpServletResponse httpServletResponse) {
		robotService.updateRobot(robot, id);
		
		httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
		httpServletResponse.setHeader("Location", "/robots/" + id);
	}
	
}
