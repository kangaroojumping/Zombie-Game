import java.util.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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
            primaryStage.setScene(new Scene(createMap()));
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, window_width, window_height));
        //primaryStage.setScene(new Scene(createMap()));
        primaryStage.show();
    }

    private static int window_width = 600;
    private static int window_height = 400;

<<<<<<< Updated upstream
    private Parent createMap(){
=======
    private int gameMode = -1;
    private Player player;
    private Civilian civilian1;
    private Civilian civilian2;
    private Civilian civilian3;
    private Map map;

    private Scene createMap(){
>>>>>>> Stashed changes
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map();
        root.getChildren().add(map.getPanes());
<<<<<<< Updated upstream
        return root;
=======
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
<<<<<<< Updated upstream
        if(mode == 0) {
            player = new Player(root, map);
            Civilian civilian1 = new Civilian(root, map);
=======
        Random rand = new Random();
        if(mode == 0) {
            player = new Player(root, map);
            civilian1 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
<<<<<<< Updated upstream
            civilian2 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
            civilian3 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
>>>>>>> Stashed changes
        }
=======
            //civilian2 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
            //civilian3 = new Civilian(root, map, rand.nextInt(MapTile.tileSize), rand.nextInt(MapTile.tileSize));
            if (player.getX() == civilian1.getX() && player.getY() == civilian1.getY()) {
                civilian1.transformCivilian();
            }
>>>>>>> Stashed changes
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
                    //civilian2.registerInput(keyEvent);
                    //civilian3.registerInput(keyEvent);
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
>>>>>>> Stashed changes
    }
}

