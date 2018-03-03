package renderers;

import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import model.Clock;
import model.Timezone;

public class RendererManager{
    
    private Renderer renderer;
    private Clock clock;
    
    private int size;
    private final GraphicsContext gc;
    
    public RendererManager(Canvas canvas, Clock clock){
        this.clock = clock;
        
        size = (int)canvas.getWidth();
        gc = canvas.getGraphicsContext2D();
        
        renderer = new RendererFlag(gc, size, clock);
                
        initGraphics();
    }
    
    private void initGraphics(){
        gc.translate(size/2, size/2);
        gc.setLineCap(StrokeLineCap.ROUND);
    }
    
    public void render(){
        renderer.render();
    }
    
    private void drawBackground(){
        
        gc.setStroke(Color.gray(0.5));
        
        //Background circle
        gc.setFill(Color.gray(0.1));
        gc.fillArc(size/4, size/4, size/2, size/2, 0, 360, ArcType.OPEN);
        
        //Main ring
        gc.setLineWidth(1);
        arc(size/2, size/2, 0.45*size/2, 0, 360);
        
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
    
    private void arc(int x, int y, double r, int start, int stop){
        gc.strokeArc(x-r, y-r, 2*r, 2*r, start, stop, ArcType.OPEN);
    }
    
    private void drawLocal(){
        
        int sec = clock.getSec();
        int min = clock.getMin();
        int hour = clock.getHour();
        
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setColor(Color.BLACK);
        gc.setEffect(shadow);
        
        //Second hand
        gc.setLineWidth(1);
        double radius = 0.325;
        double t = sec * 2 * Math.PI / 60;
        double x = size/2 + Math.sin(t) * size/2 * radius;
        double y = size/2 - Math.cos(t) * size/2 * radius;
        gc.strokeLine(size/2, size/2, x, y);
        
        //Minute hand
        gc.setLineWidth(2);
        radius = 0.325;
        t = (min + (double)sec/60) * 2 * Math.PI / 60;
        x = size/2 + Math.sin(t) * size/2 * radius;
        y = size/2 - Math.cos(t) * size/2 * radius;
        gc.strokeLine(size/2, size/2, x, y);
        
        //Hour hand
        gc.setLineWidth(2);
        radius = 0.2;
        t = (hour + (double)min/60 + (double)sec/3600) * 2 * Math.PI / 12;
        x = size/2 + Math.sin(t) * size/2 * radius;
        y = size/2 - Math.cos(t) * size/2 * radius;
        gc.strokeLine(size/2, size/2, x, y);
        
        gc.setEffect(null);
    }
    
    private void drawTimezones(){
        
        double inner = 0.350;
        double outer = 0.625;
        
        int sec = clock.getSec();
        int min = clock.getMin();
                
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
    
    public static Image[] importFlags(){
        
        WritableImage[] flag = new WritableImage[15*16-2];
        
        Image img = new Image("res/flags.png");
        PixelReader pr = img.getPixelReader();
        PixelWriter pw;

        int x, y;           //Top left corner pixel of each flag
        int x0 = 1;
        int y0 = 7;
        int hmargin = 2;
        int vmargin = 12;
        
        for(int f=0; f<15*16-2; f++){          
            x = x0 + (f%16) * (30 + hmargin);
            y = y0 + (f/16) * (20 + vmargin);
            
            flag[f] = new WritableImage(30, 20);
            pw = flag[f].getPixelWriter();
            
            for(int u=0; u<30; u++){
                for(int v=0; v<20; v++){
                    pw.setArgb(u, v, pr.getArgb(x+u, y+v));
                }
            }
        }
        
        return flag;
    }
}
