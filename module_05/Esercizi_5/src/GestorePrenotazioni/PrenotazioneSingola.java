package GestorePrenotazioni;

public class PrenotazioneSingola extends Prenotazione{
    private Preferenza pref;
    PrenotazioneSingola(String codiceUnivoco, Preferenza pref) {
        super(codiceUnivoco);
        this.pref = pref;
    }

    public Preferenza getPreferenza() {
        return pref;
    }

    public void setPref(Preferenza pref) {
        this.pref = pref;
    }
}
