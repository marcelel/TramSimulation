package com.pl.edu.agh.tramsim.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class GoogleStaticMapsApi {

    public static Image requestImage(double latitude, double longitude, int width, int height, int zoom) throws IOException {
        URL imageURL = new URL("http://maps.googleapis.com/maps/api/staticmap?center=" +
                latitude + "," +
                longitude + "&size=" +
                width + "x" +
                height + "&zoom=" +
                zoom);
        return SwingFXUtils.toFXImage(ImageIO.read(imageURL), null);
    }
}
