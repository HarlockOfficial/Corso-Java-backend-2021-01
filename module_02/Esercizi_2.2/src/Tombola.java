import java.util.Arrays;

public class Tombola {
    public static String  checkTombola(int[][] matr, int[] arr){
        if(matr==null || arr==null || matr.length!=3 || matr[0].length!=5 || arr.length<20){
            return "Parametri invalidi";
        }
        Arrays.sort(arr);
        int ambi=0, terne=0, quaterne=0, cinquine=0;
        for(int[] a: matr) {
            int row_counter = 0;
            for(int x: a) {
                if(Arrays.binarySearch(arr, x)>=0){
                    ++row_counter;
                }else{
                    if(row_counter==4){
                        ++quaterne;
                        ++terne;
                        ambi+=3;
                    }else if(row_counter==3){
                        ++terne;
                        ambi+=2;
                    }else if(row_counter==2){
                        ++ambi;
                    }
                    row_counter = 0;
                }
            }
            if(row_counter==5){
                ++cinquine;
                ++quaterne;
                ++terne;
                ambi+=4;
            }
        }
        if(cinquine==matr[0].length){
            return "Tombola";
        }
        return "Il giocatore ha totalizzato:\n\tambi: "+ambi+
                "\n\tterne: "+terne+"\n\tquaterne: "+quaterne+
                "\n\tcinquine: "+cinquine;
    }
}
