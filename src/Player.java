import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class Player {
    private Scene currentScene;
    private int x, y = 0;
    private int offset = 5;
    private Map map;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Pane pane;

    public Player(Pane pane, Map map){
        this.map = map;
        this.pane = pane;
        createPlayer();
    }

    public Player(Pane pane, Map map, int x, int y){
        this.x = x;
        this.y = y;
        this.map = map;
        this.pane = pane;
        createPlayer();
    }
    public void createPlayer(){
        pane.getChildren().add(rect);
        rect.setFill(Color.LIGHTGRAY);
        rect.setStroke(Color.WHITE);
        rect.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);
        //movePlayer();
    }

    public void registerInput(KeyEvent ke) {
        if (ke.getCode() == KeyCode.W) {
            if (y != 0) {
                y--;
            }
        }
        if (ke.getCode() == KeyCode.A) {
            if (x != 0) {
                x--;
            }
        }
        if (ke.getCode() == KeyCode.S) {
            if (y != map.getSize_y() - 1) {
                y++;
            }
        }
        if (ke.getCode() == KeyCode.D) {
            if (x != map.getSize_x() - 1) {
                x++;
            }
        }
        System.out.println("Key Pressed: " + ke.getCode());
        System.out.println("Player is @: [" + x + ", " + y + "]");
        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
