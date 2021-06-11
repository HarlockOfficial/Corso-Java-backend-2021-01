package Classifica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Classifica {
    ArrayList<Squadra> squadre;
    public Classifica(Squadra[] squadre){
        this.squadre = new ArrayList<>(Arrays.asList(squadre));
    }
    public Squadra[] getClassifica(){
        squadre.sort(Collections.reverseOrder());
        return squadre.toArray(new Squadra[1]);
    }
    public Squadra getMigliorAttacco(){
        squadre.sort((o1, o2) -> {
            int comp = Integer.compare(o1.getGolFatti(), o2.getGolFatti());
            if(comp == 0){
                return o1.getNome().compareTo(o2.getNome());
            }
            return comp;
        });
        return squadre.get(squadre.size()-1);
    }
    public Squadra getPeggiorDifesa(){
        squadre.sort(Comparator.comparingInt(o -> o.getGolSubiti()));
        return squadre.get(squadre.size()-1);
    }
    public boolean esitoPartita(Squadra squadraCasa, int golCasa, Squadra squadraOspite, int golOspite){
        if(squadraCasa==null || squadraOspite==null || golCasa<0 || golOspite<0){
            return false;
        }
        Squadra a = null, b = null;
        for(Squadra s: squadre){
            if(squadraCasa.equals(s)){
                a = s;
            }
            if(squadraOspite.equals(s)){
                b = s;
            }
        }
        if(a==null || b==null) return false;
        a.addGolFatti(golCasa);
        a.addGolSubiti(golOspite);
        b.addGolFatti(golOspite);
        b.addGolSubiti(golCasa);
        if(golCasa>golOspite){
            a.addVittoria();
        }else if(golCasa<golOspite){
            b.addVittoria();
        }else{
            a.addPareggio();
            b.addPareggio();
        }
        return true;
    }
}
