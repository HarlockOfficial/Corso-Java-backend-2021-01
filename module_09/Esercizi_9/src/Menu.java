import java.util.*;

public class Menu {
    private final Set<Piatto> lista;

    public Menu(){
        lista = new HashSet<>();
    }
    public Menu(Collection<Piatto> lista) {
        this.lista = new HashSet<>(lista);
    }
    public Menu(Menu m){
        this.lista = new HashSet<>(m.lista);
    }
    public boolean addPiatto(Piatto p){
        return lista.add(p);
    }
    public boolean removePiatto(Piatto p){
        return lista.remove(p);
    }
    public boolean contains(Piatto p){
        return lista.contains(p);
    }
    public Piatto getPiatto(Piatto p) throws PiattoNotFoundException{
        if(lista.contains(p)){
            for (Piatto piatto : lista) {
                if(p.equals(piatto)){
                    return piatto;
                }
            }
        }
        throw new PiattoNotFoundException();
    }
    public boolean containsTipologia(TipoDiCucina t){
        for (Piatto piatto : lista) {
            if(t.equals(piatto.getTipologia())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Menu:\n");
        for (Piatto piatto : lista) {
            sb.append('\t').append(piatto.toString()).append('\n');
        }
        return sb.toString();
    }
}
