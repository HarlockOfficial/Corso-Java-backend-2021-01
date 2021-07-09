package TreeExpress;

import java.nio.channels.FileLockInterruptionException;
import java.util.*;

public class TreeExpress {
    private static final TreeExpress express = new TreeExpress();
    private final Map<UUID, Spedizione> spedizioni;
    private final Map<UUID, Utente> utenti;
    private TreeExpress(){
        spedizioni = new HashMap<>();
        utenti = new HashMap<>();
    }

    public static TreeExpress getInstance() {
        return express;
    }

    public Utente addUtente(Utente utente){
        if(!utenti.containsKey(utente.getId()))
            utenti.put(utente.getId(), utente);
        return utenti.get(utente.getId());
    }

    public Utente getUtente(UUID utente){
        if(utenti.containsKey(utente))
            return utenti.get(utente);
        throw new MissingResourceException("Utente non presente", "Utente", utente.toString());
    }

    public Spedizione addSpedizione(Utente mittente, Utente destinatario, double peso){
        Spedizione s = new Spedizione(mittente, destinatario, peso);
        spedizioni.put(s.getId(), s);
        return s;
    }

    public Spedizione completeSpedizione(UUID id) throws MissingResourceException{
        if(!spedizioni.containsKey(id))
            throw new MissingResourceException("Spedizione non presente", "Spedizione", id.toString());
        Spedizione s = spedizioni.get(id);
        s.setConsegnata();
        spedizioni.put(id, s);
        return s;
    }

    public Spedizione deleteSpedizione(UUID id) throws MissingResourceException, IllegalStateException {
        if(!spedizioni.containsKey(id))
            throw new MissingResourceException("Spedizione non presente", "Spedizione", id.toString());
        if(spedizioni.get(id).getStato()!=StatoSpedizione.CONSEGNATA)
            return spedizioni.remove(id);
        throw new IllegalStateException("Spedizione già consegnata");
    }

    public Set<Spedizione> getSpedizioni() {
        return new HashSet<>(spedizioni.values());
    }
}
