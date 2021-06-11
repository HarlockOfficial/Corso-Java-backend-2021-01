package DistributoreAutomatico;

public class DistributoreDiBevande {
    private final Prodotto[] arr;
    private int index;
    private static final double INCREMENT_FACTOR = 1.5;
    private double currentCredit;
    public DistributoreDiBevande(int n){
        arr = new Prodotto[n];
        index = 0;
        currentCredit = 0.;
    }
    public boolean caricaProdotto(Prodotto p){
        if(index<arr.length-1){
            arr[index++] = p;
            return true;
        }
        return false;
    }
    public boolean inserisciImporto(double val){
        if(val>0){
            currentCredit+=val;
            return true;
        }
        return false;
    }
    public double saldoAttuale(){
        return currentCredit;
    }
    public double getResto(){
        double tmp = currentCredit;
        currentCredit = 0;
        return tmp;
    }
    public Prodotto scegliProdotto(String codiceProdotto){
        Prodotto p = null;
        for(int i = 0; i<arr.length; ++i){
            if(codiceProdotto.equals(arr[i].getCodice())){
                if(currentCredit>=arr[i].getPrezzo()){
                    currentCredit-=arr[i].getPrezzo();
                    p = arr[i];
                    arr[i] = null;
                    compress();
                }
                break;
            }
        }
        return p;
    }

    private void compress() {
        int nullFound = 0;
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] == null){
                ++nullFound;
            }else {
                arr[i - nullFound] = arr[i];
            }
        }
        index = arr.length-nullFound;
        for(int i=arr.length-1; i>=index; --i){
            arr[i] = null;
        }
    }
}
