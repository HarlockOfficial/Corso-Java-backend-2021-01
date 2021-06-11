package GestorePrenotazioni;

public class PrenotazioneGruppo extends Prenotazione{
    private final int postiRiservati;

    PrenotazioneGruppo(String codiceUnivoco, int postiRiservati) {
        super(codiceUnivoco);
        this.postiRiservati = postiRiservati;
    }

    @Override
    public int getPostiRiservati() {
        return postiRiservati;
    }
}
