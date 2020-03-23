package ZEngine;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Civilian extends ZCharacter{
    public static int referenceNumber = 2;

    protected static String characterName = "Civilian";

    private int maxHP = 3;
    public int getMapHP(){return maxHP;}
    private int currentHP = maxHP;
    public int getCurrentHP(){return currentHP;}

    private int speed = 3;
    public int getSpeed(){return speed;}

    private int x, y;
    public int getX(){return x;}
    public int getY(){return y;}
    public void moveTo(int x, int y){}

    private int offset = 5;
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);

    public Civilian(Map map){
        this.map = map;
        map.getPane().getChildren().add(pane);
        createCivilian();
    }

    public Civilian(Map map, int x, int y){
        this.x = x;
        this.y = y;
        this.map = map;
        map.getPane().getChildren().add(pane);
        createCivilian();
    }
/*
    private Image sprite = new Image("png/Walk (1).png");
    private Image front1 = new Image("new sprite/front 1.png");
    private Image front2 = new Image("new sprite/front 2.png");
    private Image front3 = new Image("new sprite/front 3.png");
    private Image back1 = new Image("new sprite/back 1.png");
    private Image back2 = new Image("new sprite/back 2.png");
    private Image back3 = new Image("new sprite/back 3.png");
    private Image left1 = new Image("new sprite/left 1.png");
    private Image left2 = new Image("new sprite/left 2.png");
    private Image left3 = new Image("new sprite/left 3.png");
    private Image right1 = new Image("new sprite/right 1.png");
    private Image right2 = new Image("new sprite/right 2.png");
    private Image right3 = new Image("new sprite/right 3.png");
*/
    public void createCivilian(){
        pane.getChildren().add(rect);
        //rect.setFill(new ImagePattern(front1));
        rect.setFill(Color.ORANGE);
        rect.setTranslateX(x * MapTile.tileSize + offset/4);
        rect.setTranslateY(y * MapTile.tileSize + offset/4);
    }

    /*
    private int playerClicked = 0;

    public void onClick(){
        /*
        if(player == null) {
            if (map.getHasPlayer()) {
                player = map.getPlayer();
            }
        }

        //Called when clicked from map
        displayOptions();
        int px = x;
        int py = y;
        // for line 101, i'm not sure what the code would be to find out if the player has clicked it's player
        // and move it's player. So, basically the civilian would move one square in random after the player
        // has moved twice.
        //if (// player is clicked) {
            playerClicked++;
            //System.out.println("Players clicked counter: " + playerClicked);
            Random rand = new Random();
            int randomNumber = rand.nextInt(4);
            if (playerClicked % 2 == 0) {
                if (randomNumber == 0) {
                    if (py != 0) {
                        if (map.tileAt(px,py - 1).getTileType() == 0) {
                            rect.setFill(new ImagePattern(back1));
                            py--;
                        }
                    }
                }
                if (randomNumber == 1) {
                    if (px != 0) {
                        if (map.tileAt(px - 1,py).getTileType() == 0) {
                            rect.setFill(new ImagePattern(left1));
                            px--;
                        }
                    }
                }
                if (randomNumber == 2) {
                    if (py != map.getSize_y() - 1) {
                        if (map.tileAt(px,py + 1).getTileType() == 0) {
                            rect.setFill(new ImagePattern(front1));
                            py++;
                        }
                    }
                }
                if (randomNumber == 3) {
                    if (px != map.getSize_x() - 1) {
                        if (map.tileAt(px + 1,py).getTileType() == 0) {
                            rect.setFill(new ImagePattern(right1));
                            px++;
                        }
                    }
                }
                /*
                //map.mapPlayer();
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

    */

    public void displayOptions(){
        // Show (and color) all tiles available to move to
        // Show (different color) tiles that can be interacted with
        // Blue for movement, red for enemies, green for items?
        // Tell map that the player is interacting with this character,
        // Momentarily pause other map items from being interacted with

        // If interact with object, interactWithObjectAt(area clicked);
        // If that object is ZCharacter, also call thatObject.beInteractedWith(this);
    }
    @Override
    public void interactWithChar(ZCharacter target){
        if(target.supplyItem != null) map.convertToResistance(target.supplyItem, this);
    }
    @Override
    public void beInteractedWith(ZCharacter z){ /* also override method */ }
    @Override
    public void interactWithItem(ZItem n){
        map.convertToResistance(n, this);
    }
}
