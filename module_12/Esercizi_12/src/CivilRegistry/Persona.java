package CivilRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persona {
    private final String nome, cognome, CF, indirizzo;
    private final int eta;
    private final List<Persona> figli;
    private final Persona[] genitori;

    public Persona(String nome, String cognome, String CF, String indirizzo, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.indirizzo = indirizzo;
        this.eta = eta;
        figli = new ArrayList<>();
        genitori = new Persona[2];
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCF() {
        return CF;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getEta() {
        return eta;
    }

    public List<Persona> getFigli() {
        return figli;
    }

    public boolean addFigli(Persona... figli){
        for (Persona persona : figli) {
            if(persona.genitori[0]==null){
                persona.genitori[0] = this;
            }else if(persona.genitori[1] == null){
                persona.genitori[1] = this;
            }
        }
        return this.figli.addAll(Arrays.asList(figli));
    }

    public Persona[] getGenitori() {
        return genitori;
    }
}
