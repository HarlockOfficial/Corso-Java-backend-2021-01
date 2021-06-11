package Autoveicoli;

public class Automobile extends Veicolo{
    private final short numeroPorte;
    public Automobile(String targa, String modello, Marca marca, short numeroPosti, short numeroPorte) {
        super(targa, modello, marca, numeroPosti);
        this.numeroPorte = numeroPorte;
    }

    @Override
    public String toString() {
        return super.toString()+":"+numeroPorte;
    }
}
