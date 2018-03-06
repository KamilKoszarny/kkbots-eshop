package kkbots.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	@RequestMapping(value={"/welcome"})
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value={"/", "/index"})
	public String index(HttpSession session, Model model) {
		if (model.asMap().get("sMIN") == null) {
			model.addAttribute("sMIN", 0);
			model.addAttribute("category", "");
		}
		return "index";
	}
	
	@RequestMapping(value={"/index/{sMIN}"})
	public ModelAndView indexSMIN(HttpSession session, @PathVariable int sMIN, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("sMIN", sMIN);
		String category = mainService.getCategories().get(sMIN);
		redirectAttributes.addFlashAttribute("category", category);
		return new ModelAndView(new RedirectView("../index"));
	}
	
	@RequestMapping(value="/about")
	public String about(){
		return "about";
	}
}
