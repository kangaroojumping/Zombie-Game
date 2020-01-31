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
        btn.setOnAction(e -> {
            System.out.println("Starting...");
            primaryStage.setScene(createMap(0));
        });
        Button btn2 = new Button("Edit Map");
        btn2.setTranslateY(btn.getTranslateY() + 30);
        btn2.setOnAction(e -> {
            System.out.println("Editing map...");
            primaryStage.setScene(createMap(1));
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

    private static int window_width = 600;
    private static int window_height = 400;

    private int gameMode = -1;
    private Player player;
    private Civilian civilian1;
    private Civilian civilian2;
    private Civilian civilian3;
    private Map map;

    private Scene createMap(){
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map();
        root.getChildren().add(map.getPanes());
        Scene newScene = new Scene(root);
        return newScene;
    }
    private Scene createMap(int mode){
        this.gameMode = mode;
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        map = new Map();
        root.getChildren().add(map.getPanes());
        Scene newScene = new Scene(root);
        Random rand = new Random();
        if(mode == 0)
            player = new Player(root, map);
            civilian1 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
            civilian2 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
            civilian3 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
        if(mode == 1){
            Button gen = new Button();
        }
        registerInput(newScene);
        return newScene;
    }

    private void registerInput(Scene currentScene){
        if(gameMode == 0){
            currentScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    player.registerInput(keyEvent);
                    civilian1.registerInput(keyEvent);
                    civilian2.registerInput(keyEvent);
                    civilian3.registerInput(keyEvent);
                }
            });
        }
        else if(gameMode == 1){
            currentScene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    map.registerInput(mouseEvent);
                }
            });
        }
    }
}

