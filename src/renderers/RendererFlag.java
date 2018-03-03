package renderers;

import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import model.Clock;
import model.Timezone;

public class RendererFlag extends Renderer{
    
    public RendererFlag(GraphicsContext gc, int size, Clock clock){
        super(gc, size, clock);
    }
    
    @Override
    protected void background(){
        gc.clearRect(-size/2, -size/2, size, size);
        
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, size, size);
        
        //Circle
        gc.setFill(Color.gray(0.5));
        gc.fillOval(-size/4, -size/4, size/2, size/2);
        
        //Groove
        gc.setFill(Color.gray(0.1));
        gc.fillArc(size/4, size/4, size/2, size/2, 0, 360, ArcType.OPEN);
        
        //Main ring
        gc.setLineWidth(1);
//        arc(size/2, size/2, 0.45*size/2, 0, 360);
        
        //Numbers
        Font numbers = new Font(12);
        gc.setFont(numbers);
        gc.strokeText("12", size/2 - 3, size/2 - size/7);
        gc.strokeText("3", size/2 - 3 + size/7, size/2);
        gc.strokeText("6", size/2 - 3, size/2 + size/7);
        gc.strokeText("9", size/2 - 3 - size/7, size/2);
        
        //Large ticks
        gc.setLineWidth(0.75);
        
        double inner = 0.40;
        double outer = 0.45;
        
        for(int i=0; i<12; i++){
            double t = i * 2 * Math.PI / 12;
            double x1 = size/2 + Math.sin(t) * size/2 * inner;
            double y1 = size/2 - Math.cos(t) * size/2 * inner;
            double x2 = size/2 + Math.sin(t) * size/2 * outer;
            double y2 = size/2 - Math.cos(t) * size/2 * outer;
            
            gc.strokeLine(x1, y1, x2, y2);
        }
        
        //Small ticks
        gc.setLineWidth(0.25);
        
        inner = 0.425;
        outer = 0.450;
        
        for(int i=0; i<60; i++){
            double t = i * 2 * Math.PI / 60;
            double x1 = size/2 + Math.sin(t) * size/2 * inner;
            double y1 = size/2 - Math.cos(t) * size/2 * inner;
            double x2 = size/2 + Math.sin(t) * size/2 * outer;
            double y2 = size/2 - Math.cos(t) * size/2 * outer;
            
            gc.strokeLine(x1, y1, x2, y2);
        }
        
        //Center dot
        gc.setLineWidth(5);
        gc.strokeLine(size/2, size/2, size/2, size/2);
        
        
    }

    @Override
    protected void clock() {
    
    }

    protected void timezones() {
        
        double inner = 0.350;
        double outer = 0.625;
                
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        
        Set<Timezone> zones = clock.getTimezones();
        zones.forEach((zone) -> {
            gc.setLineWidth(3);
            
            int hour = clock.getHour() + zone.getOffset();
            if(hour>=24) hour-=24;
            
            //Line
            double t = (hour + (double)min/60 + (double)sec/3600) * 2 * Math.PI / 12;
            double x1 = size/2 + Math.sin(t) * size/2 * inner;
            double y1 = size/2 - Math.cos(t) * size/2 * inner;
            double x2 = size/2 + Math.sin(t) * size/2 * outer;
            double y2 = size/2 - Math.cos(t) * size/2 * outer;
            
            gc.setStroke(Color.gray(0.4));
            shadow.setColor(Color.BLACK);
            gc.setEffect(shadow);
            gc.strokeLine(x1, y1, x2, y2);
            gc.setEffect(null);
            
            //Flag
            gc.drawImage(zone.getFlag(), x2 - 15, y2 - 10);

            //AM/PM
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(1);
            shadow.setColor(Color.BLACK);
            gc.setEffect(shadow);
            Font ampm = new Font(10);
            gc.setFont(ampm);
            
            if(hour<12){
                gc.strokeText("AM", x2+8, y2+12);
            } else{
                gc.strokeText("PM", x2+8, y2+12);
            }
            
            gc.setEffect(null);
        });
    }
}
