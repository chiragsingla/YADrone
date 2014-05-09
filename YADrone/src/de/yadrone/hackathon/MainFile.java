package de.yadrone.hackathon;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

public class MainFile {

	public static void main(String[] args) {
		IARDrone drone = null;
		try {
			// Tutorial Section 1
			drone = new ARDrone();
			drone.addExceptionListener(new IExceptionListener() {
				public void exeptionOccurred(ARDroneException exc) {
					exc.printStackTrace();
				}
			});
			HackathonCommander commander = new HackathonCommander(
					drone.getCommandManager(), 5000L);
			drone.start();
			ConsoleReader consoleReader = new ConsoleReader(commander);
//			DownloadVideoStream.downloadVideaStream(drone, "/Users/cchirag");
			consoleReader.handleCommands();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (drone != null)
				drone.stop();

			System.exit(0);
		}
	}

}
