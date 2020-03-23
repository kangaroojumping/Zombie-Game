package ZEngine;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private int x, y = 0;
    public int getX(){return x;}
    public int getY(){return y;}

    private int offset = 5;
    private ZEngine.Map map;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Pane pane = new Pane();

    public boolean isActive = true;

    public Player(ZEngine.Map map){
        this.map = map;
        map.getPane().getChildren().add(pane);
        createPlayer();
    }

    public Player(Map map, int x, int y){
        this.x = x;
        this.y = y;
        this.map = map;
        map.getPane().getChildren().add(pane);
        createPlayer();
    }

    public void createPlayer(){
        setPosition(x, y);
        rect.setFill(Color.LIGHTGRAY);
        rect.setStroke(Color.WHITE);
        pane.getChildren().add(rect);
        pane.toFront();
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        rect.setTranslateX(x * MapTile.tileSize + (offset + 1) / 2);
        rect.setTranslateY(y * MapTile.tileSize + (offset + 1) / 2);
        System.out.println("Player is @ " + MapTile.coor(x, y));
        /*
        if(!map.getPane().getChildren().contains((pane))) {
            System.out.println("Making player visible...");
            map.getPane().getChildren().add(pane);
            pane.toFront();
        }
        */

    }

    public void displayPlayer(boolean b){
        if(b)
            pane.toFront();
        else
            pane.getChildren().clear();
    }

    public void registerInput(KeyEvent ke) {
        if(isActive){
            int px = x;
            int py = y;

            if (ke.getCode() == KeyCode.W)
                py--;
            if (ke.getCode() == KeyCode.A)
                px--;
            if (ke.getCode() == KeyCode.S)
                py++;
            if (ke.getCode() == KeyCode.D)
                px++;
            /*
            if (((px != map.getSize_x() - 1 && px != 0) &&
                    (py != map.getSize_y() - 1 && py != 0) )&&
                    (map.tiles[px][py].getTileType() == 0)) {

                setPosition(px, py);
            }
            */
        }

    }
}
