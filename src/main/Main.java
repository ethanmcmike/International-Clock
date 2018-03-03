package main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.Controller.LIST;
import static main.Controller.WIDGET;

public class Main extends Application{
    
    @Override
    public void start(Stage stage){
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("res/icon.png"));
        stage.setResizable(false);
        
        Controller controller = new Controller();
        controller.loadScene(stage, WIDGET);
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
