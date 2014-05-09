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
		manager.waitFor(5000);
		land();
	}

	public void move(double x, double y, double z) {
		// x is forward
		// y is left
		// z is top
		// double xSquare = Math.pow(x, 2);
		// double ySquare = Math.pow(y, 2);
		// double zSquare = Math.pow(z, 2);
		// double vectorSize = Math.sqrt(xSquare + ySquare + zSquare);
		double deltaByThree = deltaTime / 3;
		long deltaByThreeLong = new Double(deltaByThree).longValue();
		if (z > 0) {
			manager.up(new Double(z / deltaByThree).intValue()).doFor(
					deltaByThreeLong);
		} else {
			manager.down(new Double(-1 * z / deltaByThree).intValue()).doFor(
					deltaByThreeLong);
		}

		if (y > 0) {
			manager.goLeft(new Double(y / deltaByThree).intValue()).doFor(
					deltaByThreeLong);
		} else {
			manager.goRight(new Double(-1 * y / deltaByThree).intValue())
					.doFor(deltaByThreeLong);
		}

		if (x > 0) {
			manager.forward(new Double(x / deltaByThree).intValue()).doFor(
					deltaByThreeLong);
		} else {
			manager.down(new Double(-1 * x / deltaByThree).intValue()).doFor(
					deltaByThreeLong);
		}
	}

}
