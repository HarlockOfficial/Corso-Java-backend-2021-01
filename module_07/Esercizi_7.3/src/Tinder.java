import java.util.*;

public class Tinder {
    private final Set<Utente> utenti;

    public Tinder() {
        utenti = new HashSet<>();
    }

    public Tinder(Collection<Utente> utenti) {
        this.utenti = new HashSet<>(utenti);
    }

    public boolean addUtente(Utente utente) {
        return utenti.add(utente);
    }

    public boolean removeUtente(Utente utente) {
        return utenti.remove(utente);
    }

    public Utente getUtente(Utente utente) {
        for (Utente u : utenti) {
            if (utente.equals(u)) {
                try {
                    return (Utente) u.clone();
                } catch (CloneNotSupportedException ex) {
                    //impossible
                    return null;
                }
            }
        }
        //if user is not present
        return null;
    }

    public Utente bestMatch(Utente utente) {
        List<Utente> matches = allMatches(utente);
        if (matches.size() > 0) {
            return matches.get(0);
        }
        return null;
    }

    public List<Utente> allMatches(Utente utente) {
        Map<Utente, Integer> matches = new HashMap<>();
        Set<GestoreInteressi.Interesse> interessiUtente = new HashSet<>(utente.getInteressi());
        for (Utente u : utenti) {
            if(utente.equals(u)) continue;
            Set<GestoreInteressi.Interesse> interessiOther = new HashSet<>(utente.getInteressi());
            interessiOther.retainAll(interessiUtente);
            if (interessiOther.size() > 0) {
                matches.put(u, interessiOther.size());
            }
        }
        List<Utente> out = new ArrayList<>(matches.keySet());
        out.sort((o1, o2) -> {
            int cmp = Integer.compare(matches.get(o2), matches.get(o1));
            if (cmp == 0) {
                cmp = o1.compareTo(o2);
            }
            return cmp;
        });
        return out;
    }
}
