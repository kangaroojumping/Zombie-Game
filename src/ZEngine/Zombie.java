package ZEngine;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Zombie extends ZCharacter {
    public static int referenceNumber = 0;
    protected static String characterName = "Zombie";
    public Zombie(Map map){
        this.map = map;
        createZombie();
    }
    public Zombie (Map map, int x, int y){
        this.map = map;
        setPosition(x, y);
        createZombie();
    }

    public void createZombie(){
        Rectangle rect = new Rectangle(MapTile.tileSize - 2, MapTile.tileSize - 2);
        rect.setFill(Color.GREEN);
        pane.getChildren().add(rect);
        map.getPane().getChildren().add(pane);
    }
    @Override
    public void interactWithChar(ZCharacter target){
        System.out.println("Interacting...");
        if(target instanceof Resistance || target instanceof Civilian) {
            int min = power / 5;
            int dmg = (int) (Math.random() * ((power - min) + 1)) + min + 1;
            System.out.println("Dealing " + dmg + " dmg.");
            target.takeDamage(this, dmg);
            target.beInteractedWith(this);
        }
    }
    @Override
    public void beInteractedWith(ZCharacter z){ /* also override method */ }
    @Override
    public void interactWithItem(ZItem n){ /* No reaction to items */ }
}
