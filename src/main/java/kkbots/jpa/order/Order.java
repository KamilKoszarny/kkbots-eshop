package kkbots.jpa.order;

import java.sql.Date;
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

import kkbots.jpa.robot.Robot;
import kkbots.jpa.user.User;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="customer_id")
	private User customer;
	
	private Timestamp date;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private List<Robot> robots;

	public Order() {
		
	}


	public Order(Long id, User customer, Timestamp date, OrderStatus status) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.status = status;
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
	
}
