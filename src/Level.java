import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Level {
    private Scene currentScene;
    private int gameMode = -1;
    private Player player;
    private Map map;

    private int buttonWidth = 100;
    private int buttonHeight = 35;
    private int min = 1;
    private int max = 50;
    private int textWidth = 50;
    private int tileType = 0;

    private int window_width = AppWindow.window_width;
    private int window_height = AppWindow.window_height;

    private Button[] btns = new Button[2];
    private Text[] texts = new Text[2];
    private TextField[] tf = new TextField[2];
    private Button tileBtn = new Button();

    public Scene createMap(){
        Pane root = new Pane();
        root.setPrefSize(window_width, window_height);
        Map map = new Map(this);
        root.getChildren().add(map.getPane());
        Scene newScene = new Scene(root);
        return newScene;
    }
    public Scene createMap(int mode){
        this.gameMode = mode;
        Pane root = new Pane();
        map = new Map(this);
        window_width = map.getSize_x() * MapTile.tileSize;
        window_height = map.getSize_y() * MapTile.tileSize;
        root.getChildren().add(map.getPane());
        if(mode == 0){
            player = new Player(root, map);
            map.setPlayer(player);
        }
        if(mode == 1) {

            window_width += buttonWidth;

            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    btns[i] = new Button("Generate Map");
                    texts[i] = new Text("X:");
                    tf[i] = new TextField(Integer.toString(map.getSize_x()));
                } else if (i == 1) {
                    btns[i] = new Button("Reset Map");
                    texts[i] = new Text("Y:");
                    tf[i] = new TextField(Integer.toString(map.getSize_y()));
                }

                btns[i].setPrefWidth(buttonWidth);
                btns[i].setPrefHeight(buttonHeight);
                btns[i].setTranslateX(window_width - buttonWidth);
                btns[i].setTranslateY(i * buttonHeight);
                root.getChildren().add(btns[i]);

                texts[i].prefWidth(textWidth);
                texts[i].setTranslateX(window_width - buttonWidth + textWidth / 2 - 10);
                texts[i].setTranslateY((3 * buttonHeight) + (i * buttonHeight) - 12);
                texts[i].setTextAlignment(TextAlignment.LEFT);
                root.getChildren().add(texts[i]);

                tf[i].setPrefWidth(buttonWidth - textWidth);
                tf[i].setPrefHeight(buttonHeight);
                tf[i].setTranslateX(window_width - buttonWidth + textWidth);
                tf[i].setTranslateY((2 * buttonHeight) + (i * buttonHeight));
                root.getChildren().add(tf[i]);
            }

            btns[0].setOnAction(e -> {
                System.out.println("Generating...");
                map.generateMap(Integer.parseInt(tf[0].getText()), Integer.parseInt(tf[1].getText()));

                window_width = (map.getSize_x() * MapTile.tileSize) + buttonWidth;
                window_height = map.getSize_y() * MapTile.tileSize;
                shiftElements();
                root.setPrefSize(window_width, window_height);
            });

            btns[1].setOnAction(e -> {
                System.out.println("Resetting...");
                shiftElements();
                map.resetMap();
            });

            tileBtn.setTranslateX(window_width - buttonWidth);
            tileBtn.setTranslateY(buttonHeight * 4);
            tileBtn.setPrefWidth(buttonWidth);
            tileBtn.setPrefHeight(buttonHeight);
            tileBtn.setText(MapTile.findTileName(tileType));
            root.getChildren().add(tileBtn);

            tileBtn.setOnAction(e -> {
                tileType++;
                if (tileType > MapTile.tileAmount)
                    tileType = 0;
                String btnName = "Ground";
                if (tileType > 0 && tileType < MapTile.tileAmount)
                    btnName = MapTile.findTileName(tileType);
                else if (tileType == MapTile.tileAmount)
                    btnName = "Player";
                tileBtn.setText(btnName);
                map.tileType = tileType;
            });
        }
        Scene newScene = new Scene(root, window_width, window_height);
        root.setPrefSize(window_width, window_height);
        registerInput(newScene);
        currentScene = newScene;
        return newScene;
    }

    private void registerInput(Scene scene){
        if(gameMode == 0){
            scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    player.registerInput(keyEvent);
                }
            });
        }
        else if(gameMode == 1){
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    map.registerInput(mouseEvent);
                }
            });
        }
    }

    public void setInput(Player player){
        currentScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                player.registerInput(keyEvent);
            }
        });
    }

    private void shiftElements(){
        for (int i = 0; i < 2; i++) {
            btns[i].setTranslateX(window_width - buttonWidth);
            btns[i].setTranslateY(i * buttonHeight);

            texts[i].setTranslateX(window_width - buttonWidth + textWidth/2 - 10);
            texts[i].setTranslateY((3 * buttonHeight) + (i * buttonHeight) - 12);

            tf[i].setTranslateX(window_width - buttonWidth + textWidth);
            tf[i].setTranslateY((2 * buttonHeight) + (i * buttonHeight));
        }

        tileBtn.setTranslateX(window_width - buttonWidth);
        tileBtn.setTranslateY(buttonHeight * 4);
    }

    public void saveLevel(String name) throws FileNotFoundException {saveLevel(map, name);}
    public void saveLevel (Map map, String name) throws FileNotFoundException {
        int[][] data = map.saveLevel();
        PrintStream con = System.out;
        if(!name.endsWith(".txt")) name += ".txt";
        PrintStream ps = new PrintStream(new File(name));
        System.setOut(ps);
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        ps.close();
        System.setOut(con);
    }

    public void loadLevel(String name){ loadLevel(this.map, name); }
    public void loadLevel(Map map, String name){
        if(!name.endsWith(".txt")) name += ".txt";
        File f = new File(name);
        if(f.exists()){
            int[][] data = new int[map.getSize_x()][];

        }
        else System.out.println("File couldn't be found.");
    }
}


