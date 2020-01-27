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

    private Parent createMap(){
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map();
        root.getChildren().add(map.getPanes());
        return root;
    }
}

