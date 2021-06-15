package Labirinto2;

public class None extends Entity{
    None(){
        super(false);
    }

    @Override
    public void action() {
        //Air does not Act
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    protected Object clone() {
        return new None();
    }
}
