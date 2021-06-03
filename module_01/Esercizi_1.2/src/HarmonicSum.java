public class HarmonicSum {
    public static void main(String[] args) {
        System.out.println(harmonicSum(0) == (0));
        System.out.println(harmonicSum(1) == (1));
        System.out.println(harmonicSum(2) == (3./2));
        System.out.println(harmonicSum(20) == (55835135./15519504));
    }

    private static double harmonicSum(int n) {
        if(n<=0){
            return 0;
        }
        double result = 0;
        for(int i=1; i<=n; ++i){
            result += 1.0/i;
        }
        return result;
    }
}
