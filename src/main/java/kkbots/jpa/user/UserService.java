package kkbots.jpa.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotService;
import kkbots.jpa.robot.robotmodel.RobotModelService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User validateUser(String login, String password) {
		User user = userRepository.findUserByLogin(login);
		String encodedPassword = user.getPassword();
		if(bCryptPasswordEncoder.matches(password, encodedPassword))
			return user;
		return null;
	}
	
	public boolean checkLoginAvailable(String login) {
		if(userRepository.findUserByLogin(login) != null)
			return false;
		return true;
	}
	
	public boolean checkEmailAvailable(String email) {
		if(userRepository.findUserByEmail(email) != null)
			return false;
		return true;
	}
	
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(User user, Long id) {
		userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
	
	public void cleanBasket(HttpServletRequest httpServletRequest, RobotService robotService, RobotModelService robotModelService) {
		@SuppressWarnings("unchecked")
		List<Robot> robotsInBasket = (List<Robot>)httpServletRequest.getSession().getAttribute("basket");
		if (robotsInBasket != null) {
			robotsInBasket.forEach(robot->{
				robotService.removeFromBasket(robot);
				robotModelService.updateStockAndInProduction();
			});
			
			httpServletRequest.getSession().setAttribute("basket", null);
		}
	}
}
