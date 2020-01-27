import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player {
    private Scene currentScene;
    private int x, y = 0;
    private Map map;

    public Player(Scene scene, Map map){
        currentScene = scene;
        this.map = map;
        movePlayer();
    }

    public Player(Scene scene, Map map, int x, int y){
        this.x = x;
        this.y = y;
        this.map = map;
        currentScene = scene;
        movePlayer();
    }

    public void movePlayer(){
        currentScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.W) {
                    if (y != 0) {
                        y--;
                    }
                }
                if (ke.getCode() == KeyCode.A) {
                    if (x != 0) {
                        x--;
                    }
                }
                if (ke.getCode() == KeyCode.S) {
                    if (y != map.getSize_y() - 1) {
                        y++;
                    }
                }
                if (ke.getCode() == KeyCode.D) {
                    if (x != map.getSize_x()) {
                        x++;
                    }
                }
            }
        });
        //System.out.println("Key Pressed: "+ ke.getCode());
        System.out.println("Player is @: ["+x +", "+y +"]");
    }
}
