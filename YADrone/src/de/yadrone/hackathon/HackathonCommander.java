package de.yadrone.hackathon;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;

public class HackathonCommander {

	private CommandManager manager;
	private final long deltaTime;

	public HackathonCommander(CommandManager manager, long deltaTime) {
		this.manager = manager;
		this.deltaTime = deltaTime;
	}

	public void animateLEDs() {
		manager.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
	}

	public void takeOff() {
		manager.takeOff();
	}

	public void land() {
		manager.landing();
	}

	public void takeOffAndLand() {
		// WaitFor is in milliseconds
		takeOff();
		manager.waitFor(1000);
		land();
	}

	public void move(double x, double y, double z) throws InterruptedException {
		// x is forward
		// y is left
		// z is top
		// double xSquare = Math.pow(x, 2);
		// double ySquare = Math.pow(y, 2);
		// double zSquare = Math.pow(z, 2);
		// double vectorSize = Math.sqrt(xSquare + ySquare + zSquare);
//		double deltaByThree = deltaTime / 3;
		long deltaByThreeLong = new Double(deltaTime).longValue();
		manager.move(Double.valueOf(x).intValue(), Double.valueOf(y).intValue(), Double.valueOf(z).intValue(), 0).doFor(deltaTime);

//		manager.hover();
//		Thread.sleep(1000L);

		
//			manager.up(new Double(z / deltaByThree).intValue()).doFor(
//					deltaByThreeLong);
//			manager.freeze();
//			Thread.sleep(deltaByThreeLong);
//			manager.goLeft(new Double(y / deltaByThree).intValue()).doFor(
//					deltaByThreeLong);
//			manager.freeze();
//			Thread.sleep(deltaByThreeLong);
//			manager.forward(new Double(x / deltaByThree).intValue()).doFor(
//					deltaByThreeLong);
			manager.freeze();
			Thread.sleep(deltaByThreeLong);
	}

}
