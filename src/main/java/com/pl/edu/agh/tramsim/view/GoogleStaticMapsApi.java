package com.pl.edu.agh.tramsim.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class GoogleStaticMapsApi {

    public static final double zoomXOffset = 397.44;
    public static final double zoomYOffset = 248.96;
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
        latitude += (tilesY/Math.pow(2, zoom-1))* zoomYOffset;
        longitude += (tilesX/Math.pow(2, zoom-1))* zoomXOffset;
        return requestImage(latitude, longitude, width, height, zoom);
    }

    public static MapPane createMapPane(double latitude, double longitude, double width, double height, int startingZoom) throws IOException {
        return new MapPane(latitude, longitude, width, height, startingZoom);
    }


}

class MapPane {
    private final int tileX = 565;
    private final int tileY = 565;
    private static Logger logger = LoggerFactory.getLogger( MapPane.class );

    private double width;
    private double height;
    private double latitude;
    private double longitude;
    private int zoom;

    private double deltaX;
    private double deltaY;

    public Pane getPane() {
        return pane;
    }

    private Pane pane;
    private ImageView[][] tiles;

    public MapPane(double latitude, double longitude, double width, double height, int zoom) throws IOException {
        this.width = width;
        this.height = height;
        this.zoom = zoom;
        this.latitude = latitude;
        this.longitude = longitude;
        pane = new Pane();
        tiles = new ImageView
                [(int) Math.ceil(width/tileX + 2)]
                [(int) Math.ceil(height/tileY + 2)];
        createTiles();
        updateTiles();
        makeZoomable();
        makeDraggable();
    }

    private void updateTiles() throws IOException {
        for(int i = 0; i < tiles.length; ++i) {
            for(int j = 0; j < tiles[0].length; ++j) {
                tiles[i][j].setImage(GoogleStaticMapsApi.requestOffsettedImage(latitude, longitude, 565, 565, zoom,
                        tiles[0].length/2 - j, - tiles.length/2 + i));
                tiles[i][j].setX((i-1) * tileX);
                tiles[i][j].setY((j-1) * tileY);
            }
        }
    }

    private void createTiles() {
        for(int i = 0; i < tiles.length; ++i) {
            for(int j = 0; j < tiles[0].length; ++j) {
                ImageView iv = new ImageView();
                iv.setX(i);
                iv.setY(j);
                tiles[i][j] = iv;
                pane.getChildren().add(iv);
            }
        }
    }

    private void makeZoomable() {
        pane.setOnScroll(event -> {
            logger.trace("Scrolling detected");
            if (event.getDeltaY() < 0){
                moveImages(false);
            } else {
                moveImages(true);
            }
        });
    }

    private void moveImages(boolean zoomIn) {
        try {
            if(zoom > 1 && zoom <= 12) {
                zoom += zoomIn ? 1 : -1;

                for(int i=0; i<tiles.length; ++i) {
                    for(int j=0; j<tiles[0].length; ++j) {
                        tiles[i][j].setImage(GoogleStaticMapsApi.requestOffsettedImage(latitude, longitude, 565, 565, zoom,
                                tiles[0].length/2 - j, - tiles.length/2 + i));
                    }
                }
            }
        } catch (IOException e) {
            logger.warn("Map zoom update failed");
        }
    }

    private void makeDraggable() {
        pane.setOnMousePressed(mouseEvent -> {
            deltaX = mouseEvent.getScreenX();
            deltaY = mouseEvent.getScreenY();
            logger.trace("Mouse pressed on " + deltaX + "x and " + deltaY + "y.");
        });

        pane.setOnMouseDragged(mouseEvent -> {
            for(int i=0; i<tiles.length; ++i) {
                for(int j=0; j<tiles[0].length; ++j) {
                    tiles[i][j].setX(tiles[i][j].getX() + mouseEvent.getScreenX() - deltaX);
                    tiles[i][j].setY(tiles[i][j].getY() + mouseEvent.getScreenY() - deltaY);
                }
            }
            deltaX = mouseEvent.getScreenX();
            deltaY = mouseEvent.getScreenY();
        });
    }
}