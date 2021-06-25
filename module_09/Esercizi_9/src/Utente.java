import java.util.*;

public class Utente implements Cloneable{
    private final UUID userId;
    private final String username;
    private final Map<Ristorante, List<Piatto>> order;
    private final Map<Ristorante, List<Piatto>> orderHistory;

    public Utente(String username){
        userId = UUID.randomUUID();
        this.username = username;
        order = new HashMap<>();
        orderHistory = new HashMap<>();
    }
    private Utente(UUID userId, String username, Map<Ristorante, List<Piatto>> order, Map<Ristorante, List<Piatto>> orderHistory){
        this.userId = userId;
        this.username = username;
        this.order = new HashMap<>(order);
        this.orderHistory = new HashMap<>(orderHistory);
    }

    public boolean addPiatto(Ristorante r, Piatto p){
        List<Piatto> lst = new ArrayList<>();
        if(order.containsKey(r)){
            lst = order.get(r);
        }
        lst.add(p);
        return order.put(r, lst)!=null;
    }
    public boolean removePiatto(Ristorante r, Piatto p){
        if(order.containsKey(r)){
            List<Piatto> lst = order.get(r);
            lst.remove(p);
            order.put(r, lst);
            return true;
        }
        return false;
    }
    public List<Piatto> getOrderHistory(){
        List<Piatto> lst = new ArrayList<>();
        for(List<Piatto> l: order.values()){
            lst.addAll(new ArrayList<>(l));
        }
        for(List<Piatto> l: orderHistory.values()){
            lst.addAll(new ArrayList<>(l));
        }
        return lst;
    }

    public Map<Ristorante, List<Piatto>> getCurrentOrder() {
        return order;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<Ristorante> getCurrentRestaurantList(){
        return new ArrayList<>(order.keySet());
    }
    public Map<Ristorante, List<Piatto>> getOrderHistoryWithRestaurant(){
        Map<Ristorante, List<Piatto>> out = new HashMap<>(orderHistory);
        for (Ristorante ristorante : order.keySet()) {
            if(out.containsKey(ristorante)){
                List<Piatto > tmp = out.get(ristorante);
                tmp.addAll(order.get(ristorante));
                out.put(ristorante, tmp);
            }else{
                out.put(ristorante, order.get(ristorante));
            }
        }
        return out;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Utente(userId, username, order, orderHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return userId.equals(utente.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
