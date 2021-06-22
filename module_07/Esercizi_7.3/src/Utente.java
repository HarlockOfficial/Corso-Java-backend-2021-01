import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Utente implements Cloneable, Comparable<Utente> {
    private final String nome, cognome;
    private final Set<GestoreInteressi.Interesse> interessi;

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        interessi = new HashSet<>();
    }

    public Utente(String nome, String cognome, Collection<GestoreInteressi.Interesse> interessi) {
        this.nome = nome;
        this.cognome = cognome;
        this.interessi = new HashSet<>(interessi);
    }

    public boolean addInteresse(GestoreInteressi.Interesse interesse) {
        return interessi.add(interesse);
    }

    public boolean removeInteresse(GestoreInteressi.Interesse interesse) {
        return interessi.remove(interesse);
    }

    public Set<GestoreInteressi.Interesse> getInteressi() {
        return new HashSet<>(interessi);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Utente(nome, cognome, interessi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return nome.equals(utente.nome) && cognome.equals(utente.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }

    @Override
    public int compareTo(Utente o) {
        int cmp = cognome.compareTo(o.cognome);
        if (cmp == 0) {
            cmp = nome.compareTo(o.nome);
        }
        return cmp;
    }
}
