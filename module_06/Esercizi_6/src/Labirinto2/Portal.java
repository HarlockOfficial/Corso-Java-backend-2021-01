package Labirinto2;

public class Portal extends Entity{
    private Portal other;
    private final Pair<Integer> position;
    public Portal(Pair<Integer> position){
        super(false);
        this.position = position;
    }

    public void setPortal(Portal other){
        if(other==null) return;
        this.other = other;
    }

    public Portal getDestination() {
        return other;
    }

    public Pair<Integer> getPosition() {
        return position;
    }

    @Override
    public void action() {
        //warp does not act
    }
    @Override
    public String toString() {
        return "O";
    }

    @Override
    protected Object clone() {
        return new Portal(position);
    }
}
