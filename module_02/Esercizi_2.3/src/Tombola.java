import java.util.Arrays;
import java.util.Random;

public class Tombola {
    private static final Random generator = new Random();
    private static final boolean[] numeri = new boolean[90];
    public enum Risultato{
        NULLA ("Nulla"),
        AMBO ("Ambo"),
        TERNA ("Terna"),
        QUATERNA ("Quaterna"),
        CINQUINA ("Cinquina"),
        TOMBOLA ("Tombola");
        private String s;
        Risultato(String s){
            this.s=s;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    public static byte[][] generaCartella(){
        byte[][] cartella = new byte[3][5];
        byte[] elementiInseriti = new byte[15];
        for(int i = 0; i<15; ++i) {
            byte elem;
            do {
                elem = (byte) (generator.nextInt(90) + 1);
            } while (arrayContains(elementiInseriti, i, elem));
            elementiInseriti[i] = elem;
            cartella[i/5][i%5] = elem;
        }
        return cartella;
    }

    private static boolean arrayContains(byte[] arr, int len, byte elem){
        for(byte b: arr){
            if(b==elem){
                return true;
            }
        }
        return false;
    }

    public static byte extractNumber(){
        byte b;
        do{
            b = (byte) generator.nextInt(90);
        }while(numeri[b]);
        numeri[b] = true;
        return (byte)(b+1);
    }

    public static Risultato check(byte[][] cartella, boolean[][] controllati, short numero){
        for(int i = 0; i<cartella.length; ++i){
            for(int j = 0; j<cartella[i].length; ++j){
                if(cartella[i][j]==numero){
                    controllati[i][j] = true;
                    return checkTabella(controllati, i, j);
                }
            }
        }
        return Risultato.NULLA;
    }
    private static Risultato checkRiga(boolean[] riga, int col){
        if(col==-1){
            col = 0;
        }
        short cont = 0;
        for(int i = col; i<riga.length; ++i){
            if(riga[i]){
                ++cont;
            }else{
                break;
            }
        }
        for(int i = col-1; i>=0; --i){
            if(riga[i]){
                ++cont;
            }else{
                break;
            }
        }
        Risultato out = Risultato.NULLA;
        switch (cont){
            case 5:
                out = Risultato.CINQUINA;
                break;
            case 4:
                out = Risultato.QUATERNA;
                break;
            case 3:
                out = Risultato.TERNA;
                break;
            case 2:
                out = Risultato.AMBO;
                break;
            default:
                break;
        }
        return out;
    }
    private static Risultato checkTabella(boolean[][] tabella, int row, int col){
        boolean isTombola = true;
        Risultato out = Risultato.NULLA;
        for(int i = 0; i<tabella.length; ++i){
            if(i==row){
                out = checkRiga(tabella[i], col);
                if(out != Risultato.CINQUINA){
                    isTombola = false;
                    break;
                }
            }else if(checkRiga(tabella[i], -1)!=Risultato.CINQUINA){
                isTombola=false;
            }
        }
        return isTombola?Risultato.TOMBOLA:out;
    }
    public static void main(String[] args) {
        byte[][] cartella = generaCartella();
        boolean[][] checked = new boolean[cartella.length][cartella[0].length];
        for(byte[] riga: cartella){
            System.out.println(Arrays.toString(riga));
        }
        for(int i = 0; i<90; ++i){
            byte b = extractNumber();
            Risultato r = check(cartella, checked, b);
            System.out.println("Result of check with number "+b+": "+r);
        }
    }
}
