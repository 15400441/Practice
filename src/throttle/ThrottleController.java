package throttle;



public class ThrottleController {

	public static void main(String[] args) throws InterruptedException {
		ThrottleConfig config = new ThrottleConfig(5, 1);

		ThrottleController throttleController = new ThrottleController(config);

		throttleController.addNewMessage("m1");

		if (throttleController.isReachedHigherThrottleLevel()) {
			throttleController.sendAlert();
		}

		throttleController.addNewMessage("m2");
		if (throttleController.isReachedHigherThrottleLevel()) {
			throttleController.sendAlert();
		}

		throttleController.addNewMessage("m3");

		if (throttleController.isReachedHigherThrottleLevel()) {
			throttleController.sendAlert();
		}
		
		System.out.println("Current rate:"+ throttleController.getThrottleRate());
		
		Thread.sleep(3000);
		
		System.out.println("Current rate:"+ throttleController.getThrottleRate());
		
		throttleController.addNewMessage("m4");
		
		System.out.println("Current rate:"+ throttleController.getThrottleRate());
		
		

	}

	private Throttle throttle;
	private Integer reachedHighestLevel = -1;
	private ThrottleConfig throttleConfig;
	private boolean manualBlocked = false;

	public ThrottleController(ThrottleConfig throttleConfig) {
		this.throttle = new Throttle(throttleConfig.getBlockRate(), throttleConfig.getSecondLevel());
	}

	public void addNewMessage(String message) {
		throttle.putItem(message);
	}

	public boolean isReachedHigherThrottleLevel() {
		Integer newLevel = caculateReachedLevel(throttle.getCurrentThrottleRate());
		if (newLevel > reachedHighestLevel) {
			reachedHighestLevel = newLevel;
			return true;
		}

		return false;

	}

	public boolean reachedBlock() {
		if (this.manualBlocked) {
			return true;
		}

		return throttle.isBlock();
	}
	
	
	public double getThrottleRate(){
		return throttle.getCurrentThrottleRate();
	}

	public void sendAlert() {
		Alert alert = new Alert("reachedHighestLevel:" + reachedHighestLevel);
		System.out.println("send alert-----" + alert);

	}

	public void manualBlock() {
		this.manualBlocked = true;
		
	}

	public void resume() {
		reachedHighestLevel=0;
		manualBlocked = false;
		throttle.resume();
	}

	private Integer caculateReachedLevel(double throttleRate) {
		System.out.println("throttleRate:"+throttleRate);
		if (throttleRate >= 5)
			return 5;
		if (throttleRate >= 4)
			return 4;
		if (throttleRate >= 3)
			return 3;
		if (throttleRate >= 2)
			return 2;
		if (throttleRate >= 1)
			return 1;
		return 0;
	}

}
