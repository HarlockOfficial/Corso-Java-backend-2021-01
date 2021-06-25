import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Piatto {
    private final String nome;
    private final TipoDiCucina tipologia;
    private static final Map<String, Piatto> listaPiatti = new HashMap<>();
    private Piatto(String nome, TipoDiCucina tipologia){
        this.nome = nome;
        this.tipologia = tipologia;
    }
    public static Piatto getPiatto(String nome, TipoDiCucina tipologia){
        if(listaPiatti.containsKey(nome.trim().toLowerCase())){
            return listaPiatti.get(nome.trim().toLowerCase());
        }
        Piatto p = new Piatto(nome, tipologia);
        listaPiatti.put(nome.trim().toLowerCase(), p);
        return p;
    }

    public String getNome() {
        return nome;
    }

    public TipoDiCucina getTipologia() {
        return tipologia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piatto piatto = (Piatto) o;
        return nome.equals(piatto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Nome piatto: " + nome + " Tipologia cucina: " + tipologia.toString();
    }
}
