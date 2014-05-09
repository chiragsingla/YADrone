package de.yadrone.hackathon;

import java.util.Scanner;

public class ConsoleReader {

	// TODO(Chirag) : Pass in the command manager here
	private static final String END = "end";
	private static final String TAKEOFFANDLAND = "takeoffandland";
	private static final String TAKEOFF = "takeoff";
	private static final String LAND = "land";
	private static final String MOVE = "m ";

	private HackathonCommander commander;

	public ConsoleReader(HackathonCommander commander) {
		this.commander = commander;
	}

	public void handleCommands() throws InterruptedException {
		Scanner scanIn = new Scanner(System.in);
//		String input = scanIn.nextLine();
//		while (!input.toLowerCase().contains(END)) {
//			System.out.println(input);
//			input = scanIn.nextLine();
//			doCommand(input);
//			System.out.println("We are done here!");
//		}
		doCommand("takeoff");
		Thread.currentThread().sleep(1000L);
		doCommand("m 10,0,0");
		doCommand("m -20,0,0");
		doCommand("m 0,10,0");
		doCommand("m 0,-20,0");
		doCommand("m 0,0,10");
		doCommand("m 0,0,-20");
		doCommand("land");
//		scanIn.close();
		System.out.println("We are done here!");
	}

	private void doCommand(String input) throws InterruptedException {
		input = input.toLowerCase();
		System.out.println("Command is input");
		if (TAKEOFFANDLAND.equals(input)) {
			commander.takeOffAndLand();
		} else if (TAKEOFF.equals(input)) {
			commander.takeOff();
		} else if (LAND.equals(input)) {
			commander.land();
		} else if (input.startsWith(MOVE)) {
			String result = input.replace(MOVE, "");
			String[] inputs = result.split(",");
			double x = Double.valueOf(inputs[0]);
			double y = Double.valueOf(inputs[1]);
			double z = Double.valueOf(inputs[2]);
			commander.move(x, y, z);
		} else {
			System.out.println("Nothing to do");
		}
	}

}
