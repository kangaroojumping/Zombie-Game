import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Map {
    private int size_x = 25;
    private int size_y = 25;
    public int getSize_x(){return size_x;}
    public int getSize_y(){return size_y;}
    public MapTile[][] tiles;
    private Group panes = new Group();
    public Group getPanes() {return panes;}

    public Map(int x, int y){
        size_x = x;
        size_y = y;
        generateMap();
    }
    public Map(){
        //Uses default values for map size
        generateMap();
    }

    private boolean generated = false;

    private void generateMap(){
        if(generated)
            resetMap();

        tiles = new MapTile[size_x][];
        for(int i = 0; i < size_x; i++){
            tiles[i] = new MapTile[size_y];
            for(int j = 0; j < size_y; j++){
                tiles[i][j] = new MapTile(this, i, j);
                tiles[i][j].getPane().setTranslateX(i * MapTile.tileSize);
                tiles[i][j].getPane().setTranslateY(j * MapTile.tileSize);
                panes.getChildren().add(tiles[i][j].getPane());
            }
        }
        generated = true;
    }

    private void resetMap(){
        tiles = new MapTile[size_x][];
        for(int i = 0; i < size_x; i++)
            tiles[i] = new MapTile[size_y];
        panes.getChildren().clear();
        generated = false;
    }

    public void registerInput(MouseEvent me){
        System.out.println("Mouse click.");
        int px = (int)me.getX() / MapTile.tileSize;
        int py = (int)me.getY() / MapTile.tileSize;
        System.out.println("[" + px + ", " + py + "]");
    }
}
