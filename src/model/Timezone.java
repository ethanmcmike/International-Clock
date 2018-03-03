package model;

import javafx.scene.image.Image;

public class Timezone {
    
    private String name;
    private int offset;
    private Image flag;
    
    public Timezone(String name, int offset){   
        this.name = name;
        this.offset = offset;
//        this.flag = new Image("flags/" + name + ".png");
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setOffset(int offset){
        this.offset = offset;
    }
    
    public int getOffset(){
        return offset;
    }
    
    public void setFlag(Image flag){
        this.flag = flag;
    }
    
    public Image getFlag(){
        return flag;
    }
}