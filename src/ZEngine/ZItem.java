package ZEngine;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class ZItem {
    private String itemName;
    public String getItemName(){return itemName;}

    public ZCharacter holder;

    protected Pane pane = new Pane();
    private static int size = MapTile.tileSize / 4;
    Rectangle rect = new Rectangle(size, size);
    public void show(boolean b){ pane.setVisible(b); }

    public void setPosition(int x, int y){
        pane.setTranslateX(MapTile.tileSize * x + randomX(size / 2));
        pane.setTranslateY(MapTile.tileSize * y + randomY(size / 2));
    }

    public int randomX(int base){
        Random rand = new Random();
        int n = (rand.nextInt(1)  + 1) * base;
        if(rand.nextBoolean()) n += size - n;
        return n;
    }
    public int randomY(int base){
        Random rand = new Random();
        int n = (rand.nextInt(1) + 1) * base;
        if(rand.nextBoolean()) n += size - n;
        return n;
    }

    public void takeItem(){
        pane.getChildren().clear();
    }
}
