package kkbots.jpa.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
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
	public ModelAndView validateUser(@RequestParam(name="login") String login, @RequestParam(name="password") String password, HttpServletRequest httpServletRequest) {
			
		User user = userService.validateUser(login, password);
		
		if(user == null)
			return new ModelAndView(new RedirectView("welcome"));
		else {
			httpServletRequest.getSession().setAttribute("user", user);
			if (user.getRole().equals("admin"))
				return new ModelAndView(new RedirectView("adminpanel"));
			else {
				httpServletRequest.getSession().setAttribute("orderlist", new ArrayList<Long>());
				return new ModelAndView(new RedirectView("customerpanel"));
			}
			
		}
	}
	
	@RequestMapping("/adminpanel")
	public ModelAndView adminPanel(HttpServletRequest httpServletRequest) {
			User user = (User)httpServletRequest.getSession().getAttribute("user");
			
			if (user == null)
				return new ModelAndView(new RedirectView("welcome"));
			if (user.getRole().equals("admin"))
				return new ModelAndView("adminpanel");
			else
				return new ModelAndView(new RedirectView("customerpanel"));
	}
	
	@RequestMapping("/customerpanel")
	public ModelAndView customerPanel(HttpServletRequest httpServletRequest) {
			User user = (User)httpServletRequest.getSession().getAttribute("user");
			
			if (user == null)
				return new ModelAndView(new RedirectView("welcome"));
			if (user.getRole().equals("admin"))
				return new ModelAndView(new RedirectView("adminpanel"));
			else
				return new ModelAndView("customerpanel");
	}
	
	@RequestMapping("/logout")
	public String logOutUser(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().setAttribute("user", null);
		
		return "welcome";
	}
}
