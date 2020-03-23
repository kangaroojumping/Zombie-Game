package ZEngine;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Resistance extends ZCharacter {
    public static int referenceNumber = 1;
    protected static String characterName = "Resistance";
    public int power = 5;

    public Resistance(Map map){
        this.map = map;
        Rectangle rect = new Rectangle(MapTile.tileSize - 2, MapTile.tileSize - 2);
        rect.setFill(Color.DARKBLUE);
        pane.getChildren().add(rect);
        map.getPane().getChildren().add(pane);
    }
    @Override
    public void interactWithChar(ZCharacter target){
        if(target instanceof Civilian && supplyItem != null) map.convertToResistance(supplyItem, (Civilian) target);
        if(target instanceof Zombie) {
            int min = power / 5;
            int dmg = (int) Math.random() * ((power - min) + 1) + min;
            System.out.println("Dealing " + dmg + " to zombie.");
            target.takeDamage(this, dmg);
        }
    }
    @Override
    public void beInteractedWith(ZCharacter z){
        if(z instanceof Zombie){
            //Less damage in return because retaliation attack
            int max = (int) power / 2;
            int min = (int) max / 5;
            int dmg = (int) Math.random() * ((max - min) + 1) + min;
            System.out.println("Dealing " + dmg + " in retaliation.");
            z.takeDamage(this, dmg);
        }
    }
    @Override
    public void interactWithItem(ZItem n){
        if(supplyItem == null) {
            supplyItem = n;
            n.holder = this;
        }
    }
}
