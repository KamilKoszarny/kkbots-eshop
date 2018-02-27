package kkbots.jpa.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kkbots.jpa.user.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	public List<Order> findAllByCustomer(User user);
}
