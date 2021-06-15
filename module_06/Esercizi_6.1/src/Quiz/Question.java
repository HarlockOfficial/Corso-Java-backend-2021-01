package Quiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Question {
    private final String domanda;
    private final List<String> risposte;
    private final String rispostaCorretta;
    public Question(String domanda, Collection<String> risposte, String rispostaCorretta){
        this.domanda = domanda;
        this.risposte = new ArrayList<>(risposte);
        this.rispostaCorretta = rispostaCorretta;
    }

    public List<String> getRisposte() {
        return risposte;
    }

    public String getDomanda() {
        return domanda;
    }
    public boolean isRispostaCorretta(String rispostaScelta){
        return rispostaScelta.equals(rispostaCorretta);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Question(domanda, risposte, rispostaCorretta);
    }
}
