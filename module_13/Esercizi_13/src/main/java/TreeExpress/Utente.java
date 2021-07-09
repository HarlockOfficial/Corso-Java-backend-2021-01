package TreeExpress;

import java.util.Objects;
import java.util.UUID;

public class Utente implements Cloneable{
    private UUID id;
    private String nome, cognome, indirizzo;

    private void init(UUID id, String nome, String cognome, String indirizzo) throws IllegalArgumentException{
        if(nome.length()<=0 || cognome.length()<=0 || indirizzo.length()<=0){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    private Utente(UUID id, String nome, String cognome, String indirizzo) {
        init(id, nome, cognome ,indirizzo);
    }

    public Utente(String nome, String cognome, String indirizzo) throws IllegalArgumentException{
        init(UUID.randomUUID(), nome, cognome, indirizzo);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Utente(id, nome, cognome, indirizzo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id.equals(utente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
