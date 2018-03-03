package views;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Controller;

public class List extends Controller{

    @FXML BorderPane listPane;
    
    @Override
    public void init(Stage stage){
        this.stage = stage;
        
        Scene scene = new Scene(listPane);
        
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
    public void returnToWidget(){
        loadScene(stage, WIDGET);
    }
}