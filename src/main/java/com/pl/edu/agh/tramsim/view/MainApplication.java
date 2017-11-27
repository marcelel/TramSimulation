package com.pl.edu.agh.tramsim.view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    private static Logger logger = LoggerFactory.getLogger( MainApplication.class );

    private int currentZoom = 10;

    private double deltaDragX;
    private double deltaDragY;

    private ImageView iv1 = new ImageView();
    private ImageView iv2 = new ImageView();

    public static void main(String[] args) throws IOException {
        logger.info("Starting app");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Image img = GoogleStaticMapsApi.requestImage(52.1, 21.1, 565, 565, currentZoom);
        Image img2 = GoogleStaticMapsApi.requestOffsettedImage(52.1, 21.1, 565, 565, currentZoom, 0, 1);

        iv1.setImage(img);

        iv2.setImage(img2);

        double deltax;

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        Pane box = new Pane();
        box.getChildren().add(iv1);
        box.getChildren().add(iv2);
        root.getChildren().add(box);

        box.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            logger.trace("Scrolling detected");
            if (deltaY < 0){
                updateImages(false);
            } else {
                updateImages(true);
            }
        });

        primaryStage.setTitle("ImageView");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        iv2.setX(565);

        box.setOnMousePressed(mouseEvent -> {
            logger.trace("Mouse pressed on " + mouseEvent.getScreenX());
            deltaDragX = mouseEvent.getScreenX();
        });

        box.setOnMouseDragged(mouseEvent -> {
            iv1.setX(iv1.getX() + mouseEvent.getScreenX() - deltaDragX);
            iv2.setX(iv2.getX() + mouseEvent.getScreenX() - deltaDragX);
            deltaDragX = mouseEvent.getScreenX();
        });

        box.setOnMouseReleased(mouseEvent -> {
            logger.trace("Mouse released on " + mouseEvent.getScreenX());
        });

        primaryStage.show();
    }

    private void updateImages(boolean zoomIn) {
        try {
            if(currentZoom > 1 && currentZoom <= 12) {
                currentZoom += zoomIn ? 1 : -1;

                iv1.setImage(GoogleStaticMapsApi.requestImage(52.1, 21.1, 565, 565, currentZoom));
                iv2.setImage(GoogleStaticMapsApi.requestOffsettedImage(52.1, 21.1, 565, 565, currentZoom, 0, 1));
            }
        } catch (IOException e) {
            logger.warn("Map zoom update failed");
        }
    }
}