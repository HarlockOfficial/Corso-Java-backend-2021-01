package Mini_E_Commerce;

import java.util.*;

public class Mini_E_Commerce {
    private final Map<UUID, Prodotto> prodotti;
    private static Mini_E_Commerce negozio = new Mini_E_Commerce();

    private Mini_E_Commerce(){
        prodotti = new HashMap<>();
    }

    public static Mini_E_Commerce getInstance() {
        return negozio;
    }

    public Prodotto addProdotto(Prodotto prodotto) throws IllegalArgumentException{
        if(!prodotti.containsKey(prodotto.getId())){
            prodotti.put(prodotto.getId(), prodotto);
            return prodotto;
        }
        Prodotto tmp = prodotti.get(prodotto.getId());
        tmp.setQuantitaStock(tmp.getQuantitaStock()+prodotto.getQuantitaStock());
        prodotti.put(prodotto.getId(), tmp);
        return tmp;
    }

    public Prodotto removeProdotto(UUID id) throws IllegalArgumentException{
        if(!prodotti.containsKey(id)){
            throw new IllegalArgumentException();
        }
        return prodotti.remove(id);
    }

    public List<Prodotto> getProdotto(){
        return new ArrayList<>(prodotti.values());
    }

    public boolean buyProdotto(UUID id, long quantity) throws IllegalArgumentException{
        if(!prodotti.containsKey(id)){
            throw new IllegalArgumentException();
        }
        Prodotto tmp = prodotti.get(id);
        if(tmp.getQuantitaStock()>=quantity){
            tmp.setQuantitaStock(tmp.getQuantitaStock()-quantity);
            prodotti.put(id, tmp);
            return true;
        }
        return false;
    }
}
