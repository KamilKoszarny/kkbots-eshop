package kkbots.jpa.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User validateUser(String login, String password) {
		
		return userRepository.findUserByLoginAndPassword(login, password);
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
}
