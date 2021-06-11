package DistributoreAutomatico;

public class Caffe extends Prodotto {
    private static Caffe c = null;
    private Caffe(String codice, double prezzo) {
        super(codice, prezzo);
    }
    public static Caffe getInstance(String codice, double prezzo){
        if(c==null){
            c = new Caffe(codice, prezzo);
        }
        return c;
    }
}
