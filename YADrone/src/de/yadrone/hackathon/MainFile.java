package de.yadrone.hackathon;

import java.io.File;
import java.io.PrintWriter;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.navdata.VelocityListener;

public class MainFile {

	public static void main(String[] args) throws Exception {
		IARDrone drone = null;
		final PrintWriter writer = new PrintWriter("/tmp/velocity.txt", "UTF-8");
		try {
			// Tutorial Section 1
			drone = new ARDrone();
			// file.
			drone.getNavDataManager().addVelocityListener(
					new VelocityListener() {

						@Override
						public void velocityChanged(float vx, float vy, float vz) {
							AtomicTest.x.set(vx);
							AtomicTest.y.set(vy);
							AtomicTest.z.set(vz);
							writer.println(vx + "   " + vy + "  " + vz);
						}
					});
			// drone.a
			drone.addExceptionListener(new IExceptionListener() {
				public void exeptionOccurred(ARDroneException exc) {
					exc.printStackTrace();
				}
			});
			drone.start();

			HackathonCommander commander = new HackathonCommander(
					drone.getCommandManager(), 10L);
			ConsoleReader consoleReader = new ConsoleReader(commander);
			// DownloadVideoStream.downloadVideaStream(drone, "/Users/cchirag");
			consoleReader.handleCommands();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			writer.close();
			if (drone != null)
				drone.stop();

			System.exit(0);
		}
	}

}
