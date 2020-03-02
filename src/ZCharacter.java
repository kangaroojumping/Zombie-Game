import javafx.scene.layout.Pane;

public class ZCharacter {
    private String characterType;
    public String getCharacterType(){return characterType;}

    private int maxHP = 3;
    public int getMapHP(){return maxHP;}
    private int currentHP = maxHP;
    public int getCurrentHP(){return currentHP;}

    private int speed = 3;
    public int getSpeed(){return speed;}

    private int x, y;
    public int getX(){return x;}
    public int getY(){return y;}
    public void moveTo(int x, int y){}

    private Map map;
    public Map getMap(){return map;}

    private Pane pane;
    public Pane getPane(){return pane;}

    public void onClick(){
        //Called when clicked from map
        displayOptions();
    }
    public void displayOptions(){
        // Show (and color) all tiles available to move to
        // Show (different color) tiles that can be interacted with
        // Blue for movement, red for enemies, green for items?
        // Tell map that the player is interacting with this character,
        // Momentarily pause other map items from being interacted with

        // If interact with object, interactWithObjectAt(area clicked);
        // If that object is ZCharacter, also call thatObject.beInteractedWith(this);
    }

    public void interactWithObjectAt(int x, int y){ /* override method */ }
    public void beInteractedWith(ZCharacter z){ /* also override method */ }
}
