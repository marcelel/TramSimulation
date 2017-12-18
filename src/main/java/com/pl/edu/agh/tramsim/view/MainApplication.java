package com.pl.edu.agh.tramsim.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApplication extends Application {

    private static Logger logger = LoggerFactory.getLogger( MainApplication.class );

    private ImageView iv1 = new ImageView();
    private ImageView iv2 = new ImageView();

    public static void main(String[] args) throws IOException {
        logger.info("Starting app");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        Pane pane = GoogleStaticMapsApi.createMapPane(50.0647, 19.9450, 1280, 720, 10).getPane();
        root.getChildren().add(pane);

        primaryStage.setTitle("TramSim");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        primaryStage.show();

        int a = 1;
        System.out.println(a);
    }
}