import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {
    private Scene currentScene;
    private int gameMode = -1;
    private Player player;
    public void setPlayer(Player p){player = p;}
    private Map map;
    private Pane root;

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
    private Text levelText = new Text();
    private TextField levelField = new TextField();
    private Button[] slBtns = new Button[2];

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
        this.root = new Pane();
        map = new Map(this);
        window_width = map.getSize_x() * MapTile.tileSize;
        window_height = map.getSize_y() * MapTile.tileSize;
        root.getChildren().add(map.getPane());
        if(mode == 0){
            player = new Player(map);
            map.setPlayer(player);
        }
        if(mode == 1) {
            window_width += buttonWidth;
            drawEditControls();
            defineButtons();
        }
        root.setPrefSize(window_width, window_height);
        Scene newScene = new Scene(root, window_width, window_height);
        registerInput(newScene, gameMode);
        currentScene = newScene;
        return newScene;
    }

    private void registerInput(Scene scene, int m){
        if(m == 0){
            scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    player.registerInput(keyEvent);
                }
            });
        }
        else if(m == 1){
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    map.registerInput(mouseEvent);
                }
            });
        }
    }

    public void setInput(Player player){
        if(this.player == null){
            setPlayer(player);
            registerInput(currentScene, 0);
        }else
            setPlayer(player);

    }

    private void shiftElements(){
        tileBtn.setTranslateX(window_width - buttonWidth);
        tileBtn.setTranslateY(buttonHeight * 4);

        levelText.setTranslateX(window_width - buttonWidth + textWidth / 2 - 20);
        levelText.setTranslateY(tileBtn.getTranslateY() + buttonHeight * 2);
        
        levelField.setTranslateX(window_width - buttonWidth + textWidth);
        levelField.setTranslateY(tileBtn.getTranslateY() + buttonHeight);

        for (int i = 0; i < 2; i++) {
            btns[i].setTranslateX(window_width - buttonWidth);
            btns[i].setTranslateY(i * buttonHeight);

            texts[i].setTranslateX(window_width - buttonWidth + textWidth/2 - 10);
            texts[i].setTranslateY((3 * buttonHeight) + (i * buttonHeight) - 12);

            tf[i].setTranslateX(window_width - buttonWidth + textWidth);
            tf[i].setTranslateY((2 * buttonHeight) + (i * buttonHeight));

            slBtns[i].setTranslateX(window_width - buttonWidth);
            slBtns[i].setTranslateY(levelText.getTranslateY() + (buttonHeight * (i + 1)));
        }
    }

    public void saveLevel(String name) {saveLevel(map, name);}
    public void saveLevel (Map map, String name) {
        int[][] data = map.saveLevel();
        PrintStream con = System.out;
        if(!name.endsWith(".txt")) name += ".txt";
        try{
            File f = new File(name);
            PrintStream ps = new PrintStream(f);
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
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public void loadLevel(String name){ loadLevel(this.map, name); }
    public void loadLevel(Map map, String name){
        if(!name.endsWith(".txt")) name += ".txt";
        try{
            File f = new File(name);
            Scanner scan = new Scanner(f);
            List<String> lineList = new ArrayList<String>();
            while(scan.hasNext())
                lineList.add(scan.nextLine());
            int[][] data = new int[lineList.size()][];
            for(int i = 0; i < lineList.size(); i++){
                Scanner lineScan = new Scanner(lineList.get(i));
                List<Integer> intList = new ArrayList<Integer>();
                while (lineScan.hasNext())
                    intList.add(lineScan.nextInt());
                data[i] =  new int[intList.size()];
                for(int j = 0; j < intList.size(); j++)
                    data[i][j] = intList.get(j);
            }
            map.loadLevel(data);
        }
        catch(FileNotFoundException e){
            System.out.println("File couldn't be found.");
        }
    }

    private void drawEditControls(){
        tileBtn.setTranslateX(window_width - buttonWidth);
        tileBtn.setTranslateY(buttonHeight * 4);
        tileBtn.setPrefWidth(buttonWidth);
        tileBtn.setPrefHeight(buttonHeight);
        tileBtn.setText(MapTile.findTileName(tileType));
        root.getChildren().add(tileBtn);

        levelText.setText("Name");
        levelText.prefWidth(textWidth);
        levelText.prefHeight(buttonHeight);
        levelText.setTranslateX(window_width - buttonWidth + textWidth / 2 - 20);
        levelText.setTranslateY(tileBtn.getTranslateY() + buttonHeight * 2);
        levelText.setTextAlignment(TextAlignment.LEFT);
        root.getChildren().add(levelText);

        levelField.setText("...");
        levelField.setPrefWidth(buttonWidth - textWidth);
        levelField.setPrefHeight(buttonHeight);
        levelField.setTranslateX(window_width - buttonWidth + textWidth);
        levelField.setTranslateY(levelText.getTranslateY() - buttonHeight/2);
        root.getChildren().add(levelField);

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                btns[i] = new Button("Generate Map");
                texts[i] = new Text("X:");
                tf[i] = new TextField(Integer.toString(map.getSize_x()));
                slBtns[i] = new Button("Save Map");
            } else if (i == 1) {
                btns[i] = new Button("Reset Map");
                texts[i] = new Text("Y:");
                tf[i] = new TextField(Integer.toString(map.getSize_y()));
                slBtns[i] = new Button("Load Map");
            }

            btns[i].setPrefSize(buttonWidth, buttonHeight);
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

            slBtns[i].setPrefSize(buttonWidth, buttonHeight);
            slBtns[i].setTranslateX(window_width - buttonWidth);
            slBtns[i].setTranslateY(levelText.getTranslateY() + (buttonHeight * (i + 1)));
            root.getChildren().add(slBtns[i]);
        }
    }

    private void defineButtons(){
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

        slBtns[0].setOnAction(e -> {
            System.out.println("Saving map...");
            saveLevel(map, levelField.getText());
        });

        slBtns[1].setOnAction(e -> {
            System.out.println("Loading map...");
            loadLevel(map, levelField.getText());
        });
    }
}


