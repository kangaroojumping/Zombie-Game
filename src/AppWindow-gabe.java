import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import pngs.menu;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;

public class AppWindow extends Application {
    private AnchorPane  root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        int x = 50;
        int y = 50;

//        primaryStage.setTitle("Hello World!");
         root = new AnchorPane(); // must have
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

        menu btn1 = new menu("P l A Y");
        btn1.setLayoutX(x);
        btn1.setLayoutY( 2 * x);
        root.getChildren().add(btn1);
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("btn1");
            }

        });

        menu btn2 = new menu("S C O R E S");
        btn2.setLayoutX(x);
        btn2.setLayoutY( 4 * x);
        root.getChildren().add(btn2);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("btn2");
            }

        });

        menu btn3 = new menu("H E L P");
        btn3.setLayoutX(x);
        btn3.setLayoutY( 6 * x);
        root.getChildren().add(btn3);

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("btn3");
            }

        });


        menu button = new menu("H I S T O R Y");
        button.setLayoutX(x);
        button.setLayoutY( 8 * x);
        root.getChildren().add(button);


        primaryStage.setScene(new Scene(root, 1200, 550));
        primaryStage.show();
    //    background();

//        Image logo = new Image("pngs/files/zombie-logo-png-1.png",250,250,false,true);
//        root.getChildren().add(logo);

    }

    public void background(){
        Image backgroundImage = new Image("pngs/files/73d4f317117cf08d5967b1ba2baa0eea.jpg",250,250,false,true);
       BackgroundImage backgroundImageset = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,null);
       root.setBackground(new Background(backgroundImageset));
    }
}