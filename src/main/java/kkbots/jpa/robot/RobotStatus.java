package kkbots.jpa.robot;

public enum RobotStatus {
    CONCEPT(false, 30), DESIGN(false, 90), PRODUCTION(false, 30), TESTS(false, 10), READY(false, 0), 
    TO_SEND(true, 0), SEND(true, 4), SOLD(true, 0);
	
	boolean orderSpecific;
	int duration;
	RobotStatus(boolean orderSpecific, int duration){
		this.orderSpecific = orderSpecific;
		this.duration = duration;
	}
	public boolean isOrderSpecific() {
		return orderSpecific;
	}
	public void setOrderSpecific(boolean orderSpecific) {
		this.orderSpecific = orderSpecific;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}