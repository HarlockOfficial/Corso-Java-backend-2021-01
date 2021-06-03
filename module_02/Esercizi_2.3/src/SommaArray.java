import java.util.Arrays;

public class SommaArray {
    public static int recSum(int[] arr){
        if(arr.length<=0){
            return 0;
        }
        return arr[0]+recSum(Arrays.copyOfRange(arr, 1, arr.length));
    }
}
