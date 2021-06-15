package Labirinto2;

public class Wall extends Entity{
    public Wall(){
        super(false);
    }
    @Override
    public void action() {
        //Wall does not act
    }

    @Override
    public String toString() {
        return "W";
    }

    @Override
    protected Object clone() {
        return new Wall();
    }
}
