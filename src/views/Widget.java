package views;

import renderers.RendererManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import model.Clock;

public class Widget extends Controller implements Runnable{
    
    public static final double SIZE = 500;
    
    private Clock clock;
    private RendererManager renderer;
    
    private Thread thread;
    
    @FXML BorderPane clockBorderPane;
    
    @Override
    public void init(Stage stage){
        this.stage = stage;
        
        clock = new Clock();
        
        Canvas canvas = new Canvas(SIZE, SIZE);
//        canvas.widthProperty().bind(stage.widthProperty());
//        canvas.heightProperty().bind(stage.heightProperty());
        canvas.setOnContextMenuRequested(openList);
        clockBorderPane.setCenter(canvas);
        
        renderer = new RendererManager(canvas, clock);
        
        Scene scene = new Scene(clockBorderPane);
        scene.setFill(Color.TRANSPARENT);
        
        stage.close();
        stage.setScene(scene);
        stage.show();
        
        start();
    }
    
    public void start(){
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }
    
    @Override
    public void run(){
        while(true){
            
            clock.update();
            renderer.render();
            
            try{
                thread.sleep(1000);
            } catch(Exception e){
                
            }
        }
    }
    
    private EventHandler openList = (EventHandler) (Event e) -> {
        loadScene(stage, LIST);
    };
}