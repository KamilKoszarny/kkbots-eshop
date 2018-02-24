package kkbots.jpa.robot;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kkbots.jpa.order.Order;
import kkbots.jpa.robot.robotmodel.RobotModel;

@Entity
@Table(name="robots")
public class Robot {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="model")
    private RobotModel robotModel;
	
	@Enumerated(EnumType.STRING)
    private RobotStatus status;
    
    @ManyToOne(optional=true, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
    private Order order;
    

    public Robot() {
    	
    }

    public Robot(Long id, RobotModel model, RobotStatus status) {
    	super();
		this.id = id;
		this.robotModel = model;
		this.status = status;
    }

	public Robot(Long id, RobotModel model, RobotStatus status, Order order) {
		super();
		this.id = id;
		this.robotModel = model;
		this.status = status;
		this.order = order;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public RobotModel getRobotModel() {
		return robotModel;
	}

	public void setRobotModel(RobotModel robotModel) {
		this.robotModel = robotModel;
	}


	public RobotStatus getStatus() {
		return status;
	}

	public void setStatus(RobotStatus status) {
		this.status = status;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
    
}
