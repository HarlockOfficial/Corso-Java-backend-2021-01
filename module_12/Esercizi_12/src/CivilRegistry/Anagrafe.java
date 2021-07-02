package CivilRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrafe {
    private final Map<String, Persona> lista;

    public Anagrafe() {
        lista = new HashMap<>();
    }

    public boolean inserisciPersona(Persona p){
        if(p!=null && !lista.containsKey(p.getCF())){
            lista.put(p.getCF(), p);
            return true;
        }
        return false;
    }

    public boolean rimuoviPersona(String CF){
        return lista.remove(CF)!=null;
    }

    public List<Persona> ottieniPersonePerNome(String nome){
        return lista.values().stream().filter(p->p.getNome().startsWith(nome)).toList();
    }

    public List<Persona> ottieniPersonePiuAnziane(){
        return lista.values().stream().sorted((p1, p2) -> Integer.compare(p2.getEta(), p1.getEta())).limit(3).toList();
    }
    public List<String> ottieniIndirizzoPerNome(String nome){
        return ottieniPersonePerNome(nome).stream().filter(p->nome.equals(p.getNome())).map(Persona::getIndirizzo).toList();
    }
    public boolean addFigli(Persona p, Persona... figli){
        if(lista.containsKey(p.getCF())){
            return lista.get(p.getCF()).addFigli(figli);
        }
        return false;
    }

    public List<Persona> getFigli(String nomeGenitore){
        return lista.values().stream().filter(p->{
            Persona[] pers = p.getGenitori();
            return (pers[0].getNome()!=null && pers[0].getNome().equalsIgnoreCase(nomeGenitore)) ||
                    (pers[1].getNome()!=null && pers[1].getNome().equalsIgnoreCase(nomeGenitore));
        }).toList();
    }

    public List<Persona> getFigliAlt(String nomeGenitore){
        return lista.values().stream()
                .filter(p->nomeGenitore.equalsIgnoreCase(p.getNome()))
                .map(Persona::getFigli).flatMap(List::stream).toList();
    }
}
