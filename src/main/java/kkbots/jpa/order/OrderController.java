package kkbots.jpa.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotService;
import kkbots.jpa.user.User;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	RobotService robotService;
	
	@RequestMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@RequestMapping("/orders/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots/{robotId}/orders")
	public String addOrder(@PathVariable Long robotId, HttpServletRequest httpServletRequest) {
		
		List<Long> orderList = (List<Long>)httpServletRequest.getSession().getAttribute("orderList");
		orderList.add(robotId);
		httpServletRequest.getSession().setAttribute("orderList", orderList);
		
		return "robots";
		
		//orderService.addOrder(order);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/orders/{id}")
	public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
		orderService.updateOrder(order, id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orders/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/order")
	public ModelAndView placeOrder(HttpSession session) {
		List<Robot> basket = (List<Robot>)session.getAttribute("basket");
		User customer = (User)session.getAttribute("user");
		Order order = new Order(0l, customer, new java.sql.Timestamp(new java.util.Date().getTime()), OrderStatus.COMPLETING);
		order.setRobots(basket);
		
		order.getRobots().forEach(robot->{
			robot.setOrder(order);
		});
		
		orderService.addOrder(order);
		
		session.setAttribute("basket", new ArrayList<Robot>());
		
		return new ModelAndView(new RedirectView("customerpanel"));
	}

}
