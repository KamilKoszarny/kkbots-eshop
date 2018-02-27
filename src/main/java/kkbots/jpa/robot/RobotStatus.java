package kkbots.jpa.robot;

public enum RobotStatus {
    CONCEPT(30), DESIGN(90), PRODUCTION(30), TESTS(10), READY(0);
	
	int duration;
	RobotStatus(int duration){
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}