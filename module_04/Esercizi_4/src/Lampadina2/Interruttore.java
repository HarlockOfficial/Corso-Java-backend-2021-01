package Lampadina2;

import Lampadina.Lampadina;
import Lampadina.Status;

public class Interruttore {
    private Lampadina lamp;
    Interruttore(Lampadina lamp){
        this.lamp = lamp;
    }
    public boolean switchLamp(){
        if(lamp.stato() == Status.BROKEN)
            return false;
        lamp.click();
        return lamp.stato()!= Status.BROKEN;
    }
}
