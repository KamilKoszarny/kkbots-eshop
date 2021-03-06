package kkbots.jpa.order;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import kkbots.jpa.robot.Robot;
import kkbots.jpa.robot.robotmodel.RobotModel;
import kkbots.jpa.user.User;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private User customer;
	
	private Timestamp date;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@JoinColumn(name="status_date")
	private Timestamp statusDate;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Robot> robots;
	
	@Transient
	private double orderSumPrice;
	@Transient
	private List<RobotModel> orderByModels;
	

	public Order() {
		
	}
	
	public Order(List<Robot> robots) {
		this.robots = robots;
	}


	public Order(Long id, User customer, Timestamp date, OrderStatus status, Timestamp statusDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.status = status;
		this.statusDate = statusDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getCustomer() {
		return customer;
	}


	public void setCustomer(User customer) {
		this.customer = customer;
	}


	public Timestamp getDate() {
		return date;
	}


	public void setDate(Timestamp date) {
		this.date = date;
	}


	public OrderStatus getStatus() {
		return status;
	}


	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public List<Robot> getRobots() {
		return robots;
	}


	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}


	public Timestamp getStatusDate() {
		return statusDate;
	}


	public void setStatusDate(Timestamp statusDate) {
		this.statusDate = statusDate;
	}

	public double getOrderSumPrice() {
		return orderSumPrice;
	}

	public void setOrderSumPrice(double orderSumPrice) {
		this.orderSumPrice = orderSumPrice;
	}

	public List<RobotModel> getOrderByModels() {
		return orderByModels;
	}

	public void setOrderByModels(List<RobotModel> orderByModels) {
		this.orderByModels = orderByModels;
	}
}
