package Store;

import java.util.Objects;

public class Stock {
    private final Prodotto p;
    private int quantity;
    Stock(Prodotto p, int quantity){
        this.p = p;
        setQuantity(quantity);
    }
    public boolean takeFromStock(int quantity){
        if(this.quantity-quantity>=0){
            setQuantity(this.quantity-quantity);
            return true;
        }
        return false;
    }
    public boolean fillStock(int quantity){
        if(this.quantity+quantity>=0){
            setQuantity(this.quantity-quantity);
            return true;
        }
        return false;
    }
    private void setQuantity(int quantity) {
        if(quantity>=0)
            this.quantity = quantity;
        this.quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public Prodotto getProdotto() {
        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return p.equals(stock.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Stock((Prodotto) p.clone(), quantity);
    }
}
