package kkbots.jpa.robot;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
    private String status;
    
    @ManyToOne(optional=true, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
    private Order order;
    

    public Robot() {
    	
    }

    public Robot(Long id, String model, String status) {
    	super();
		this.id = id;
		this.robotModel = new RobotModel(model, "", "", "", 0, 0, new Date(0));
		this.status = status;
    }

	public Robot(Long id, String model, String status, Long order_id) {
		super();
		this.id = id;
		this.robotModel = new RobotModel(model, "", "", "", 0, 0, new Date(0));
		this.status = status;
		this.order = new Order(order_id, null, new Date(0), "");
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
    
}
