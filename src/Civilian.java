import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.*;
public class Civilian {
    private Scene currentScene;
    private int x = 0;
    private int y = 0;
    private int offset = 5;
    private Map map;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Pane pane;
    private Player player;
    private int keyPressCount = 0;

    public Civilian(Pane pane, Map map) {
        this.pane = pane;
        this.map = map;
        createCivilian();
    }

    public Civilian(Pane pane, Map map, int x, int y) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.pane = pane;
        createCivilian();
    }

    public void createCivilian() {
        pane.getChildren().add(rect);
        rect.setFill(Color.BLUE);
        rect.setStroke(Color.WHITE);
        rect.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }

    public void registerInput(KeyEvent ke) {

        int px = x;
        int py = y;
        if (ke.getCode() == KeyCode.W || ke.getCode() == KeyCode.A || ke.getCode() == KeyCode.S || ke.getCode() == KeyCode.D) {
            keyPressCount++;
            System.out.println("Numbers of Keys Pressed: " + keyPressCount);
            Random rand = new Random();
            int randomNumber = rand.nextInt(4);
            if (keyPressCount % 2 == 0) {
                if (randomNumber == 0) {
                    if (py != 0) {
                        if (map.tiles[px][py - 1].getTileType() == 0) {
                            py--;
                        }
                    }
                }
                if (randomNumber == 1) {
                    if (px != 0) {
                        if (map.tiles[px - 1][py].getTileType() == 0) {
                            px--;
                        }
                    }
                }
                if (randomNumber == 2) {
                    if (py != map.getSize_y() - 1) {
                        if (map.tiles[px][py + 1].getTileType() == 0) {
                            py++;
                        }
                    }
                }
                if (randomNumber == 3) {
                    if (px != map.getSize_x() - 1) {
                        if (map.tiles[px + 1][py].getTileType() == 0) {
                            px++;
                        }
                    }
                }
                if(x != px || y != py) {
                    if (player.getX() != px && player.getY() != py) {
                        setPosition(px, py);
                        //String coor = "[" + x + ", " + y + "]";
                        //System.out.println("Player is @ " + coor);
                    }
                }
                //System.out.println(player.getX() + "," + player.getY());
            }
        }
    }
} // end class Civilian
