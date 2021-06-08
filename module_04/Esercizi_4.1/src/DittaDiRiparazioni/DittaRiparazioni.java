package DittaDiRiparazioni;

import java.util.*;

public class DittaRiparazioni {
    Queue<Riparazione> toDo;
    List<Riparazione> inCorso;
    //Le riparazioni completate vengono eliminate,
    // mantenere lo storico può essere importante,
    // ma non è richiesto in questo caso
    List<Tecnico> dipendenti;
    DittaRiparazioni(){
        toDo = new PriorityQueue<>();
        inCorso = new ArrayList<>();
        dipendenti = new ArrayList<>();
    }
    public boolean addRiparazione(Riparazione r){
        if(r == null)
            return false;
        return toDo.add(r);
    }
    public List<Riparazione> getRiparazioniInAttesa(){
        return new ArrayList<>(toDo);
    }
    public Riparazione getNextMaxPriorityRepair(){
        return toDo.peek();
    }
    //si assume che la riparazione più importante abbia precedenza
    public void assign(Tecnico t){
        if(toDo.isEmpty())
            return;
        Riparazione r = toDo.poll();
        r.assegnaTecnico(t);
        dipendenti.get(dipendenti.indexOf(t)).setStatus(Status.ASSEGNATO);
        inCorso.add(r);
    }
    public boolean completeRepair(Tecnico t){
        Riparazione tmp = new Riparazione("", -1);
        tmp.assegnaTecnico(t);
        boolean out = inCorso.remove(tmp);
        changeStatus(t, Status.DISPONIBILE);
        return out;
    }
    public boolean addTecnico(Tecnico t){
        try {
            return dipendenti.add((Tecnico) t.clone());
        }catch (CloneNotSupportedException ex){
            //unused
            //impossible
        }
        return false;
    }
    public void setOnVacation(Tecnico[] t){
        for(Tecnico tec: t) {
            changeStatus(tec, Status.IN_VACANZA);
        }
    }
    public void setDisponibile(Tecnico[] t){
        for(Tecnico tec: t) {
            changeStatus(tec, Status.DISPONIBILE);
        }
    }
    private void changeStatus(Tecnico t, Status stat){
        dipendenti.get(dipendenti.indexOf(t)).setStatus(stat);
    }
}
