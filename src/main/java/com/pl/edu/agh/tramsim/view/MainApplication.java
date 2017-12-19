package com.pl.edu.agh.tramsim.view;

import elements.Position;
import elements.Route;
import elements.RouteMap;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

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

        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        /* ************************* */

        RouteMap routeMap = new RouteMap();
        Route route1 = new Route();
        Route route2 = new Route();
        double[][] cord1 = {
                {20.0649692, 50.0872266},
                {20.0650579, 50.0872183},
                {20.0650999, 50.0872085},
                {20.0651831, 50.0871689},
                {20.0652186, 50.0871376},
                {20.0652518, 50.0870805}
        };
        double[][] cord2 = {
                {20.0652518, 50.0870805},
                {20.0652636, 50.0869204}
        };

        Arrays.stream(cord1).forEach(cord -> route1.coordinates.add(new Position(cord[0], cord[1])) );
        Arrays.stream(cord2).forEach(cord -> route2.coordinates.add(new Position(cord[0], cord[1])) );

        routeMap.routes.add(route1);
        routeMap.routes.add(route2);


        /* ************************* */

        drawTramMap(gc, routeMap);

        root.getChildren().add(canvas);

        primaryStage.setTitle("TramSim");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        primaryStage.show();

        int a = 1;
        System.out.println(a);
    }

    void translateCoords(Position p) {
        double cutLeft = 20.0647;
        double cutTop = 50.0867;

        double Vcoeff = 500000;
        double Hcoeff = 500000;

        p.setX((p.getX() - cutLeft)*Hcoeff);
        p.setY((p.getY() - cutTop)*Vcoeff);
    }

    void drawTramMap(GraphicsContext gc, RouteMap rm) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        rm.routes.forEach(route -> drawRoute(gc, route));
    }

    void drawRoute(GraphicsContext gc, Route r) {
        Position startPosition = r.coordinates.get(0);
        translateCoords(startPosition);

        Position nextPosition;
        for(int i = 1; i < r.coordinates.size(); ++i) {
            nextPosition = r.coordinates.get(i);

            translateCoords(nextPosition);

            gc.strokeLine(
                    startPosition.getX(),
                    startPosition.getY(),
                    nextPosition.getX(),
                    nextPosition.getY()
            );

            startPosition = nextPosition;
        }
    }
}