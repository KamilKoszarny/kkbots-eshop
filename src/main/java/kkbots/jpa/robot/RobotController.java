package kkbots.jpa.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	public ModelAndView getAllRobots(Model model, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null)
			return new ModelAndView(new RedirectView("/panel"));
		
		List<Robot> robotsList = new ArrayList<>();
		robotsList = robotService.getAllRobots();
		if (user.getRole().equals("admin")) {
			robotsList = robotService.getAllRobots();
			model.addAttribute("robotslist", robotsList);
			model.addAttribute("title", "All robots:");
		} else {
			robotsList = robotService.getRobotsByUser(user);
			model.addAttribute("robotslist", robotsList);
			model.addAttribute("title", "My robots:");
		}

		return new ModelAndView("robots");
	}
	
	@RequestMapping("/robots/{id}")
	public ModelAndView getRobotById(@PathVariable Long id, Model model, @RequestParam(name="delete", required=false) String delete, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null)
			return new ModelAndView(new RedirectView("/panel"));
		
		Robot robot = robotService.getRobot(id);
		
		if (delete != null) {
			if (robot.getStatus() == RobotStatus.READY && robot.getOrder() == null)
				robotModelService.updateStockAndInProduction();
			robotService.deleteRobot(id);
			return new ModelAndView(new RedirectView("../robots"));
		} else {
			
			model.addAttribute("title", "This robot:");
			model.addAttribute("robot", robot);
			
			return new ModelAndView("robot");
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
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket");
		if (basket == null)
			basket = new ArrayList<Robot>();
		
		Robot robot = robotService.getAvailableRobot(robotModel);
		robotService.putInBasket(robot);
		basket.add(robot);
		session.setAttribute("basket", basket);
		
		robotModelService.updateStockAndInProduction();
		
		robotModelService.calcBasket(session);
		
		return new ModelAndView(new RedirectView("/shop"));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/removeone")
	public ModelAndView removeOne(HttpSession session, @RequestParam(name="robotmodel") RobotModel robotModel) {
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket");
		
		Robot robot = new Robot();
		for (Robot robotInBasket : basket) {
			if(robotInBasket.getRobotModel().equals(robotModel))
				robot = robotInBasket;
		}

		robotService.removeFromBasket(robot);
		basket.remove(robot);
		session.setAttribute("basket", basket);
		
		robotModelService.updateStockAndInProduction();
		
		robotModelService.calcBasket(session);
		
		return new ModelAndView(new RedirectView("/order"));
	}

	
	@RequestMapping(method=RequestMethod.GET, value="/addrobot")
	public ModelAndView addRobot(Model model, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if (!user.getRole().equals("admin"))
			return new ModelAndView(new RedirectView("/panel"));
		
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		RobotStatus[] robotStatuses = RobotStatus.values();
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		return new ModelAndView("addrobot");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addrobot")
	public ModelAndView addRobot(@RequestParam(name="model") RobotModel robotModel, @RequestParam(name="status") RobotStatus robotStatus, Model model) {
		model.addAttribute("title", "Added robot:");
		Robot robot = new Robot(0l, robotModel, robotStatus, new java.sql.Date(new java.util.Date().getTime()));
		
		robotService.addRobot(robot);
		
		if(robotStatus == RobotStatus.READY) {
			robotModelService.updateStockAndInProduction();
		}
		
		return new ModelAndView(new RedirectView("/robots"));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/robots/{id}/edit")
	public ModelAndView updateRobot(Model model, @PathVariable Long id, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if (!user.getRole().equals("admin"))
			return new ModelAndView(new RedirectView("/panel"));
		
		List<RobotModel> robotModels = robotModelService.getAllRobotModels();
		
		List<RobotStatus> robotStatuses = new ArrayList<>(Arrays.asList(RobotStatus.values()));
		
		model.addAttribute("robotmodels", robotModels);
		model.addAttribute("robotstatuses", robotStatuses);
		
		model.addAttribute("robot", robotService.getRobot(id));
		
		return new ModelAndView("editrobot");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots/{id}/edit")
	public ModelAndView updateRobot(@RequestParam(name="model") RobotModel robotModel, @RequestParam(name="status") RobotStatus robotStatus, @PathVariable Long id, Model model) {
		Robot robot = robotService.getRobot(id);
		
		robot = new Robot(id, robotModel, robotStatus, robot.getStatusDate(), robot.getOrder(), robot.isInBasket());
		
		robotService.updateRobot(robot);
		
		if(robotStatus == RobotStatus.READY && robot.getOrder() == null) {
			robotModelService.updateStockAndInProduction();
		}
		
		return new ModelAndView(new RedirectView("../../robots"));
	}
	
	
	
}
