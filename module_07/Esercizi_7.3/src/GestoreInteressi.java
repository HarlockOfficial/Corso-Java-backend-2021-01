import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GestoreInteressi {
    private static int ID = 0;
    private static GestoreInteressi gestore = null;
    private final Map<String, Interesse> listaInteressi;

    private GestoreInteressi() {
        listaInteressi = new HashMap<>();
    }

    public static GestoreInteressi getInstance() {
        if (gestore == null) {
            gestore = new GestoreInteressi();
        }
        return gestore;
    }

    public Interesse getInteresse(String nome) {
        if (!listaInteressi.containsKey(nome)) {
            listaInteressi.put(nome, new Interesse(++ID, nome));
        }
        try {
            return (Interesse) listaInteressi.get(nome).clone();
        } catch (CloneNotSupportedException ex) {
            //impossible
            return null;
        }
    }

    static class Interesse implements Cloneable {
        private final int codice;
        private final String nome;

        public Interesse(int codice, String nome) {
            this.codice = codice;
            this.nome = nome;
        }

        public int getCodice() {
            return codice;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interesse interesse = (Interesse) o;
            return nome.equals(interesse.nome);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Interesse(codice, nome);
        }
    }
}
