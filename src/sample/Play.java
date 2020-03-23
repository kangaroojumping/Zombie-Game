package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import javafx.util.*;

public class Play {

    public static void display(String title, String message){
        Stage window = new Stage(); // new window
        window.initModality(Modality.APPLICATION_MODAL);// block other interaction from user unless this box is closed
        window.setTitle(title);
        window.setMaxWidth(250);

        Label label = new Label() ;
        label.setText(message);
        Button close = new Button("close") ;
        VBox layout = new VBox(10);
        layout.getChildren().add(label);

        Scene scene = new Scene(layout);
        window.showAndWait();




    }
}