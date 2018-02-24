package kkbots.jpa.robot.robotmodel;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="robotmodels")
public class RobotModel {

	@Id
	private String model;
	
	private String motion;
	private String function;
	private String description;
	private double price;
	private int stock;
	private Date when_ready;
	

//	@OneToMany(mappedBy="robotModel", fetch=FetchType.LAZY)
//    private List<Robot> robots;
    
    RobotModel(){
    	
    }

    public RobotModel(String model, String motion, String function, String description, double price, int stock,
			Date when_ready) {
		super();
		this.model = model;
		this.motion = motion;
		this.function = function;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.when_ready = when_ready;
	}



	public String toString() {
    	return model;
    }
    

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMotion() {
		return motion;
	}

	public void setMotion(String motion) {
		this.motion = motion;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getWhen_ready() {
		return when_ready;
	}

	public void setWhen_ready(Date when_ready) {
		this.when_ready = when_ready;
	}
	
//	public List<Robot> getRobots() {
//		return robots;
//	}
//
//	public void setRobots(List<Robot> robots) {
//		this.robots = robots;
//	}
   
}
