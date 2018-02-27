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
	
	@JoinColumn(name="status_date")
	private Date statusDate;
    
    @ManyToOne(optional=true, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
    private Order order;
    
    @JoinColumn(name="in_basket")
    private boolean inBasket;
    

    public Robot() {
    	
    }

    public Robot(Long id, RobotModel model, RobotStatus status) {
    	super();
		this.id = id;
		this.robotModel = model;
		this.status = status;
    }

	public Robot(Long id, RobotModel robotModel, RobotStatus status, Date statusDate, Order order,
			boolean inBasket) {
		super();
		this.id = id;
		this.robotModel = robotModel;
		this.status = status;
		this.statusDate = statusDate;
		this.order = order;
		this.inBasket = inBasket;
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
	
	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isInBasket() {
		return inBasket;
	}

	public void setInBasket(boolean inBasket) {
		this.inBasket = inBasket;
	}
    
}
