package Lampadina;

public class Lampadina {
    private boolean isCurrentPresent;
    private Status prevStatus;
    private Status currStatus;
    private final int maxSwitchCount;
    private int currSwitchCount;
    public Lampadina(int maxSwitchCount){
        currStatus = Status.OFF;
        prevStatus = Status.OFF;
        this.maxSwitchCount = maxSwitchCount;
        currSwitchCount = 0;
        isCurrentPresent = true;
    }
    public Lampadina(Status status, int maxSwitchCount){
        currStatus = status;
        prevStatus = status;
        this.maxSwitchCount = maxSwitchCount;
        currSwitchCount = 0;
        isCurrentPresent = true;
    }
    public Status stato(){
        return currStatus;
    }
    public void click(){
        if(currStatus == Status.ON){
            currStatus = Status.OFF;
            prevStatus = currStatus;
        }else if(currStatus == Status.OFF){
            if(isCurrentPresent) {
                currStatus = Status.ON;
                prevStatus = currStatus;
            }else{
                prevStatus = Status.ON;
            }
        }
        incCurrSwitchCount();
        brokenCheck();
    }

    private void brokenCheck() {
        if(currSwitchCount>maxSwitchCount){
            currStatus = Status.BROKEN;
            prevStatus = Status.BROKEN;
        }
    }

    public void swCurrent(){
        isCurrentPresent = !isCurrentPresent;
        if(!isCurrentPresent){
            prevStatus = currStatus;
            currStatus = (currStatus != Status.BROKEN ? Status.OFF: Status.BROKEN);
        }else{
            currStatus = prevStatus;
        }
    }

    public boolean isCurrentPresent(){
        return isCurrentPresent;
    }

    private void incCurrSwitchCount(){
        setCurrSwitchCount(currSwitchCount+1);
    }

    public void setCurrSwitchCount(int currSwitchCount) {
        this.currSwitchCount = currSwitchCount;
    }
}
