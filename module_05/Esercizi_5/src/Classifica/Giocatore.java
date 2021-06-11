package Classifica;

import java.util.Objects;

public class Giocatore {
    private final int id;
    private final String nome, cognome;
    Giocatore(int id, String nome, String cognome){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return id == giocatore.id && nome.equals(giocatore.nome) && cognome.equals(giocatore.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome);
    }
}
