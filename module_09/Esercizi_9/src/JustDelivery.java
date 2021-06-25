import java.util.*;

public class JustDelivery {
    private static final JustDelivery app = new JustDelivery();
    private final Set<Utente> allUser;
    private final Set<Ristorante> allRestaurant;

    private JustDelivery() {
        allUser = new HashSet<>();
        allRestaurant = new HashSet<>();
    }

    public static JustDelivery getInstance() {
        return app;
    }

    public boolean addUser(Utente u) {
        return allUser.add(u);
    }

    public boolean addRestaurant(Ristorante r) {
        return allRestaurant.add(r);
    }
    public boolean addMenuToRestaurant(Menu m, Ristorante r) throws RestourantNotPresentException {
        if(!allRestaurant.contains(r)){
            throw new RestourantNotPresentException();
        }
        for (Ristorante ristorante : allRestaurant) {
            if (r.equals(ristorante)) {
                r.setMenu(m);
                return true;
            }
        }
        return false;
    }
    public Set<Ristorante> searchRestaurantByTipoCucina(TipoDiCucina t){
        Set<Ristorante> out = new HashSet<>();
        for (Ristorante ristorante : allRestaurant) {
            if(ristorante.getMenu().containsTipologia(t)){
                out.add(ristorante);
            }
        }
        return out;
    }
    public void printMenu(Ristorante r){
        for (Ristorante ristorante : allRestaurant) {
            if(r.equals(ristorante)) {
                System.out.println(ristorante);
                break;
            }
        }
    }
    public boolean addProductToOrder(Utente user, Piatto prodotto) throws RestourantNotPresentException {
        Ristorante r = null;
        for (Ristorante ristorante : allRestaurant) {
            if(ristorante.getMenu().contains(prodotto)){
                r = ristorante;
                break;
            }
        }
        if(r==null) throw new RestourantNotPresentException();
        if(allUser.contains(user)){
            for (Utente utente : allUser) {
                if(user.equals(utente)){
                    return user.addPiatto(r, prodotto);
                }
            }
        }
        return false;
    }
    public void printDetailedOrder(Utente user) throws UserNotFoundException {
        StringBuilder sb = new StringBuilder();
        for (Utente utente : allUser) {
            if (user.equals(utente)) {
                Map<Ristorante, List<Piatto>> lista = utente.getCurrentOrder();
                sb.append("L'utente: ").append(utente.getUsername()).append(" sta acquistando:\n");
                for(Ristorante r: lista.keySet()){
                    sb.append("Dal Ristorante '").append(r.getNome()).append("'\n");
                    List<Piatto> p = lista.get(r);
                    p.sort(Comparator.comparing(Piatto::getNome));
                    for (Piatto piatto : p) {
                        sb.append("\t").append(piatto.toString()).append("\n");
                    }
                }
                break;
            }
        }
        if(sb.length()<=0){
            throw new UserNotFoundException();
        }
        System.out.println(sb);
    }
    public List<Piatto> getUserOrderHistory(Utente u) throws UserNotFoundException {
        for(Utente user: allUser){
            if(u.equals(user)){
                return user.getOrderHistory();
            }
        }
        throw new UserNotFoundException();
    }
    public SortedSet<Ristorante> getMostOrderedRestaurant(Utente u) throws UserNotFoundException {
        for(Utente user: allUser){
            if(u.equals(user)){
                Map<Ristorante, List<Piatto>> history = user.getOrderHistoryWithRestaurant();
                SortedSet<Ristorante> lst = new TreeSet<>((o1, o2) -> Integer.compare(history.get(o2).size(), history.get(o1).size()));
                lst.addAll(history.keySet());
                return lst;
            }
        }
        throw new UserNotFoundException();
    }
    public TipoDiCucina getPreferredTipoDiCucina(Utente u){
        Map<TipoDiCucina, Integer> preferences = new HashMap<>();
        for (Utente utente : allUser) {
            if(u.equals(utente)){
                List<Piatto> history = utente.getOrderHistory();
                for (Piatto piatto : history) {
                    TipoDiCucina tmp = piatto.getTipologia();
                    int val = 1;
                    if(preferences.containsKey(tmp)){
                        val = preferences.get(tmp)+1;
                    }
                    preferences.put(tmp, val);
                }
            }
        }
        List<TipoDiCucina> lst = new ArrayList<>(preferences.keySet());
        lst.sort(Comparator.comparingInt(preferences::get));
        return lst.get(0);
    }
    public Set<Ristorante> getNeverUsedConsigliato(Utente u){
        TipoDiCucina tipoPreferito = getPreferredTipoDiCucina(u);
        Set<Ristorante> listaRistoranti = searchRestaurantByTipoCucina(tipoPreferito);
        Set<Ristorante> out = new HashSet<>();
        for(Ristorante r: listaRistoranti){
            if(!u.getOrderHistoryWithRestaurant().containsKey(r)){
                out.add(r);
            }
        }
        return out;
    }
}
