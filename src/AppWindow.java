import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.util.*;

public class AppWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zombie Survival");
        Button btn = new Button("Start Game");
        Level level = new Level();
        btn.setOnAction(e -> {
            System.out.println("Starting...");
            primaryStage.setScene(level.createMap(0));
        });
        Button btn2 = new Button("Edit Map");
        btn2.setTranslateY(btn.getTranslateY() + 30);
        btn2.setOnAction(e -> {
            System.out.println("Editing map...");
            primaryStage.setScene(level.createMap(1));
        });

        Group buttons = new Group();
        buttons.getChildren().add(btn);
        buttons.getChildren().add(btn2);

        StackPane root = new StackPane();
        root.getChildren().add(buttons);
        primaryStage.setScene(new Scene(root, window_width, window_height));
        //primaryStage.setScene(new Scene(createMap()));
        primaryStage.show();
    }

    public static int window_width = 600;
    public static int window_height = 400;

}

