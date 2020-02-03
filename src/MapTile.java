import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.logging.Handler;

public class MapTile extends StackPane {
    private Map map;
    private int x;
    private int y;

    public static int tileSize = 20;
    private Rectangle rect = new Rectangle(tileSize - 1, tileSize - 1);
    private StackPane pane = new StackPane();
    public StackPane getPane(){
        return pane;
    }

    public static int tileAmount = 2;
    private int tileType;
    public int getTileType() {return tileType;}
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
        if(tileType == 1)
            rect.setFill(Color.LIGHTGRAY);
        else
            rect.setFill(Color.WHITE);

        rect.setStroke(Color.BLACK);
        //rect.addEventHandler(MouseEvent, new Handler());
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
        else if(m == tileAmount){
            setTile(0);
        }
    }
}
