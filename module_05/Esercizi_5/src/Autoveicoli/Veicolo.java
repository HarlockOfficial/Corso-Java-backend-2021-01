package Autoveicoli;

public abstract class Veicolo {
    private final String targa;
    private final Marca marca;
    private final String modello;
    private final short numeroPosti;
    Veicolo(String targa, String modello, Marca marca, short numeroPosti){
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.numeroPosti = numeroPosti;
    }

    public Marca getMarca() {
        return marca;
    }

    public short getNumeroPosti() {
        return numeroPosti;
    }

    public String getModello() {
        return modello;
    }

    public String getTarga() {
        return targa;
    }

    @Override
    public String toString(){
        return targa;
    }
}
