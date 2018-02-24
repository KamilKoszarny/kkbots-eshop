package kkbots.jpa.order;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private User customer;
	
	private Date date;
	private String status;
	
	@OneToMany(mappedBy="order", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Robot> robots;

	public Order() {
		
	}


	public Order(Long id, Long customer_id, Date date, String status) {
		super();
		this.id = id;
		this.customer = new User(customer_id);
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Robot> getRobots() {
		return robots;
	}


	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}
	
}
