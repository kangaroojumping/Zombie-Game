package ZEngine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Weapon extends ZItem {
    private Map map;
    public Weapon(Map map) {
        this.map = map;
        //Rectangle rect = new Rectangle(MapTile.tileSize  - 3, MapTile.tileSize - 3);
        rect.setFill(Color.RED);
        pane.getChildren().add(rect);
    }

}
