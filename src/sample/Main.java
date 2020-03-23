package sample;

import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import pngs.menu;
import sample.Play;

import ZEngine.*;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;

public class Main extends Application {
    private AnchorPane  root;
    private VBox vbox;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        int x = 30;
        int y = 30;

//        primaryStage.setTitle("Hello World!");
        root = new AnchorPane(); // must have
        vbox = new VBox();
//        Button btn = new Button(); // create button
//        btn.setLayoutX(x); // position
//        btn.setLayoutY(y);
//        root.getChildren().add(btn); // add
//        btn.setText("'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        menu play = new menu("P L A Y");


        play.getPane().setTranslateX(x);
        play.getPane().setTranslateY(4*x);
        root.getChildren().add(play.getPane());

        Level level = new Level();

        play.getButton().setOnAction(e-> {
            Play.display("Play Mode", "What team are you on?");
            primaryStage.setScene(level.loadMap("level1",0));
            primaryStage.show();
        });

        menu scores = new menu("E D I T");

        scores.getPane().setTranslateX(x);
        scores.getPane().setTranslateY(8*x);
        root.getChildren().add(scores.getPane());
        scores.getButton().setOnAction(e-> {
            Play.display("Edit Mode", "");
            primaryStage.setScene(level.createMap(1));
            primaryStage.show();
        });
        
        menu help = new menu("H E L P");
        help.getPane().setTranslateX(x);
        help.getPane().setTranslateY(12*x);
        root.getChildren().add(help.getPane());

        help.getButton().setOnAction(e-> Play.display("Help Menu", "TBD"));

        menu history = new menu("H I S T O R Y");
        history.getPane().setTranslateX(x);
        history.getPane().setTranslateY(16*x);
        root.getChildren().add(history.getPane());
        history.getButton().setOnAction(e-> Play.display("History Page", "TBD"));

        //primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        background();
        logo();



    }

    public void background(){
        Image backgroundImage = new Image("sample/pngs/files/73d4f317117cf08d5967b1ba2baa0eea.jpg",1100,700,false,true);
        BackgroundImage backgroundImageset = new BackgroundImage(backgroundImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND, BackgroundPosition.CENTER,null);
        root.setBackground(new Background(backgroundImageset));
    }
    public void logo(){
        ImageView logo = new ImageView("sample/pngs/files/zombie-logo-png-1.png");
        logo.setLayoutX(500);
        logo.setLayoutY(25);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { // setting for mouse entire the pic
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() { //setting for mouse exit the pic
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        root.getChildren().add(logo);
    }
}