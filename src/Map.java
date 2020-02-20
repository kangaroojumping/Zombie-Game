import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Map {
    private Level level;
    public Level getLevel(){return level;}

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
    public boolean getHasPlayer(){return hasPlayer;}
    public void setPlayer(Player player){
        if(mapPlayer != null) {
            boolean b = player != null;
            mapPlayer.displayPlayer(b);
            if (!b)
                mapPlayer.isActive = false;
        }
        //else if(level.getPlayer() == player && level.hasScene()) level.setInput(player);

        mapPlayer = player;
        hasPlayer = player != null;
    }
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

        if(hasPlayer)
            mapPlayer.displayPlayer(true);
    }

    public void resetMap(){
        if(generated){
            tiles = new MapTile[size_x][];
            for(int i = 0; i < size_x; i++)
                tiles[i] = new MapTile[size_y];
            pane.getChildren().remove(panes);
            panes.getChildren().clear();
            generated = false;

            setPlayer(null);
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
        if(px != -1 && py != -1)
            //System.out.println("Click @ " + coor);
        if(tileType <= MapTile.tileAmount) {
            System.out.println("Setting " + coor + " to " + MapTile.findTileName(tileType));
            tiles[px][py].setTile(tileType);
        }
        else {

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
        if(hasPlayer)
            t[mapPlayer.getX()][mapPlayer.getY()] = 2;
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
                int num = t[i][j];
                tiles[i][j] = new MapTile(this, i, j, num);
                //System.out.print(t[i][j] + " ");
                setPosition(tiles[i][j], i, j);
                panes.getChildren().add(tiles[i][j].getPane());
            }
        }
        pane.getChildren().add(panes);
        generated = true;

        if(hasPlayer) {
            level.setPlayer(mapPlayer);
            mapPlayer.displayPlayer(true);
        }
    }

}
