import java.util.Arrays;

public class Tartaglia {
    public static long[] recTartaglia(int n){
        if(n==0){
            return new long[]{1};
        }
        long[] old = recTartaglia(n-1);
        long[] out = new long[n+1];
        out[0] = out[n] = 1;
        for(int i = 1;i<n;++i){
            out[i] = old[i-1]+old[i];
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(recTartaglia(100)));
    }
}
