package de.yadrone.hackathon;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.yadrone.base.IARDrone;
import de.yadrone.base.video.ImageListener;

public class DownloadVideoStream {
	private final static String SLASH = "/";
	private final static String IMAGE_PREFIX = "droneImage";
	private final static String SUFFIX = ".png";
	private final static String PNG = "PNG";
	
	public static void DownloadVideaStream(final IARDrone drone, String downloadDir) {
		if (null == drone) {
			throw new IllegalArgumentException("drone obj is null");
		}
		final String imageDir = downloadDir;
		
		drone.getVideoManager().addImageListener(new ImageListener() {
            public void imageUpdated(BufferedImage newImage)
            {
            	long currentTime = System.currentTimeMillis();
            	File imFile = new File(imageDir + SLASH + IMAGE_PREFIX + currentTime 
            			+ SUFFIX);
            	
            	try {
            		ImageIO.write(newImage, PNG, imFile);	
            	} catch(Exception e) {
            		e.printStackTrace();
            	}
            }
        });
	}
}
