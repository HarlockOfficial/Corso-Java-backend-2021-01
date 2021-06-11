package GestorePrenotazioni;

import java.util.Objects;

public abstract class Prenotazione {
    private final String codiceUnivoco;
    Prenotazione(String codiceUnivoco){
        this.codiceUnivoco = codiceUnivoco;
    }
    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return codiceUnivoco.equals(that.codiceUnivoco);
    }
    public abstract int getPostiRiservati();
    @Override
    public int hashCode() {
        return Objects.hash(codiceUnivoco);
    }
}
