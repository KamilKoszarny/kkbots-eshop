package kkbots.jpa.robot.robotmodel;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import kkbots.jpa.robot.Robot;

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
	
	@JoinColumn(name="in_production")
	private int inProduction;
	
	@JoinColumn(name="when_ready")
	private Date whenReady;
	
	@OneToMany(mappedBy="robotModel", fetch=FetchType.LAZY)
    private List<Robot> robots;
	
	@Transient
	private int robotCount;
    
    public RobotModel(){
    	
    }
    
    public RobotModel(String model){
    	this.model = model;
    }

    public RobotModel(String model, String motion, String function, String description, double price, int stock,
			int inProduction, Date whenReady) {
		super();
		this.model = model;
		this.motion = motion;
		this.function = function;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.inProduction = inProduction;
		this.whenReady = whenReady;
	}

	public String toString() {
    	return model;
    }
    
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
        if (!(obj instanceof RobotModel)) return false;
        
		RobotModel otherModel = (RobotModel)obj;
		String otherModelName = otherModel.model;

		return otherModelName.equals(model);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(model);
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

	public int getInProduction() {
		return inProduction;
	}

	public void setInProduction(int inProduction) {
		this.inProduction = inProduction;
	}

	public Date getWhenReady() {
		return whenReady;
	}

	public void setWhenReady(Date whenReady) {
		this.whenReady = whenReady;
	}
	
	public List<Robot> getRobots() {
		return robots;
	}

	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}

	public int getRobotCount() {
		return robotCount;
	}

	public void setRobotCount(int robotCount) {
		this.robotCount = robotCount;
	}
   
}
