import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Player {
    private int x, y = 0;
    public int getX(){return x;}
    public int getY(){return y;}

    private int offset = 5;
    private Map map;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Pane pane;

    private double north = 180;
    private double east = 90;
    private double south = 0;
    private double west = 270;

    Image image;

    {
        try {
            image = new Image(new FileInputStream(("C:\\Users\\devan\\OneDrive\\Documents\\GitHub\\Zombie-Game\\src\\zombie-temp.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    ImageView imageView = new ImageView(image);


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

        imageView.setX(x - 11);
        imageView.setY(y - 11);
        imageView.setFitHeight(35);
        imageView.setFitWidth(35);

        pane.getChildren().add(imageView);
        imageView.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
        imageView.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);


//        pane.getChildren().add(rect);
//        rect.setFill(Color.LIGHTGRAY);
//        rect.setStroke(Color.WHITE);
//        rect.setTranslateX(x * MapTile.tileSize + (offset+1) / 2);
//        rect.setTranslateY(y * MapTile.tileSize + (offset+1) / 2);
//        //movePlayer();
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        imageView.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        imageView.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);


//        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
//        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
    }

    public void registerInput(KeyEvent ke) {
        int px = x;
        int py = y;

        if (ke.getCode() == KeyCode.W) {
            if (py != 0) {
                if(map.tiles[px][py - 1].getTileType() == 0)
                py--;
                imageView.setRotate(west);

            }
        }
        if (ke.getCode() == KeyCode.A) {
            if (px != 0) {
                if(map.tiles[px - 1][py].getTileType() == 0)
                px--;
                imageView.setRotate(north);
            }
        }
        if (ke.getCode() == KeyCode.S) {
            if (py != map.getSize_y() - 1) {
                if(map.tiles[px][py + 1].getTileType() == 0)
                py++;
                imageView.setRotate(east);
            }
        }
        if (ke.getCode() == KeyCode.D) {
            if (px != map.getSize_x() - 1) {
                if(map.tiles[px + 1][py].getTileType() == 0)
                px++;
                imageView.setRotate(south);
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
