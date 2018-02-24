package kkbots.jpa.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}
	
	public Order getOrder(Long id) {
		return orderRepository.findOne(id);
	}
	
	public void addOrder(Order order) {
		orderRepository.save(order);
	}
	
	public void updateOrder(Order order, Long id) {
		orderRepository.save(order);
	}
	
	public void deleteOrder(Long id) {
		orderRepository.delete(id);
	}
}
