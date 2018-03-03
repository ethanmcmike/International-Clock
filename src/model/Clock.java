package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Clock {
    
    private Set<Timezone> zones;
    
    private int hour, min, sec;
    
    public Clock(){
        zones = new HashSet();
    }
    
    public void update(){
        Date date = new Date();
        hour = date.getHours();
        min = date.getMinutes();
        sec = date.getSeconds();
    }
    
    public void addTimezone(Timezone timezone){
        zones.add(timezone);
    }
    
    public Set getTimezones(){
        return zones;
    }
    
    public int getHour(){
        return hour;
    }
    
    public int getMin(){
        return min;
    }
    
    public int getSec(){
        return sec;
    }
}
