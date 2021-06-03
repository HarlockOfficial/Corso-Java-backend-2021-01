public class FactorialCalculator {
    public static void main(String[] args) {
        System.out.println(factorial(1) == (0));
        System.out.println(factorial(2) == (2));
        System.out.println(factorial(5) == (120));
        System.out.println(factorial(10) == (3628800));
        System.out.println(factorial(20) == 2432902008176640000L);
    }

    private static long factorial(int n) {
        if(n<=1){
            return 1;
        }
        long result = 1;
        for(int i = 2; i<=n; ++i){
            result *=i;
        }
        return result;
    }
}
