package Store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Stock> prodotti;
    Store(){
        prodotti = new ArrayList<>();
    }
    public boolean aggiungiProdotto(Prodotto p, int quantity){
        if(prodotti.contains(new Stock(p, 0))){
            int index = prodotti.indexOf(new Stock(p, 0));
            if(index<0) return false;
            return prodotti.get(index).fillStock(quantity);
        }else{
            return prodotti.add(new Stock(p, quantity));
        }
    }
    public boolean rimuoviProdotto(Prodotto p){
        return prodotti.remove(new Stock(p,0));
    }
    public boolean rimuoviProdotto(Prodotto p, int quantity){
        int index = prodotti.indexOf(new Stock(p, 0));
        if(index>0 && quantity>=prodotti.get(index).getQuantity()){
            return rimuoviProdotto(p);
        }else if(index>0){
            return prodotti.get(index).takeFromStock(quantity);
        }
        return false;
    }
    public List<Stock> listaProdottiRimanenti(){
        return prodotti;
    }
    public int quantitaRimanente(Prodotto p){
        int index = prodotti.indexOf(new Stock(p, 0));
        if(index<0) return -1;
        return prodotti.get(index).getQuantity();
    }

    public float getPrice(Stock s, Cliente c, DayOfTheWeek d) throws Exception{
        if(prodotti.contains(s) && quantitaRimanente(s.getProdotto())>s.getQuantity()){
            float prezzoOrig = prodotti.get(prodotti.indexOf(s)).getProdotto().getPrezzo();
            if(c.getAge()>60 && (d==DayOfTheWeek.LUNEDI || d==DayOfTheWeek.MERCOLEDI)){
                prezzoOrig *= 0.8;
            }
            return prezzoOrig;
        }
        throw new Exception(s.getProdotto() + " non presente");
    }
}
