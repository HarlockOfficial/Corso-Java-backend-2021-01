package Lampadina3;

import Lampadina.Lampadina;

import java.util.Random;

public class Impianto {
    Lampadina[] l;
    public Impianto(int lampCount){
        l = new Lampadina[lampCount];
        for(int i = 0; i<l.length; ++i){
            l[i] = new Lampadina(5);
        }
    }
    Impianto(Lampadina[] l){
        this.l = l.clone();
    }
    public String getCurrentStatus(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<l.length; ++i) {
            sb.append("Lamp ").append(i).append(" status: ").append(l[i].stato()).append("\nIs Current Present: ").append(l[i].isCurrentPresent()).append("\n");
        }
        return sb.toString();
    }
    public void switchCurrent(){
        for(Lampadina lamp: l){
            lamp.swCurrent();
        }
    }
    public void clickLamp(int index){
        if(index<l.length)
            l[index].click();
    }
}
