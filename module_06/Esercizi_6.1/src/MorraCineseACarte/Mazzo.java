package MorraCineseACarte;

public class Mazzo {
    private int cartaCounter = 0, forbiciCounter = 0, sassoCounter = 0, currentIndex = 0;
    private final Simbolo[] mazzo;

    public Mazzo() {
        mazzo = new Simbolo[60];
        generateMazzo();
    }

    private void generateMazzo() {
        int index = 0;
        for (int i = 0; i < mazzo.length; ++i) {
            Simbolo s = Simbolo.getRandom();
            mazzo[index] = s;
            cartaCounter += s == Simbolo.CARTA ? 1 : 0;
            forbiciCounter += s == Simbolo.FORBICI ? 1 : 0;
            sassoCounter += s == Simbolo.SASSO ? 1 : 0;
        }
    }

    public int getCartaCounter() {
        return cartaCounter;
    }

    public int getForbiciCounter() {
        return forbiciCounter;
    }

    public int getSassoCounter() {
        return sassoCounter;
    }

    public Simbolo[] getCarte(){
        Simbolo[] mano = {mazzo[currentIndex], mazzo[currentIndex+1], mazzo[currentIndex+2]};
        currentIndex += 3;
        return mano;
    }
    public boolean isGameOver(){
        return currentIndex>=mazzo.length;
    }
}
