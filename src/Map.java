import javafx.scene.Group;
import javafx.scene.layout.StackPane;

public class Map {
    private int size_x = 25;
    private int size_y = 25;
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

    private void generateMap(){
        tiles = new MapTile[size_x][];
        for(int i = 0; i < size_x; i++){
            tiles[i] = new MapTile[size_y];
            for(int j = 0; j < size_y; j++){
                tiles[i][j] = new MapTile(i, j);
                tiles[i][j].getPane().setTranslateX(i * MapTile.tileSize);
                tiles[i][j].getPane().setTranslateY(j * MapTile.tileSize);
                panes.getChildren().add(tiles[i][j].getPane());
            }
        }
    }
}
