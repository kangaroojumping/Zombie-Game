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
    private int x, y = 0;
    public int getX(){return x;}
    public int getY(){return y;}

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

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }

    public void registerInput(KeyEvent ke) {
        int px = x;
        int py = y;

        if (ke.getCode() == KeyCode.W) {
            if (py != 0) {
                if(map.tiles[px][py - 1].getTileType() == 0)
                py--;
            }
        }
        if (ke.getCode() == KeyCode.A) {
            if (px != 0) {
                if(map.tiles[px - 1][py].getTileType() == 0)
                px--;
            }
        }
        if (ke.getCode() == KeyCode.S) {
            if (py != map.getSize_y() - 1) {
                if(map.tiles[px][py + 1].getTileType() == 0)
                py++;
            }
        }
        if (ke.getCode() == KeyCode.D) {
            if (px != map.getSize_x() - 1) {
                if(map.tiles[px + 1][py].getTileType() == 0)
                px++;
            }
        }
        //System.out.println("Key Pressed: " + ke.getCode());
        if(x != px || y != py){
            setPosition(px, py);
            //String coor = "[" + x + ", " + y + "]";
            //System.out.println("Player is @ " + coor);
        }
    }
}
