package ZEngine;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends StackPane {
    private Map map;
    private int x;
    private int y;
    public int getX(){return x;}
    public int getY(){return y;}
    public static String coor(int x, int y){return String.format("[%d, %d]", x, y); }
    public String coor(){return coor(x, y);}
    public static int tileSize = 25;
    private Rectangle rect = new Rectangle(tileSize - 1, tileSize - 1);
    private StackPane pane = new StackPane();
    public StackPane getPane(){
        return pane;
    }

    public static int tileAmount = 2;
    private int tileType;
    public int getTileType() {
        if(containedCharacter != null) {
            int n = findRef(containedCharacter);
            System.out.println("Saving " + ZCharacter.typeName(n) + " @ " + coor());
            return MapTile.tileAmount + n;
        }
        if(containedItem != null) {
            System.out.println("Saving item @ " + coor());
            return MapTile.tileAmount + 3;
        }
        else
            return tileType;
    }
    public static int findRef(ZCharacter z){
        int n = 2;
        if(z instanceof Zombie) n = 0;
        if(z instanceof Resistance) n = 1;
        return n;
    }

    public static String findTileName(int m) {
        if (m > 0 || m < tileAmount) {
            if (m == 1) return "Wall";
        }
            return "Ground";
    }
    private String tileName;
    public String getTileName(){return tileName;}

    public MapTile(Map map, int x, int y){
        this.map = map;
        this.x = x;
        this.y = y;
        setTile(0);
        createTile();
        //System.out.println("Tile " + x + ", " + y + " created.");
    }
    public MapTile(Map map, int x, int y, int type){
        this.map = map;
        this.x = x;
        this.y = y;
        setTile(type);
        createTile();
        //System.out.println("Tile " + x + ", " + y + " created.");
    }

    private void createTile(){
        rect.setStroke(Color.BLACK);

        pane.getChildren().add(rect);
        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);

        /*
        Text text = new Text("[" + x + ", " + y + "]");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setScaleX(.5);
        text.setScaleY(.5);
        pane.getChildren().add(text);
         */
    }

    public void setTile(int m){
        if(m < tileAmount){
            tileType = m;
            tileName = findTileName(m);
            Color fillColor = Color.WHITE;
            if(m == 1)
                fillColor = Color.BLACK;
            rect.setFill(fillColor);
            rect.setStroke(Color.BLACK);
        }

        if(m >= MapTile.tileAmount){
            setTile(0);
            //add something to add characters
            int n = m - MapTile.tileAmount;
            if(n >= 3){
                Weapon w = new Weapon(map);
                map.addItem(w);
                w.setPosition(x, y);
                containedItem = w;
                //System.out.println("Setting " + coor() + " to item space.");
                return;
            }
            var z = generateChar(n);
            setZCharacter(z);
        }
    }

    public ZCharacter generateChar(int n){
        if(n == 0) return new Zombie(map);
        if(n == 1) return new Resistance(map);
        else return new Civilian(map);
    }

    public void setZCharacter(ZCharacter z){
        if(z == null) { containedCharacter = null; return; }
        if(containedItem == null) {
            if(containedCharacter == null) {
                map.addChar(z);
                containedCharacter = z;
                z.setPosition(x, y);
            }
            else System.out.println("Another character is on that slot.");
        }
        else System.out.println("An item is already here."); //Does nothing
    }

    public void switchChar(MapTile f, ZCharacter z){
        f.clearChar();
        containedCharacter = z;
        z.setPosition(x, y);
    }

    public void killChar(ZCharacter z){
        map.killChar(z);
        if(z instanceof Zombie) return;
        clearChar();
        Zombie zom = new Zombie(map);
        containedCharacter = zom;
        zom.setPosition(x, y);
    }

    public void clearChar(){ containedCharacter = null; }
    private ZCharacter containedCharacter;
    public ZCharacter getContainedCharacter(){return containedCharacter;}

    private ZItem containedItem;
    public ZItem getContainedItem(){ return containedItem; }
    public void setContainedItem(ZItem n){ containedItem = n; }
}
