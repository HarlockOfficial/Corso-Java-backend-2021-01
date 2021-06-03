public class SquareCalculator {
    public long calculate(int n){
        long sum = 0;
        for(long i = 0, m = 1; i<n; ++i, m+=2){
            sum+=m;
        }
        return sum;
    }
}
