package kkbots.jpa.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	public User findUserByLoginAndPassword(String login, String password);
}
