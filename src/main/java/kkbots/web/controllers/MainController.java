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
	
	@RequestMapping(value={"/welcome"})
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value={"/", "/index"})
	public String index(HttpSession session, Model model) {
		return "index";
	}
}
