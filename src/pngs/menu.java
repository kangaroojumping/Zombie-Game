package pngs;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Play;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class menu extends Button {
    private final String FONT_PATH = "src/sample/pngs/files/Zombie_Holocaust.ttf";

    private final String BUTTON_FREE_STYLE = "-fx-background-image: url('sample/pngs/files/button2.png'); -fx-background-color : transparent";
    private Pane pane = new Pane();
    public Pane getPane() {return pane;}
    private Button btn;
    public Button getButton(){return btn;}
    public menu(String text) throws FileNotFoundException {
        setFONT_PATH();
        setPrefHeight(50);
        setPrefWidth(300);

        btn = new Button(text);
        btn.setTextFill(Color.rgb(255,16,16,1));
        btn.setFont(font);
        btn.setStyle( BUTTON_FREE_STYLE );
        pane.getChildren().add(btn);
//        btn.setOnAction(e-> Play.display("fuckthis","and fuck this"));
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { // setting for mouse entire the pic
                btn.setEffect(new DropShadow());
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() { //setting for mouse exit the pic
            @Override
            public void handle(MouseEvent event) {
                btn.setEffect(null);
            }
        });

    }

    private Font font;
    private void setFONT_PATH() {
        try {
            font = Font.loadFont(new FileInputStream(FONT_PATH), 30.0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    private void applyButtonStyle(){
//        setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)){
//                    setButton();
//                }
//            }
//        });
//    }
//
//
}

