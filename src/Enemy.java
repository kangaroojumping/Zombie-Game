import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image ;
import javafx.scene.transform.Rotate;

import java.util.*;

public class Enemy {

    private int x = 10;
    private int y = 10;

    private int count = 0;
    private int offset = 5;
    private Map map;
    private Pane pane;
    private Player player;

    private double north = 180;
    private double east = 90;
    private double south = 0;
    private double west = 270;
    
    Image image = new Image(new FileInputStream(("C:\\Users\\devan\\OneDrive\\Documents\\GitHub\\Zombie-Game\\Zombie-Game\\src\\survivor-idle_shotgun_0.png")));
    ImageView imageView = new ImageView(image);


    public Enemy(Pane pane, Map map) throws FileNotFoundException {
        this.map = map;
        this.pane = pane;
        createEnemy();
    }

    public Enemy(Pane pane, Map map, int x, int y) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.map = map;
        this.pane = pane;
        createEnemy();
    }


    public void createEnemy() {
        imageView.setX(x - 20);
        imageView.setY(y - 20);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        pane.getChildren().add(imageView);
        imageView.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
        imageView.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        imageView.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        imageView.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }

    public void move(KeyEvent ke) {
        if (player == null) {
            if (map.getHasPlayer()) {
                player = map.getPlayer();
            }
        }
        int px = x;
        int py = y;

        if (ke.getCode() == KeyCode.D || ke.getCode() == KeyCode.S || ke.getCode() == KeyCode.A || ke.getCode() == KeyCode.W) {
            count++;
            if (player.getX() + 5 < x) {
                if (px != map.getSize_x()) {
                    if (map.tiles[x - 1][y].getTileType() == 0)
                        px--;
                    imageView.setRotate(north);
                }
            } else if (player.getY() + 5 < y) {
                if (py != map.getSize_y() - 1) {
                    if (map.tiles[x][y - 1].getTileType() == 0)
                        py--;
                    imageView.setRotate(west);

                }
            } else if (player.getX() - 5 > x) {
                if (px != 0) {
                    if (map.tiles[px + 1][py].getTileType() == 0)
                        px++;
                    imageView.setRotate(south);

                }
            } else if (player.getY() - 5 > y) {
                if (py != 0) {
                    if (map.tiles[px][py + 1].getTileType() == 0)
                        py++;
                    imageView.setRotate(east);

                }
            }
            if (count == 4) {
                shoot();
                count = 0;
            }
            if (player.getX() == x && player.getY() == y) {
                pane.getChildren().remove(imageView);
            }
            System.out.println(count);
            setPosition(px, py);
        }
    }

    public void shoot(){
        Line bullet = new Line();
        bullet.setStartX(x * 20 + 10);
        bullet.setStartY(y * 20 + 10);
        bullet.setEndX(player.getX() * 20 + 10);
        bullet.setEndY(player.getY() * 20 + 10);
        pane.getChildren().add(bullet);

    }
    public void remove(){

    }

}
