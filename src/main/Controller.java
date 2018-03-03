package main;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Controller {
    
    public static final String WIDGET = "/views/Widget.fxml";
    public static final String LIST = "/views/List.fxml";
    public static final String NEW_TIMEZONE = "/views/newTimezone.fxml";
    
    protected Stage stage;
    
    public Controller(){
        
    }

    public void init(Stage stage){
        this.stage = stage;
    }
    
    public void loadScene(Stage stage, String path){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource(path);
            loader.setLocation(url);
            loader.load();
            
            Controller newController = loader.getController();
            newController.init(stage);
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
