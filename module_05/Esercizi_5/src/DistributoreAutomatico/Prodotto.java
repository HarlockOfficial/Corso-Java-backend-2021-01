package DistributoreAutomatico;

public abstract class Prodotto {
    private final String codice;
    private final double prezzo;
    Prodotto(String codice, double prezzo){
        this.codice = codice;
        this.prezzo = prezzo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getCodice() {
        return codice;
    }
}
