package Labirinto2;

public class Exit extends Entity{
    public Exit(){
        super(false);
    }
    @Override
    public void action() {
        //exit does not act
    }

    @Override
    public String toString() {
        return "E";
    }

    @Override
    protected Object clone() {
        return new Exit();
    }
}
