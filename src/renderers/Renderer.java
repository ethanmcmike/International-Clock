package renderers;

import javafx.scene.canvas.GraphicsContext;
import model.Clock;

public class Renderer {
    
    protected GraphicsContext gc;
    protected Clock clock;
    
    protected int size, hour, min, sec;
    
    public Renderer(GraphicsContext gc, int size, Clock clock){
        this.gc = gc;
        this.size = size;
        this.clock = clock;
    }
    
    public void render(){
        update();
        background();
        clock();
        timezones();
    }
    
    protected void update(){
        clock.update();
        hour = clock.getHour();
        min = clock.getMin();
        sec = clock.getSec();
    }
    
    protected void background(){
        
    }
    
    protected void clock(){
        
    }
    
    protected void timezones(){
        
    }
}
