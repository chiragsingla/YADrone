package de.yadrone.hackathon;

import java.io.File;
import java.io.PrintWriter;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.navdata.ReferencesData;
import de.yadrone.base.navdata.ReferencesListener;
import de.yadrone.base.navdata.TrackerData;
import de.yadrone.base.navdata.VelocityListener;
import de.yadrone.base.navdata.VisionData;
import de.yadrone.base.navdata.VisionListener;
import de.yadrone.base.navdata.VisionPerformance;
import de.yadrone.base.navdata.VisionTag;

public class MainFile {

	public static void main(String[] args) throws Exception {
		IARDrone drone = null;
		final PrintWriter writer = new PrintWriter("/tmp/velocity.txt", "UTF-8");
		final PrintWriter trackers = new PrintWriter("/tmp/trackers.txt", "UTF-8");
		final PrintWriter tagsDetected = new PrintWriter("/tmp/tagsDetected.txt", "UTF-8");
		final PrintWriter visionOf = new PrintWriter("/tmp/visionOf.txt", "UTF-8");
		final PrintWriter performance = new PrintWriter("/tmp/performance.txt", "UTF-8");
		final PrintWriter receivedData = new PrintWriter("/tmp/receivedData.txt", "UTF-8");
		final PrintWriter referenceData = new PrintWriter("/tmp/reference.txt", "UTF-8");
		
		try {
			// Tutorial Section 1
			drone = new ARDrone();
//			drone.setHorizontalCamera();
			drone.getNavDataManager().addVisionListener(new VisionListener() {
				
				@Override
				public void typeDetected(int detection_camera_type) {
					
				}
				
				@Override
				public void trackersSend(TrackerData trackersData) {
					trackers.println(trackersData.toString());
					
				}
				
				@Override
				public void tagsDetected(VisionTag[] tags) {
					tagsDetected.println("Tags Detected");
					for(VisionTag tag: tags){
						tagsDetected.println(tag.toString());
					}
				}
				
				@Override
				public void receivedVisionOf(float[] of_dx, float[] of_dy) {
					// TODO Auto-generated method stub
					int len = Math.min(of_dx.length, of_dy.length);
					visionOf.println("vision of");
					for(int i=0;i<len;i++){
						visionOf.println(of_dx[i] + "  " + of_dy[i]);
					}
				}
				
				@Override
				public void receivedRawData(float[] vision_raw) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void receivedPerformanceData(VisionPerformance d) {
					performance.println(d.toString());
				}
				
				@Override
				public void receivedData(VisionData d) {
					receivedData.println(d.toString());
				}
			});
			
			drone.getNavDataManager().addReferencesListener(new ReferencesListener() {
				
				@Override
				public void receivedReferences(ReferencesData d) {
					referenceData.println(d.toString());
				}
				
				@Override
				public void receivedRcReferences(int[] rc_ref) {
					// TODO Auto-generated method stub
					
				}
			});
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
					drone.getCommandManager(), 2L);
			ConsoleReader consoleReader = new ConsoleReader(commander);
			 DownloadVideoStream.downloadVideaStream(drone, "/Users/cchirag");
			consoleReader.handleCommands();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			writer.close();
			trackers.close();
			tagsDetected.close();
			visionOf.close();
			performance.close();
			receivedData.close();
			referenceData.close();
			if (drone != null)
				drone.stop();

			System.exit(0);
		}
	}

}
