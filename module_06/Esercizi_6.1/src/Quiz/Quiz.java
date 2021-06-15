package Quiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Quiz {
    private static int ID = 0;
    private final int quizID;
    private final String name;
    private final List<Question> domande;
    public Quiz(Collection<Question> domande, String name){
        this.name = name;
        this.domande = new ArrayList<>(domande);
        quizID = ID++;
    }

    public int getID() {
        return quizID;
    }

    public List<Question> getDomande() {
        return domande;
    }

    public String getName() {
        return name;
    }
    public int getQuestionQuantity(){
        return domande.size();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Quiz(domande, name);
    }
}
