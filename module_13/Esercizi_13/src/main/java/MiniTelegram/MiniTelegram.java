package MiniTelegram;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InvalidAttributeValueException;
import java.time.ZonedDateTime;
import java.util.*;

public class MiniTelegram {
    private static final MiniTelegram miniTelegram = new MiniTelegram();
    private static final long MAX_TOKEN_AGING_MINUTES = 15;
    private final Queue<Messaggio> messaggi;
    private final Map<UUID, Utente> utenti;
    private final Map<Token, UUID> tokens;

    private MiniTelegram(){
        messaggi = new ArrayDeque<>();
        utenti = new HashMap<>();
        tokens = new HashMap<>();
    }

    public static MiniTelegram getInstance() {
        return miniTelegram;
    }

    public Messaggio addMessaggio(String token, UUID destinatario, String content) throws IllegalArgumentException, CloneNotSupportedException {
        Optional<Token> tok = tokens.keySet().stream().filter(t->token.equals(t.getToken())).findFirst();
        if(tok.isEmpty())
            throw new MissingResourceException("Token non trovato", "Token", token);
        if(utenti.containsKey(tokens.get(tok.get())) && utenti.containsKey(destinatario)){
            Messaggio m = new Messaggio(utenti.get(tokens.get(tok.get())), utenti.get(destinatario), content);
            messaggi.add(m);
            return m;
        }
        throw new IllegalArgumentException("Messaggio non inviato");
    }

    public Messaggio editMessaggio(String token, UUID id, String content) throws MissingResourceException, InvalidAttributeValueException {
        Optional<Token> tok = tokens.keySet().stream().filter(t->token.equals(t.getToken())).findFirst();
        if(tok.isEmpty())
            throw new MissingResourceException("Token non trovato", "Token", token);
        for(Messaggio m: messaggi){
            if(id.equals(m.getId())){
                if(!tokens.get(tok.get()).equals(m.getMittente().getId())){
                    throw new InvalidAttributeValueException("You're not allowed to edit this message");
                }
                m.setContent(content);
                return m;
            }
        }
        throw new MissingResourceException("Messaggio non trovato", "Messaggio", id.toString());
    }

    public List<Messaggio> getMessaggi(String token, UUID destinatario) throws InvalidAttributeValueException {
        Optional<Token> tok = tokens.keySet().stream().filter(t->token.equals(t.getToken())).findFirst();
        if(tok.isEmpty() || destinatario.equals(tokens.get(tok.get()))){
            throw new InvalidAttributeValueException("You're not logged in or you're not allowed to download these messages");
        }
        return messaggi.stream()
            .filter(m->!m.isLetto() && ((
                    tokens.get(tok.get()).equals(m.getMittente().getId()) && destinatario.equals(m.getDestinatario().getId())
                ) || (
                    tokens.get(tok.get()).equals(m.getDestinatario().getId()) && destinatario.equals(m.getMittente().getId())
                )
            )).toList();
    }

    public Utente addUtente(Utente utente) throws CloneNotSupportedException, InstanceAlreadyExistsException{
        if(utenti.values().stream().filter(u->utente.getUsername().equals(u.getUsername())).count()<=0) {
            utenti.put(utente.getId(), utente);
            return (Utente) utente.clone();
        }
        throw new InstanceAlreadyExistsException("A user with same username already exists");
    }

    public String loginUtente(String username) throws InvalidAttributeValueException {
        Optional<Utente> user = utenti.values().stream().filter(u->username.equals(u.getUsername())).findFirst();
        if(user.isPresent()){
            //in a real case, token should contain at least a hash
            Token t = new Token(user.get());
            //doppioni impossibili, al più viene rigenerato il token per un utente
            tokens.put(t, user.get().getId());
            return "{\"token\":\""+t.getToken()+"\"}";
        }
        throw new InvalidAttributeValueException("Username does not exists");
    }

    public void logoutUtente(String token) throws InvalidAttributeValueException{
        Optional<Token> tok = tokens.keySet().stream().filter(t->token.equals(t.getToken())).findFirst();
        if(tok.isEmpty()){
            throw new InvalidAttributeValueException("You're not logged in");
        }
        tokens.remove(tok.get());
    }

    public void cleanTokens(){
        ZonedDateTime now = ZonedDateTime.now().minusMinutes(MAX_TOKEN_AGING_MINUTES);
        List<Token> tmp = tokens.keySet().stream()
                .filter(t->t.getIssueTime().isBefore(now)).toList();
        tmp.forEach(tokens::remove);
    }

    public Utente getUtente(String username) throws AttributeNotFoundException {
        Optional<Utente> user = utenti.values().stream().filter(u->username.equals(u.getUsername())).findFirst();
        if(user.isPresent()){
            return user.get();
        }
        throw new AttributeNotFoundException("No user has this username");
    }
}
