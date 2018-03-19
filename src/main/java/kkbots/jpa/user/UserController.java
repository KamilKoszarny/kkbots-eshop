package kkbots.jpa.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kkbots.jpa.robot.RobotService;
import kkbots.jpa.robot.robotmodel.RobotModelService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RobotService robotService;
	@Autowired
	RobotModelService robotModelService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	public void updateUser(@RequestBody User user, @PathVariable Long id) {
		userService.updateUser(user, id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@RequestMapping(method= RequestMethod.POST, value= "/uservalidation")
	public ModelAndView validateUser(@RequestParam(name="login") String login, @RequestParam(name="password") String password, 
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
			
		if(userService.checkLoginAvailable(login)) {
			redirectAttributes.addFlashAttribute("message", "No such user. Check login.");
			return new ModelAndView(new RedirectView("/news"));			
		}
		
		User user = userService.validateUser(login, password);
		if(user == null) {
			redirectAttributes.addFlashAttribute("message", "Wrong password.");
			return new ModelAndView(new RedirectView("/news"));
		} else {
			request.getSession().setAttribute("user", user);
			if (user.getRole().equals("customer"))
				request.getSession().setAttribute("orderlist", new ArrayList<Long>());
			StringBuilder pathSB = new StringBuilder(request.getHeader("referer"));
			String path = pathSB.substring(pathSB.lastIndexOf("/"));
			return new ModelAndView(new RedirectView(path));
		}
	}
	
	@RequestMapping("/panel")
	public ModelAndView adminPanel(HttpServletRequest httpServletRequest) {
			User user = (User)httpServletRequest.getSession().getAttribute("user");
			
			if (user == null)
				return new ModelAndView("emptypanel");
			if (user.getRole().equals("admin"))
				return new ModelAndView("adminpanel");
			else
				return new ModelAndView("customerpanel");
	}
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public ModelAndView registerPost(
			@RequestParam(name="login") String login, 
			@RequestParam(name="password") String password, 
			@RequestParam(name="firstname") String firstName, 
			@RequestParam(name="lastname") String lastName, 
			@RequestParam(name="email") String email,
			@RequestParam(name="phone", required=false) String phone,
			@RequestParam(name="address", required=false) String address,
			Model model){
		
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		
		User user = new User(0l, "customer", login, encodedPassword, firstName, lastName, email, phone, address);
		if(!validateRegistrationAndGenerateMessages(user, model)) {
			model.addAttribute("userInfo", user);
			return new ModelAndView("/register");
		};
		userService.addUser(user);
		
		return new ModelAndView(new RedirectView("/registerthanks"));
	}
	
	private boolean validateRegistrationAndGenerateMessages(User user, Model model) {
		boolean correct = true;
		String valRegMessage = "";
		if (!userService.checkLoginAvailable(user.getLogin())) {
			correct = false;
			valRegMessage = "Login exists. Choose different one";
		}
		if (!userService.checkEmailAvailable(user.getEmail())) {
			correct = false;
			valRegMessage = "Email exists. Choose different one";
		}
		model.addAttribute("message", valRegMessage);
		return correct;
	}
	
	@RequestMapping("/registerthanks")
	public String registerthanks(){
		return "registerthanks";
	}
	
	
	@RequestMapping("/logoutothername")
	public ModelAndView logOutUser(HttpServletRequest httpServletRequest, Model model, HttpSession session) {
		userService.cleanBasket(httpServletRequest, robotService, robotModelService);
		robotModelService.calcBasket(session);
		
		httpServletRequest.getSession().setAttribute("user", null);
		
		return new ModelAndView(new RedirectView("/news"));
	}
	
	@RequestMapping("/cancelorder")
	public ModelAndView cancelOrder(HttpServletRequest request, HttpSession session) {
		userService.cleanBasket(request, robotService, robotModelService);
		robotModelService.calcBasket(session);
		
		return new ModelAndView(new RedirectView("/order"));
	}
	

}
