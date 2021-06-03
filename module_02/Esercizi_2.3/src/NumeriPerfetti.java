import java.util.Arrays;

public class NumeriPerfetti {
    public static int[] getDivisoriList(int n){
        return getDivisoriList(n, n);
    }
    public static int[] getDivisoriList(int n, int i){
        if(i==2){
            return (n%2==0?new int[] {1,2}: new int[] {1});
        }
        int[] old = getDivisoriList(n, i-1);
        int[] out;
        if(n%i==0){
            out = new int [old.length+1];
            out[out.length-1] = i;
            System.arraycopy(old, 0, out, 0, out.length - 1);
        }else{
            out = old;
        }
        return out;
    }
    public static boolean isPerfectNum(int n){
        return SommaArray.recSum(getDivisoriList(n))==2*n;
    }
    public static void main(String[] args) {
        for(int i = 1; i<=1000; ++i){
            System.out.println("Is "+i+" a perfect num? "+isPerfectNum(i));
        }
    }
}
