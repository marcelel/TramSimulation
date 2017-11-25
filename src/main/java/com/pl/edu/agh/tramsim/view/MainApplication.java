package com.pl.edu.agh.tramsim.view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//"http://maps.googleapis.com/maps/api/staticmap?center=48.167432,10.533072&size=565x565&zoom=7"

        Image img = GoogleStaticMapsApi.requestImage(48.167432, 10.533072, 565, 565, 7);

        System.out.println(img.getProgress());

        ImageView iv1 = new ImageView();
        iv1.setImage(img);

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        root.getChildren().add(box);

        primaryStage.setTitle("ImageView");
        primaryStage.setWidth(565);
        primaryStage.setHeight(565);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        iv1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                System.out.println(scene.getX());
                System.out.println(mouseEvent.getScreenX());
            }
        });

        iv1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getScreenX());
            }
        });

        primaryStage.show();
    }
}