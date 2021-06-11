package Autoveicoli;

public class Autocarro extends Veicolo{
    private final long maxQuintali;

    public Autocarro(String targa, String modello, Marca marca, short numeroPosti, long maxQuintali) {
        super(targa, modello, marca, numeroPosti);
        this.maxQuintali = maxQuintali;
    }

    @Override
    public String toString() {
        return super.toString()+":"+maxQuintali;
    }
}
