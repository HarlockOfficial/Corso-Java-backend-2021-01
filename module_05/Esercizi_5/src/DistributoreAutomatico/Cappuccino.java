package DistributoreAutomatico;

public class Cappuccino extends Prodotto {
    private static Cappuccino c=null;
    private Cappuccino(String codice, double prezzo) {
        super(codice, prezzo);
    }

    public static Cappuccino getInstance(String codice, double prezzo){
        if(c==null){
            c = new Cappuccino(codice, prezzo);
        }
        return c;
    }
}
