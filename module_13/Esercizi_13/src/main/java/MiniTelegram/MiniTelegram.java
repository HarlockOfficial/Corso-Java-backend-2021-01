package MiniTelegram;

import java.util.*;

public class MiniTelegram {
    private static final MiniTelegram miniTelegram = new MiniTelegram();
    private final Queue<Messaggio> messaggi;
    private final Map<UUID, Utente> utenti;

    private MiniTelegram(){
        messaggi = new ArrayDeque<>();
        utenti = new HashMap<>();
    }

    public static MiniTelegram getInstance() {
        return miniTelegram;
    }

    public Messaggio addMessaggio(UUID mittente, UUID destinatario, String content) throws IllegalArgumentException, CloneNotSupportedException {
        if(utenti.containsKey(mittente) && utenti.containsKey(destinatario)){
            Messaggio m = new Messaggio(utenti.get(mittente), utenti.get(destinatario), content);
            messaggi.add(m);
            return m;
        }
        throw new IllegalArgumentException("Messaggio non inviato");
    }

    public Messaggio editMessaggio(UUID id, String content) throws MissingResourceException{
        for(Messaggio m: messaggi){
            if(id.equals(m.getId())){
                m.setContent(content);
                return m;
            }
        }
        throw new MissingResourceException("Messaggio non trovato", "Messaggio", id.toString());
    }

    public List<Messaggio> getMessaggi(UUID mittente, UUID destinatario){
        return messaggi.stream()
                .filter(m->
                        (mittente.equals(m.getMittente().getId()) && destinatario.equals(m.getDestinatario().getId())) ||
                        (mittente.equals(m.getDestinatario().getId()) && destinatario.equals(m.getMittente().getId())))
                .toList();
    }

    public Utente addUtente(Utente utente) throws CloneNotSupportedException{
        utenti.put(utente.getId(), utente);
        return (Utente) utente.clone();
    }
}
