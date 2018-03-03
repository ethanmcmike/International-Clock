//package model;
//
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//public class Menu {
//    
//    public static int size;
//    
//    public static int listY;
//    
//    public Menu(int size){
//        this.size = size;
//        
//        this.listY = 0;
//    }
//    
//    public static void draw(){
//        //Background
//        gc.clearRect(0, 0, size, size);
//        gc.setFill(Color.gray(0.1));
//        gc.fillRect(0, 0, size, size);
//        
//        //Outline
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(size/500D);
//        gc.strokeRect(size/17D, size/17D, size/17D*15, size/17D*15);
//        
//        //Dividers
//        
//        for(int i=0; i<5; i++){
//            gc.strokeLine(size/17D, size/17D*16 - (listY + size/17D*3*i)%(size/17D*15), size/17D*16, size/17D*16 - (listY + size/17D*3*i)%(size/17D*15));
//        }
//    
//        //Rectangles
//        gc.setFill(Color.gray(0.5));
//        gc.setFont(new Font("Comic Sans", 12.0));
//        
//        gc.fillRect(18, 18, 32, 22);
//        gc.strokeText("Kenya", 55, 34);
//        gc.strokeText("+8", 165, 34);
//        
//        gc.fillRect(18, 54, 32, 22);
//        gc.strokeText("United States", 55, 70);
//        gc.strokeText("-6", 165, 70);
//        
//        gc.drawImage(getFlag("Algeria"), 0, 0, 30, 20, 18, 89, 30, 20);
//        gc.strokeText("Longest Country Na", 55, 105);
//        gc.strokeText("+3", 165, 105);
//        
//        
//        
//    }
//    
//    public void scroll(double dy){
//        listY += dy;
//        if(listY < 0) listY = 0;
//        if(listY > 255) listY = 255;
//    }
//}
