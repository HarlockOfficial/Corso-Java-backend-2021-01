package DittaDiRiparazioni;

import java.util.Objects;

public class Tecnico implements Comparable<Tecnico>{
    private final String nome;
    private Status status;
    Tecnico(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public int compareTo(Tecnico o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tecnico tecnico = (Tecnico) o;
        return nome.equals(tecnico.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Tecnico(nome);
    }
}
