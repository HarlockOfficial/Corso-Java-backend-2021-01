package MorraCineseACarte;

public class Game {
    private final Mazzo mazzo;
    private Simbolo[] manoGiocatore, manoCPU;
    public Game(){
        mazzo = new Mazzo();
        manoGiocatore = mazzo.getCarte();
        manoCPU = mazzo.getCarte();
    }
    public Simbolo[] getManoGiocatore() {
        return manoGiocatore;
    }

    public Simbolo[] getManoCPU() {
        return manoCPU;
    }
    public boolean isGameOver(){
        return mazzo.isGameOver();
    }
    public Risultato checkRisultato(Simbolo user, Simbolo cpu){
        if(user == cpu){
            return Risultato.PAREGGIO;
        }else if((user.getN()+1)%3 == cpu.getN()%3){
            return Risultato.VITTORIA_CPU;
        }else{
            return Risultato.VITTORIA_USER;
        }
    }
}
