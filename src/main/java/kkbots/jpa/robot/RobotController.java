package kkbots.jpa.robot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kkbots.jpa.order.Order;
import kkbots.jpa.order.OrderService;
import kkbots.jpa.robot.robotmodel.RobotModel;
import kkbots.jpa.robot.robotmodel.RobotModelService;
import kkbots.jpa.user.User;

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
		
		model.addAttribute("robotslist", robotsList);
		model.addAttribute("title", "All robots:");
		
		return "robots";
	}
	
	@RequestMapping("/robots/{id}")
	public String getRobotById(@PathVariable Long id, Model model, @RequestParam(name="delete", required=false) String delete) {
		
		Robot robot = robotService.getRobot(id);
		
		if (delete != null) {
			if (robot.getStatus() == RobotStatus.READY && robot.getOrder() == null)
				robotModelService.decreaseStock(robot.getRobotModel());
			robotService.deleteRobot(id);
			return getAllRobots(model);
		} else {
			
			model.addAttribute("title", "This robot:");
			model.addAttribute("robot", robot);
			
			return "robot";
		}
	}
	
	@RequestMapping("/shop/robots/{robotModel}")
	public String getRobotById(@PathVariable String robotModel, Model model) {

		model.addAttribute("title", "Robot " + robotModel + " details");
		
		Robot robot = robotService.getRobotByModel(robotModel);
		
		model.addAttribute("robot", robot);
		
		
		return "robot";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addtobasket")
	public ModelAndView addToBasket(HttpSession session, @RequestParam(name="robotmodel") RobotModel robotModel) {
		List<Robot> basket = (List<Robot>)session.getAttribute("basket");
		if (basket == null)
			basket = new ArrayList<Robot>();
		
		Robot robot = robotService.getAvailableRobot(robotModel);
		robotService.putInBasket(robot);
		basket.add(robot);
		session.setAttribute("basket", basket);
		
		robotModelService.decreaseStock(robotModel);
		
		return new ModelAndView(new RedirectView("shop"));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/addrobot")
	public String addRobot(Model model) {
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		RobotStatus[] robotStatuses = RobotStatus.values();
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		return "addrobot";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addrobot")
	public ModelAndView addRobot(@RequestParam(name="model") RobotModel robotModel, @RequestParam(name="status") RobotStatus robotStatus, Model model) {
		model.addAttribute("title", "Added robot:");
		Robot robot = new Robot(0l, robotModel, robotStatus);
		
		robotService.addRobot(robot);
		
		if(robotStatus == RobotStatus.READY) {
			robotModelService.increaseStock(robotModel);
		}
		
		return new ModelAndView(new RedirectView("/robots"));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/robots/{id}/edit")
	public String updateRobot(Model model, @PathVariable Long id) {
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		
		List<RobotStatus> robotStatuses = new ArrayList<>(Arrays.asList(RobotStatus.values()));
		if(robotService.getRobot(id).getOrder() == null)
			for(Iterator<RobotStatus> iterator = robotStatuses.iterator(); iterator.hasNext();) {
				RobotStatus status = iterator.next();
				if(status.isOrderSpecific()) {
					iterator.remove();
				}
			}
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		model.addAttribute("robot", robotService.getRobot(id));
//		model.addAttribute("thisrobotmodel", robotService.getRobot(id).getRobotModel());
//		model.addAttribute("thisrobotstatus", robotService.getRobot(id).getStatus());
//		model.addAttribute("id", id);
//		model.addAttribute(robotService.getRobot(id).)
		
		return "editrobot";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots/{id}/edit")
	public ModelAndView updateRobot(@RequestParam(name="model") RobotModel robotModel, @RequestParam(name="status") RobotStatus robotStatus, @PathVariable Long id, Model model) {
		Robot robot = robotService.getRobot(id);
		
		robot = new Robot(id, robotModel, robotStatus, robot.getOrder(), robot.isInBasket());
		
		robotService.updateRobot(robot);
		
		if(robotStatus == RobotStatus.READY && robot.getOrder() == null) {
			robotModelService.increaseStock(robotModel);
		}
		
		return new ModelAndView(new RedirectView(".."));
	}
	
	
	
}
