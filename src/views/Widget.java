package views;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Controller;
import model.Clock;
import renderers.Renderer;
import renderers.RendererFlag;

public class Widget extends Controller implements Runnable{
    
    public static final double SIZE = 500;
    
    private Clock clock;
    private Renderer renderer;
    
    private GraphicsContext gc;
    
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
        
        gc = canvas.getGraphicsContext2D();
        
        initGraphics();
        
        Scene scene = new Scene(clockBorderPane);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.centerOnScreen();
        
        start();
    }
    
    private void initGraphics(){
        
        renderer = new RendererFlag(gc, (int)SIZE, clock);
        
        gc.translate(SIZE/2, SIZE/2);
        gc.setLineCap(StrokeLineCap.ROUND);
        
        Font numbers = new Font(12);
        gc.setFont(numbers);
        gc.setTextAlign(TextAlignment.CENTER);
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