package GestorePrenotazioni;

import java.util.ArrayList;
import java.util.List;

public class GestorePrenotazioni {
    private final int maxPostiInterni, maxPostiEsterni;
    private int postiInterniOccupati, postiEsterniOccupati;
    List<Prenotazione> postiInterni, postiEsterni;
    public GestorePrenotazioni(int postiInterni, int postiEsterni){
        this.maxPostiEsterni = postiEsterni;
        this.maxPostiInterni = postiInterni;
        postiEsterniOccupati = 0;
        postiInterniOccupati = 0;
        this.postiInterni = new ArrayList<>();
        this.postiEsterni = new ArrayList<>();
    }
    public boolean prenota(Prenotazione p){
        if(p==null) return false;
        if(p.getClass().equals(PrenotazioneSingola.class)){
            Preferenza pref = ((PrenotazioneSingola) p).getPreferenza();
            if(pref == Preferenza.ESTERNO){
                if(postiEsterniOccupati<maxPostiEsterni){
                    ++postiEsterniOccupati;
                    return postiEsterni.add(p);
                }
                if(postiInterniOccupati<maxPostiInterni){
                    ++postiInterniOccupati;
                    return postiInterni.add(p);
                }
            }else{
                if(postiInterniOccupati<maxPostiInterni){
                    ++postiInterniOccupati;
                    return postiInterni.add(p);
                }
                if(postiEsterniOccupati<maxPostiEsterni){
                    ++postiEsterniOccupati;
                    return postiEsterni.add(p);
                }
            }
        }else if(p.getClass().equals(PrenotazioneGruppo.class)){
            int numPosti = ((PrenotazioneGruppo) p).getPostiRiservati();
            if(postiInterniOccupati+numPosti<maxPostiInterni){
                postiInterniOccupati+=numPosti;
                return postiInterni.add(p);
            }
            if(postiEsterniOccupati+numPosti<maxPostiEsterni){
                postiEsterniOccupati+=numPosti;
                return postiEsterni.add(p);
            }
        }
        return false;
    }
    public void terminaPrenotazione(Prenotazione p){
        if(p==null) return;
        int toRemove = funct(postiInterni, p);
        postiInterniOccupati-=toRemove;
        toRemove = funct(postiEsterni, p);
        postiEsterniOccupati-=toRemove;
        checkPreferences();
    }
    public Prenotazione[] prenotazioniAttualiInterno(){
        return postiInterni.toArray(new Prenotazione[0]);
    }
    public Prenotazione[] prenotazioniAttualiEsterno(){
        return postiEsterni.toArray(new Prenotazione[0]);
    }

    //TODO find a name
    private int funct(List<Prenotazione> posti, Prenotazione p) {
        for(int i = 0; i<posti.size(); ++i){
            if(p.equals(posti.get(i))){
                int toRemove = getPostiOccupati(posti.get(i));
                if(toRemove == 0){
                    return 0;
                }
                posti.remove(i);
                return toRemove;
            }
        }
        return 0;
    }
    private void checkPreferences() {
        int i = 0;
        while(i<postiEsterni.size()){
            Prenotazione p = postiEsterni.get(i);
            if(p.getClass().equals(PrenotazioneSingola.class) &&
                    ((PrenotazioneSingola) p).getPreferenza()==Preferenza.INTERNO &&
                    postiInterniOccupati<maxPostiInterni){
                postiEsterni.remove(i);
                prenota(p);
            }else{
                ++i;
            }
        }
        i=0;
        while(i<postiInterni.size()){
            Prenotazione p = postiInterni.get(i);
            if(p.getClass().equals(PrenotazioneSingola.class) &&
                    ((PrenotazioneSingola) p).getPreferenza()==Preferenza.ESTERNO &&
                    postiEsterniOccupati<maxPostiEsterni){
                postiEsterni.remove(i);
                prenota(p);
            }else{
                ++i;
            }
        }
    }
    private int getPostiOccupati(Prenotazione p) {
        if(p.getClass().equals(PrenotazioneSingola.class))
            return 1;
        else if(p.getClass().equals(PrenotazioneGruppo.class)) {
            return ((PrenotazioneGruppo) p).getPostiRiservati();
        }
        return 0;
    }
}
