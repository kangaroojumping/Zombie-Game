package ZEngine;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static ZEngine.MapTile.findRef;

public class Map {
    private Level level;
    public Level getLevel(){return level;}

    private int size_x = 25;
    private int size_y = 25;
    public int getSize_x(){return size_x;}
    public int getSize_y(){return size_y;}

    private MapTile[][] tiles;
    public MapTile tileAt(int x, int y){return tiles[x][y];}

    private Pane pane = new Pane();
    public Pane getPane(){return pane;}
    private Group panes = new Group();

    public void showMap(boolean show){
        //System.out.println("Map Visibility: " + show);
        pane.setVisible(show);
        panes.setVisible(show);
        chars.setVisible(show);
        for(Zombie z : zombies) z.show(show);
        for(Resistance r : resistance) r.show(show);
        for(Civilian c : civilians) c.show(show);
        for(ZItem n : items) n.show(show);
    }

    public int tileType = 0;

    public static int advanceTimer = 20;

    public int instanceTeam = -1;

    public void play(int team){
        instanceTeam = team;
    }

    /*
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
    */

    public Map(Level level, int x, int y){
        this.level = level;
        generateMap(x, y);
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
        pane.getChildren().add(chars);
        generated = true;

        //if(hasPlayer) mapPlayer.displayPlayer(true);
    }

    public void resetMap(){
        if(generated){
            tiles = new MapTile[size_x][];
            for(int i = 0; i < size_x; i++)
                tiles[i] = new MapTile[size_y];
            pane.getChildren().remove(panes);
            panes.getChildren().clear();
            generated = false;
            zombies.clear();
            resistance.clear();
            civilians.clear();
            items.clear();
            chars.getChildren().clear();
            pane.getChildren().remove(chars);
            //setPlayer(null);
        }
    }

    private void setPosition(MapTile t, int x, int y){
        t.getPane().setTranslateX(x * MapTile.tileSize);
        t.getPane().setTranslateY(y * MapTile.tileSize);
    }

    public boolean showingPlayer = false;
    public ZCharacter charHeld;

    public void registerInput(MouseEvent me){
        int px = (int)me.getX() / MapTile.tileSize;
        int py = (int)me.getY() / MapTile.tileSize;
        String coor = "[" + px + ", " + py + "]";
        if(px >= size_x) px = -1;
        if(py >= size_y) py = -1;
        //if(px == -1 || py == -1) System.out.println(); //Out of map bounds.
        if(px != -1 && py != -1){
            if (level.getGameMode() == 1){
                //if(tileType == 1) System.out.println("Setting " + coor + " to " + MapTile.findTileName(tileType));
                tiles[px][py].setTile(tileType);
            }
            else if(instanceTeam == -1) return;
            else if(instanceTeam < 4){
                MapTile t = tiles[px][py];
                int m = 0;
                //if(t.getContainedCharacter() == null || t.getContainedItem() == null) System.out.println("Empty space.");
                if(t.getContainedCharacter() != null){
                    var z = t.getContainedCharacter();
                    if(instanceTeam == findRef(z)) {
                        if (!showingPlayer) {
                            showingPlayer = true;
                            charHeld = z;
                            z.onClick();
                            m = 1;
                        }

                        else {
                            charHeld.displayOptions(false);
                            showingPlayer = false;
                            charHeld = null;
                            m = 2;
                        }
                    }
                    else if(showingPlayer){
                        charHeld.displayOptions(false);
                        tileAt(px - 1, py).switchChar(tileAt(charHeld.getX() , charHeld.getY()), charHeld);
                        charHeld.interactWithChar(z);
                        charHeld = null;
                        showingPlayer = false;
                        m = 3;
                    }
                }
                else if(charHeld != null){
                    charHeld.displayOptions(false);
                    t.switchChar(tileAt(charHeld.getX(), charHeld.getY()), charHeld);
                    charHeld = null;
                    showingPlayer = false;
                    m = 4;
                }
                System.out.println("Int: " + m);
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

        //if(hasPlayer) t[mapPlayer.getX()][mapPlayer.getY()] = 2;
        return t;
    }

    public void loadLevel(int[][] t){
        if(generated)
            resetMap();

        size_x = t.length;
        size_y = t[0].length; //Should always be rectangular
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
                if(tiles[i][j].getContainedCharacter() != null)
                    tiles[i][j].getContainedCharacter().show(true);
                if(tiles[i][j].getContainedItem() != null)
                    tiles[i][j].getContainedItem().show(true);
            }
        }
        pane.getChildren().add(panes);
        pane.getChildren().add(chars);
        generated = true;

        /*
        if(hasPlayer) {
            level.setPlayer(mapPlayer);
            mapPlayer.displayPlayer(true);
        }
        */
    }

    Group chars = new Group();
    private List<Zombie> zombies = new ArrayList<Zombie>();
    private List<Resistance> resistance = new ArrayList<Resistance>();
    private List<Civilian> civilians = new ArrayList<Civilian>();
    private List<ZItem> items = new ArrayList<ZItem>();

    public void addChar(ZCharacter z){
        if(z instanceof Zombie) zombies.add((Zombie)z);
        if(z instanceof Resistance) resistance.add((Resistance)z);
        if(z instanceof Civilian) civilians.add((Civilian) z);
        z.show(true);
        chars.getChildren().add(z.pane);
    }

    public void killChar(ZCharacter z){
        if(z instanceof Zombie) { zombies.remove(z); return; }
        if(z instanceof Resistance) resistance.remove(z);
        if(z instanceof Civilian) civilians.remove(z);
        chars.getChildren().remove(z.pane);
    }

    public void convertToResistance(ZItem item, Civilian target){
        if(item.holder != null && target instanceof Civilian && target.heldItem == null){
            int x = target.getX();
            int y = target.getY();
            chars.getChildren().remove(target);
            civilians.remove(target);
            Resistance r = new Resistance(this);
            addChar(r);
            r.setPosition(x, y);
            item.holder.supplyItem = null;
            r.heldItem = item;
        }
    }

    public void addItem(ZItem n){
        items.add(n);
        chars.getChildren().add(n.pane);
    }

    public void deleteItem(ZItem n){
        items.remove(n);
        chars.getChildren().remove(n);
    }
}
