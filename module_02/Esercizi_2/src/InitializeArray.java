import java.util.Arrays;
import java.util.Scanner;

public class InitializeArray {
    public static void init(int n){
        int[] out = new int[n];
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i<n; ++i){
            out[i] = sc.nextInt();
        }
        int toSearch = sc.nextInt();
        Arrays.sort(out);
        System.out.println(Arrays.binarySearch(out, toSearch));
    }
}
