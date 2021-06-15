package Labirinto2;

public abstract class Entity{
    private final boolean canAct;
    protected Entity(boolean canAct){
        this.canAct = canAct;
    }
    public boolean canAct(){
        return canAct;
    }
    public abstract void action();
    @Override
    public abstract String toString();

    @Override
    protected abstract Object clone();
}
