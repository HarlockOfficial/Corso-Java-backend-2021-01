import java.util.Arrays;

public class MissingNumber {
    public static int find(int[] arr){
        Arrays.sort(arr);
        for(int i = 0; i<arr.length; ++i){
            if(i!=arr[i]){
                return i;
            }
        }
        return -1;
    }
}
