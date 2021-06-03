public class Fibonacci {
    public static long calculate(long n){
        if(n == 0){
            return 0;
        }
        long a = 0 , b = 1;
        for(long i = 0;i<n-1;++i){
            long tmp = a;
            a = b;
            b += tmp;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(calculate(45));
    }
}
