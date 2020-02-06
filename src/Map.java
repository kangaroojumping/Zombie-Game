import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Map {
    private Level level;
    private int size_x = 25;
    private int size_y = 25;

    public int getSize_x(){return size_x;}
    public int getSize_y(){return size_y;}
    public MapTile[][] tiles;
    private Pane pane = new Pane();
    public Pane getPane(){return pane;}
    private Group panes = new Group();
    public int tileType = 0;

    private Player mapPlayer;
    private boolean hasPlayer = false;
    public void setPlayer(Player player){
        mapPlayer = player;
        hasPlayer = player != null;
    }
    public boolean getHasPlayer(){return hasPlayer;}
    public Player getPlayer(){return mapPlayer;}

    public Map(Level level, int x, int y){
        this.level = level;
        size_x = x;
        size_y = y;
        generateMap();
    }
    public Map(Level level){
        //Uses default values for map size
        this.level = level;
        generateMap();
    }

    private boolean generated = false;

    public void generateMap(int x, int y){
        this.size_x = x;
        this.size_y = y;
        generateMap();
    }
    public void generateMap(){
        if(generated)
            resetMap();

        tiles = new MapTile[size_x][];
        for(int i = 0; i < size_x; i++){
            tiles[i] = new MapTile[size_y];
            for(int j = 0; j < size_y; j++){
                tiles[i][j] = new MapTile(this, i, j);
                setPosition(tiles[i][j], i, j);
                panes.getChildren().add(tiles[i][j].getPane());
            }
        }
        pane.getChildren().add(panes);
        generated = true;
    }

    public void resetMap(){
        if(generated){
            tiles = new MapTile[size_x][];
            for(int i = 0; i < size_x; i++)
                tiles[i] = new MapTile[size_y];
            pane.getChildren().clear();
            panes.getChildren().clear();
            generated = false;
        }
    }

    private void setPosition(MapTile t, int x, int y){
        t.getPane().setTranslateX(x * MapTile.tileSize);
        t.getPane().setTranslateY(y * MapTile.tileSize);
    }

    public void registerInput(MouseEvent me){
        int px = (int)me.getX() / MapTile.tileSize;
        int py = (int)me.getY() / MapTile.tileSize;
        String coor = "[" + px + ", " + py + "]";
        if(px >= size_x) px = -1;
        if(py >= size_y) py = -1;
        //if(px == -1 || py == -1) System.out.println(); //Out of map bounds.
        if(px != -1 && py != -1) {
            //System.out.println("Click @ " + coor);
            if(tileType < MapTile.tileAmount){
                System.out.println("Setting " + coor + " to " + MapTile.findTileName(tileType));
                tiles[px][py].setTile(tileType);
            }
            else{
                if(tileType == MapTile.tileAmount){
                    if(hasPlayer)
                        mapPlayer.setPosition(px, py);
                    else{
                        tiles[px][py].setTile(tileType);
                        Player player = new Player(pane, this, px, py);
                        setPlayer(player);
                        level.setInput(player);
                    }
                    System.out.println("Player set to " + coor);
                }
            }
        }
    }
    public int[][] saveLevel(){
        int[][] t = new int[size_x][];
        for(int i = 0; i < size_x; i++){
            t[i] = new int[size_y];
            for(int j = 0; j < size_y; j++){
                t[i][j] = tiles[i][j].getTileType();
            }
        }
        return t;
    }

    public void loadLevel(int[][] t){
        if(generated)
            resetMap();

        size_x = t.length;
        size_y = t[0].length; //Should always be square
        //System.out.println(size_x + ", " + size_y);
        tiles = new MapTile[size_x][];
        for(int i = 0; i < size_x; i++){
            tiles[i] = new MapTile[size_y];
            //System.out.println();
            for(int j = 0; j < size_y; j++){
                tiles[i][j] = new MapTile(this, i, j, t[i][j]);
                //System.out.print(t[i][j] + " ");
                setPosition(tiles[i][j], i, j);
                panes.getChildren().add(tiles[i][j].getPane());
            }
        }
        pane.getChildren().add(panes);
        generated = true;
    }

}
