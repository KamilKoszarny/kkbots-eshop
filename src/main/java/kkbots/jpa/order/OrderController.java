package kkbots.jpa.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.RobotService;
import kkbots.jpa.robot.robotmodel.RobotModel;
import kkbots.jpa.robot.robotmodel.RobotModelService;
import kkbots.jpa.user.User;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	RobotService robotService;
	@Autowired
	RobotModelService robotModelService;
	
	@RequestMapping("/orders")
	public String getAllOrders(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		List<Order> orders;
		if (user.getRole().equals("customer")) {
			orders = orderService.getOrdersByCustomer(user);
			model.addAttribute("title", "My orders");
		} else {
			orders = orderService.getAllOrders();
			model.addAttribute("title", "All orders");
		}
		orders.forEach(order->{
			List<Robot> robots = robotService.getRobotsByOrder(order);
			order.setRobots(robots);
		});
		orders = robotModelService.calcOrders(session, orders);
		orderService.updateOrdersStatusByRobotsAvailability(orders);
		user.setOrders(orders);
		session.setAttribute("user", user);
		return "orders";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pay")
	public ModelAndView pay(@RequestParam(name="orderid") Long orderId) {
		updateOrder(orderId, OrderStatus.PAYMENT);
		return new ModelAndView(new RedirectView("orders"));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/confirmpayment")
	public ModelAndView confirmPayment(@RequestParam(name="orderid") Long orderId) {
		updateOrder(orderId, OrderStatus.TO_SEND);
		return new ModelAndView(new RedirectView("orders"));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/confirmsend")
	public ModelAndView confirmSend(@RequestParam(name="orderid") Long orderId) {
		updateOrder(orderId, OrderStatus.SEND);
		return new ModelAndView(new RedirectView("orders"));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/confirmreceived")
	public ModelAndView confirmReceived(@RequestParam(name="orderid") Long orderId) {
		updateOrder(orderId, OrderStatus.SOLD);
		return new ModelAndView(new RedirectView("orders"));
	}
	
	private void updateOrder(Long id, OrderStatus status) {
		Order order = orderService.getOrder(id);
		order.setStatus(status);
		order.setStatusDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		orderService.updateOrder(order);
	}
	
	@RequestMapping("/basket")
	public String basket(HttpSession session, Model model) {
		double sumPrice = 0; 
		int robotCount = 0;
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket"); 
			if(basket == null) basket = new ArrayList<Robot>();
		
			sumPrice = 0;
			Map<RobotModel, Integer> robotModelMap = new HashMap<>();
			for(Robot robot: basket){
				RobotModel robotModel = robot.getRobotModel();
				robotCount = 0;
				for(Robot nextRobot: basket)
					if(nextRobot.getRobotModel().getModel().equals(robotModel.getModel())) 
						robotCount++;
				if(!robotModelMap.keySet().contains(robotModel))
					robotModelMap.put(robotModel, robotCount);
			}
			
			List<RobotModel> basketByModels = new ArrayList<>();
			for(Map.Entry<RobotModel, Integer> entry : robotModelMap.entrySet()) {
				RobotModel robotModel = entry.getKey();
			    robotCount = entry.getValue();
			    sumPrice += robotCount * robotModel.getPrice();
			    robotModel.setRobotCount(robotCount);
			    basketByModels.add(robotModel);
			}
			
			model.addAttribute("basketbymodels", basketByModels);
			model.addAttribute("sumprice", sumPrice);
			
		return "basket";
	}
	
	@RequestMapping("/orders/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/robots/{robotId}/orders")
	public String addOrder(@PathVariable Long robotId, HttpSession session) {
		
		@SuppressWarnings("unchecked")
		List<Long> orderList = (List<Long>)session.getAttribute("orderList");
		orderList.add(robotId);
		session.setAttribute("orderList", orderList);
		
		return "robots";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/orders/{id}")
	public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
		orderService.updateOrder(order);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orders/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	@RequestMapping(value="/order")
	public String order(){
		return "order";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/order")
	public ModelAndView placeOrder(HttpSession session, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Robot> basket = (List<Robot>)session.getAttribute("basket");
		User customer = (User)session.getAttribute("user");
		Order order = new Order(0l, customer, new java.sql.Timestamp(new java.util.Date().getTime()), OrderStatus.COMPLETING, new java.sql.Timestamp(new java.util.Date().getTime()));
		order.setRobots(basket);
		
		order.getRobots().forEach(robot->{
			robot.setOrder(order);
		});
		
		customer.addOrder(order);
		
		orderService.addOrder(order);
		
		session.setAttribute("basket", null);
		session.setAttribute("basketbymodels", null);
		session.setAttribute("sumPrice", null);
		
		session.setAttribute("basket", new ArrayList<Robot>());
		StringBuilder pathSB = new StringBuilder(request.getHeader("referer"));
		String path = pathSB.substring(pathSB.lastIndexOf("/"));
		return new ModelAndView(new RedirectView(path));
	}

}
