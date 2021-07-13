package MiniTelegram;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Messaggio implements Cloneable{
    private UUID id;
    private Utente mittente, destinatario;
    private String content;
    private boolean letto;
    private ZonedDateTime sendingTime;

    private void init(UUID id, Utente mittente, Utente destinatario, String content) throws IllegalArgumentException, CloneNotSupportedException {
        if(content.length()<=0){
            throw new IllegalArgumentException("Il messaggio non può essere vuoto");
        }
        this.id = id;
        this.mittente = (Utente) mittente.clone();
        this.destinatario = (Utente) destinatario.clone();
        this.content = content;
        sendingTime = ZonedDateTime.now();
    }

    public Messaggio(Utente mittente, Utente destinatario, String content) throws IllegalArgumentException, CloneNotSupportedException{
        init(UUID.randomUUID(), mittente, destinatario, content);
    }
    private Messaggio(UUID id, Utente mittente, Utente destinatario, String content) throws IllegalArgumentException, CloneNotSupportedException{
        init(id, mittente, destinatario, content);
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Utente getMittente() {
        return mittente;
    }

    public Utente getDestinatario() {
        return destinatario;
    }

    public ZonedDateTime getSendingTime() {
        return sendingTime;
    }

    public void setContent(String content) {
        if(content.length()<=0)
            throw new IllegalArgumentException("Il messaggio non può essere vuoto");
        this.content = content;
        letto = false;
    }

    public boolean isLetto() {
        return letto;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Messaggio(id, mittente, destinatario, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messaggio messaggio = (Messaggio) o;
        return id.equals(messaggio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
