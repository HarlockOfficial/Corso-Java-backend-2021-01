package MiniTelegram;

import java.util.Objects;
import java.util.UUID;

public class Utente implements Cloneable{
    private UUID id;
    private String username;

    private void init(UUID id ,String username) throws IllegalArgumentException{
        if(username.length()<=0){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.id = id;
        this.username = username;
    }

    public Utente(String username){
        init(UUID.randomUUID(), username);
    }
    private Utente(UUID id, String username){
        init(id, username);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Utente(id, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id.equals(utente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
