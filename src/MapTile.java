import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.awt.*;

public class MapTile extends StackPane {
    private int x;
    private int y;

    public static int tileSize = 20;
    private Rectangle rect = new Rectangle(tileSize - 1, tileSize - 1);
    private StackPane pane = new StackPane();

    private int tileType;
    public int getTileType() {return tileType;}
    private String tileName;
    public String getTileName(){return tileName;}

    public MapTile(int x, int y){
        this.x = x;
        this.y = y;
        tileType = 0;
        tileName = "ground";
        /*
        StackPane stackPane = new StackPane(rect);
        Group group = new Group();
        group.getChildren().add(rect);
        */
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        pane.getChildren().add(rect);

        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);

        //System.out.println("Tile " + x + ", " + y + " created.");
    }
    public MapTile(int x, int y, int type){
        this.x = x;
        this.y = y;
        tileType = type;
        if(tileType == 0)
            tileName = "ground";
        else if(tileType == 1)
            tileName = "wall";
        rect.setStroke(Color.BLACK);
        pane.getChildren().add(rect);
        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);
        //System.out.println("Tile " + x + ", " + y + " created.");
    }

    public StackPane getPane(){
        return pane;
    }
}
