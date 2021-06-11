package Classifica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Squadra implements Comparable<Squadra>{
    private final int id;
    private final String nome;
    private final List<Giocatore> rosa;
    private int punteggio, golFatti, golSubiti;
    Squadra(int id, String nome, Collection<Giocatore> rosa){
        this.id = id;
        this.nome = nome;
        this.rosa = new ArrayList<>(rosa);
        punteggio = 0;
        golFatti = 0;
        golSubiti = 0;
    }
    public void addVittoria(){
        punteggio += 3;
    }
    public void addPareggio(){
        punteggio += 1;
    }
    public void addGolFatti(int quantitaGolFatti){
        golFatti += quantitaGolFatti;
    }
    public void addGolSubiti(int quantitaGolSubiti){
        golSubiti += quantitaGolSubiti;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getGolFatti() {
        return golFatti;
    }

    public int getGolSubiti() {
        return golSubiti;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public List<Giocatore> getRosa() {
        return rosa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return id == squadra.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Squadra o) {
        int comp = Integer.compare(this.punteggio, o.punteggio);
        if(comp == 0){
            comp = Integer.compare(this.golFatti-this.golSubiti, o.golFatti-o.golSubiti);
            if(comp == 0){
                comp = this.nome.compareTo(o.nome);
            }
        }
        return comp;
    }
}
