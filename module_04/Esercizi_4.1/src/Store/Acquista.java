package Store;

import java.util.ArrayList;
import java.util.List;

public class Acquista {
    private final List<Stock> carrello;
    private final Cliente c;
    Acquista(Cliente c){
        carrello = new ArrayList<>();
        this.c = c;
    }
    public float pagaARate(int quanitaMesi, Store s){
        float price = checkout(s, DayOfTheWeek.NONE);
        return price/quanitaMesi;
    }
    public float checkout(Store store, DayOfTheWeek day){
        float prezzoTotale = 0f;
        for(Stock s: carrello){
            try {
                prezzoTotale += store.getPrice(s, c, day);
            }catch(Exception e){
                System.err.println("La quantita' richiesta non e' piu' disponibile");
                return -1;
            }
        }
        int puntiRegalo=(int)prezzoTotale/10;
        c.setPuntiRegalo(c.getPuntiRegalo() + puntiRegalo);
        puntiRegalo = c.getPuntiRegalo();
        int sconto = puntiRegalo/10;
        c.setPuntiRegalo(c.getPuntiRegalo() - (10*sconto));
        carrello.clear();
        return prezzoTotale-sconto;
    }
    public boolean addProduct(Store s, Prodotto p, int quantity){
        return addProduct(s, new Stock(p, quantity));
    }
    public boolean addProduct(Store store, Stock s){
        if(store.quantitaRimanente(s.getProdotto())>=s.getQuantity()){
            return carrello.add(s);
        }
        return false;
    }
    public float pagaProdottoSingolo(Store s, DayOfTheWeek d, Prodotto p, int quantity){
        List<Stock> tmp = new ArrayList<>(carrello);
        carrello.clear();
        addProduct(s, p, quantity);
        float result = checkout(s, d);
        for(Stock stock: tmp){
            if(!addProduct(s, stock)){
                System.err.println("Uno degli elementi che avevi nel carrello non e' piu' presente in quantita' sufficiente, pertanto e' stato rimosso");
            }
        }
        return result;
    }
}
