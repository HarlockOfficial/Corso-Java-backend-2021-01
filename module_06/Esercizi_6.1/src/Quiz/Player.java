package Quiz;

public class Player {
    private Action azione = Action.NONE;
    private Quiz quiz = null;
    private int currentScore = 0, maxScore = 0, currentQuestion = 0;
    public Action getAzione() {
        return azione;
    }
    public void setQuiz(Quiz quiz) {
        try {
            if(quiz == null){
                azione = Action.NONE;
                this.quiz = null;
                return;
            }
            azione = Action.QUIZ;
            this.quiz = (Quiz) quiz.clone();
            currentScore = 0;
            currentQuestion = 0;
            maxScore = this.quiz.getQuestionQuantity();
        }catch(CloneNotSupportedException ex){
            System.err.println("Impossible exception: " + ex.getMessage());
        }
    }
    public boolean isQuizOver(){
        if(currentQuestion>=quiz.getQuestionQuantity()){
            azione = Action.NONE;
            return true;
        }
        return false;
    }
    public Question getNextQuestion(){
        try {
            return (Question) quiz.getDomande().get(currentQuestion).clone();
        }catch (CloneNotSupportedException ex){
            System.err.println("Impossible exception: " + ex.getMessage());
            //impossible exception
            return null;
        }
    }
    public void setAnswer(String answer){
        if(quiz.getDomande().get(currentQuestion++).isRispostaCorretta(answer)){
            ++currentScore;
        }
    }
    public int getCurrentScore() {
        return currentScore;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
