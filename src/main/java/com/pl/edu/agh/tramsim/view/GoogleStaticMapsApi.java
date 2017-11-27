package com.pl.edu.agh.tramsim.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class GoogleStaticMapsApi {

    private static final double zoomOffset = 397.44;
    private static final int retryTime = 100;
    private static final int retryCount = 5;
    private static Logger logger = LoggerFactory.getLogger( GoogleStaticMapsApi.class );

    public static Image requestImage(double latitude, double longitude, int width, int height, int zoom) throws IOException {
        URL imageURL = new URL("http://maps.googleapis.com/maps/api/staticmap?center=" +
                latitude + "," +
                longitude + "&size=" +
                width + "x" +
                height + "&zoom=" +
                zoom);

        for(int i=0;; ++i) {
            try {
                return SwingFXUtils.toFXImage(ImageIO.read(imageURL), null);
            } catch (IOException e) {
                if(i == retryCount) {
                    logger.debug("Error loading tile for the " + (i-1) + " time");
                    throw e;
                }
                try {
                    Thread.sleep(retryTime);
                    logger.trace("Retrying map tile download...");
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public static Image requestOffsettedImage(double latitude, double longitude, int width, int height, int zoom, int tilesY, int tilesX) throws IOException {
        latitude += (tilesY/Math.pow(2, zoom-1))*zoomOffset;
        longitude += (tilesX/Math.pow(2, zoom-1))*zoomOffset;
        return requestImage(latitude, longitude, width, height, zoom);
    }
}
