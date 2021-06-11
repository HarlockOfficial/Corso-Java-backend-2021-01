package GestorePrenotazioni;

public class MainClass {
    public static void main(String[] args) {
        GestorePrenotazioni miniGestorePrenotazioni =
                new GestorePrenotazioni(3, 5);
        Prenotazione p1 = new PrenotazioneSingola("12", Preferenza.ESTERNO);
        Prenotazione p2 = new PrenotazioneSingola("23", Preferenza.ESTERNO);
        Prenotazione p3 = new PrenotazioneSingola("34", Preferenza.INTERNO);
        Prenotazione p4 = new PrenotazioneSingola("56", Preferenza.ESTERNO);
        miniGestorePrenotazioni.prenota(p1);
        miniGestorePrenotazioni.prenota(p2);
        miniGestorePrenotazioni.prenota(p3);
        miniGestorePrenotazioni.prenota(p4);

        Prenotazione[] prenotazioniInternoArray = miniGestorePrenotazioni.prenotazioniAttualiInterno();
        Prenotazione[] prenotazioniEsternoArray = miniGestorePrenotazioni.prenotazioniAttualiEsterno();
        int prenotazioniInterno = 0, prenotazioniEsterno = 0;
        //contiamo e togliamo i null se presenti
        for (Prenotazione prenotazione : prenotazioniInternoArray)
            if (prenotazione != null) {
                prenotazioniInterno++;
            }
        for (Prenotazione prenotazione : prenotazioniEsternoArray)
            if (prenotazione != null) {
                prenotazioniEsterno++;
            }
        System.out.println(prenotazioniInterno == 1);
        System.out.println(prenotazioniEsterno == 3);
        Prenotazione p5 = new PrenotazioneGruppo("45", 2);
        miniGestorePrenotazioni.prenota(p5);
        prenotazioniInterno = 0;
        prenotazioniEsterno = 0;
        //contiamo e togliamo i null se presenti
        for (Prenotazione prenotazione : prenotazioniInternoArray)
            if (prenotazione != null) {
                prenotazioniInterno++;
            }
        for (Prenotazione prenotazione : prenotazioniEsternoArray)
            if (prenotazione != null) {
                prenotazioniEsterno++;
            }
        System.out.println(prenotazioniInterno + prenotazioniEsterno == 5);
        //verifichiamo i posti effettivamente riservati
        int postiTotali = 0;
        for (Prenotazione prenotazione : prenotazioniInternoArray)
            if (prenotazione != null) {
                postiTotali += prenotazione.getnPosti();
            }
        for (Prenotazione prenotazione : prenotazioniEsternoArray)
            if (prenotazione != null) {
                postiTotali += prenotazione.getnPosti();
            }
        System.out.println(postiTotali == 6);
        Prenotazione p6 = new PrenotazioneSingola("67", Preferenza.ESTERNO);
        boolean inserita = miniGestorePrenotazioni.prenota(p6);
        System.out.println(inserita);
    }
}
