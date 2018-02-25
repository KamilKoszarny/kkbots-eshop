package kkbots.jpa.robot;

public enum RobotStatus {
    CONCEPT(false), DESIGN(false), PRODUCTION(false), TESTS(false), READY(false), 
    TO_SEND(true), SEND(true), SOLD(true);
	
	boolean orderSpecific;
	RobotStatus(boolean orderSpecific){
		this.orderSpecific = orderSpecific;
	}
	public boolean isOrderSpecific() {
		return orderSpecific;
	}
	public void setOrderSpecific(boolean orderSpecific) {
		this.orderSpecific = orderSpecific;
	}
}