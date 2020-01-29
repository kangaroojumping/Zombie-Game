import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
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
            primaryStage.setScene(createMap(true));
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, window_width, window_height));
        //primaryStage.setScene(new Scene(createMap()));
        primaryStage.show();
    }

    private static int window_width = 600;
    private static int window_height = 400;

    private Scene createMap(){
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map();
        root.getChildren().add(map.getPanes());
        Scene newScene = new Scene(root);
        return newScene;
    }
    private Scene createMap(boolean hasPlayer){
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map();
        root.getChildren().add(map.getPanes());
        Scene newScene = new Scene(root);
        Player player;
        if(hasPlayer)
            player = new Player(newScene, root, map);
        return newScene;
    }
}

