package throttle;

import sun.security.action.GetBooleanAction;

public class ThrottleConfig {
	
	private double blockRate=Integer.MAX_VALUE;
	private double secondLevel=1;
	
	
	
    public ThrottleConfig(double blockRate, double secondLevel) {
    	
    	this.blockRate=blockRate;
    	this.secondLevel=secondLevel;
		
	}

	
	
    public double getBlockRate() {
		return blockRate;
	}

	public void setBlockRate(double blockRate) {
		this.blockRate = blockRate;
	}

	public double getSecondLevel() {
		return secondLevel;
	}

	public void setSecondLevel(double secondLevel) {
		this.secondLevel = secondLevel;
	}
	
	
	

	
}
