package DittaDiRiparazioni;

import java.util.Objects;

public class Riparazione implements Comparable<Riparazione>{
    private final String indirizzo;
    private final int livelloEmergenza;
    private Tecnico assegnato;

    Riparazione(String indirizzo, int livelloEmergenza){
        this.indirizzo = indirizzo;
        this.livelloEmergenza = livelloEmergenza;
        assegnato = null;
    }
    public void assegnaTecnico(Tecnico t){
        try {
            assegnato = (Tecnico) t.clone();
        }catch (CloneNotSupportedException ex){
            //unused
            //impossible
        }
    }
    public int getLivelloEmergenza() {
        return livelloEmergenza;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public int compareTo(Riparazione o) {
        return Integer.compare(livelloEmergenza, o.livelloEmergenza);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Riparazione that = (Riparazione) o;
        return assegnato.equals(that.assegnato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assegnato);
    }
}
