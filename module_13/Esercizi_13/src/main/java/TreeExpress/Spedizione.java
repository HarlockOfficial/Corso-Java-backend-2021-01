package TreeExpress;

import java.util.Objects;
import java.util.UUID;

public class Spedizione implements Cloneable{
    private UUID id;
    private Utente mittente, destinatario;
    private double peso;
    private StatoSpedizione stato;

    private void init(UUID id, Utente mittente, Utente destinatario, double peso, StatoSpedizione stato) {
        this.id=id;
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.peso = peso;
        this.stato = stato;
    }

    public Spedizione(Utente mittente, Utente destinatario, double peso) {
        init(UUID.randomUUID(), mittente, destinatario, peso, StatoSpedizione.IN_CONSEGNA);
    }
    private Spedizione(UUID id, Utente mittente, Utente destinatario, double peso, StatoSpedizione stato){
        init(id, mittente, destinatario, peso, stato);
    }

    public UUID getId() {
        return id;
    }

    public Utente getMittente() {
        return mittente;
    }

    public Utente getDestinatario() {
        return destinatario;
    }

    public StatoSpedizione getStato() {
        return stato;
    }

    public void setConsegnata(){
        stato = StatoSpedizione.CONSEGNATA;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Spedizione(id, mittente, destinatario, peso, stato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spedizione that = (Spedizione) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
