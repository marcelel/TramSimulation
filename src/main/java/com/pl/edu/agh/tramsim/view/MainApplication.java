package com.pl.edu.agh.tramsim.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
        //Image img = GoogleStaticMapsApi.requestImage(52.1, 21.1, 565, 565, 8);
        //Image img2 = GoogleStaticMapsApi.requestOffsettedImage(52.1, 21.1, 565, 565, 8, -1, 0);

        //iv1.setImage(img);

        //iv2.setImage(img2);

        double deltax;

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        Pane pane = GoogleStaticMapsApi.createMapPane(52, 21, 1280,720, 8).getPane();
//        pane.getChildren().add(iv1);
//        pane.getChildren().add(iv2);
        iv2.setY(565);
        root.getChildren().add(pane);

//        box.setOnScroll(event -> {
//            double deltaY = event.getDeltaY();
//            logger.trace("Scrolling detected");
//            if (deltaY < 0){
//                updateImages(false);
//            } else {
//                updateImages(true);
//            }
//        });

        primaryStage.setTitle("TramSim");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(1080);

//        iv2.setX(565);
//
//        box.setOnMousePressed(mouseEvent -> {
//            logger.trace("Mouse pressed on " + mouseEvent.getScreenX());
//            deltaDragX = mouseEvent.getScreenX();
//        });
//
//        box.setOnMouseDragged(mouseEvent -> {
//            iv1.setX(iv1.getX() + mouseEvent.getScreenX() - deltaDragX);
//            iv2.setX(iv2.getX() + mouseEvent.getScreenX() - deltaDragX);
//            deltaDragX = mouseEvent.getScreenX();
//        });
//
//        box.setOnMouseReleased(mouseEvent -> {
//            logger.trace("Mouse released on " + mouseEvent.getScreenX());
//        });

        primaryStage.show();

        int a = 1;
        System.out.println(a);
    }
}