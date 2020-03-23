package ZEngine;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;

import static ZEngine.AppWindow.window_width;

public class ZCharacter {
    protected static String characterName = "Undefined";
    public String getCharacterName(){return characterName;}
    public void setCharacterName(String s){ characterName = s; }
    public static int referenceNumber = -1;

    public static String typeName(int n){
        if(n == 0) return "Zombie";
        if(n == 1) return "Resistance";
        if(n == 2) return "Civilian";
        else return characterName;
    }

    protected int maxHP = 3;
    public int getMapHP(){return maxHP;}
    private int currentHP = maxHP;
    public int getCurrentHP(){return currentHP;}
    public void takeDamage(ZCharacter dealer, int d){
        currentHP -= d;
        if(currentHP <= 0){
            map.killChar(this);
        }
    }

    public static int speed = 3;
    protected int currentSpeed = speed;
    public int getSpeed(){return currentSpeed;}

    public int power = 3;

    private int x, y;
    public int getX(){return x;}
    public int getY(){return y;}

    protected Map map;
    public Map getMap(){return map;}

    protected Pane pane = new Pane();
    public Pane getPane(){return pane;}

    protected static int offset = 6;
    protected Rectangle rect = new Rectangle(MapTile.tileAmount - offset, MapTile.tileAmount - offset);

    public void show(boolean b){ pane.setVisible(b); }

    public void onClick(){
        //Called when clicked from map
        displayOptions(true);
    }

    public ZItem heldItem;
    public ZItem supplyItem;

    public boolean showing = false;

    public void displayOptions(boolean lever){

        // Show (and color) all tiles available to move to
        // Show (different color) tiles that can be interacted with
        // Blue for movement, red for enemies, green for items?
        // Tell map that the player is interacting with this character,
        // Momentarily pause other map items from being interacted with
        if(lever && !showing) drawPortrait();
        else if(map.getPane().getChildren().contains(group)) {
            //System.out.println("Clearing...");
            group.getChildren().clear();
            map.getPane().getChildren().remove(group);
        }
        showing = lever;
        // If interact with object, interactWithObjectAt(area clicked);
        // If that object is ZCharacter, also call thatObject.beInteractedWith(this);
    }

    private int qx, qy;
    public void queueAction(int x, int y){ qx = x; qy = y; }
    public void executeAction(){
        MapTile tile = map.tileAt(qx, qy);
        if(tile.getContainedCharacter() != null || tile.getContainedItem() != null){
            if(tile.getContainedCharacter() != null && tile.getContainedItem() != null)
                System.out.println("There should not be a character and item on the same space???");
            else{
                //interactWithObjectAt(qx, qy);
            }
        }
        else moveTo(qx, qy);
    }

    public void setPosition(int x, int y){
        //teleports character
        pane.toFront();
        pane.setTranslateX(MapTile.tileSize * x + offset/4);
        pane.setTranslateY(MapTile.tileSize * y + offset/4);

        this.x = x;
        this.y = y;
    }
    public void moveTo(int x, int y){

    }
    public void findTarget(int x, int y){ findTarget(this.map.tileAt(x,y)); }
    public void findTarget(MapTile t){ findTarget(t, this.map); }
    public void findTarget(MapTile t, Map map){
        //Simple algorithm for finding a path
    }
    public void interactWithChar(ZCharacter z){ /*override method */ }
    public void beInteractedWith(ZCharacter z){ /*also override method */ }
    public void interactWithItem(ZItem n){ /*also also override method */ }

    private Group group = new Group();

    public void drawPortrait(){
        System.out.println("Showing character stats...");
        int n = 2;
        if(this instanceof Zombie) n = 0;
        if(this instanceof Resistance) n = 1;
        Text name = new Text("Name: " + typeName(n));
        Text hp = new Text("HP: " + currentHP + " / " + maxHP);
        Text speed = new Text("Speed: " + currentSpeed + " / " + getSpeed());
        group.getChildren().addAll(name, hp, speed);

        for(int i = 0; i < group.getChildren().size(); i++){
            var element = group.getChildren().get(i);
            element.setTranslateX(0);
            element.setTranslateY(Level.buttonHeight * i);
        }

        group.setTranslateX(10 + MapTile.tileSize * map.getSize_x());
        group.setTranslateY(10);
        map.getPane().getChildren().add(group);
    }
}
