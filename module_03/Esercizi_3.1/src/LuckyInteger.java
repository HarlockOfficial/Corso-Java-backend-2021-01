import java.util.Arrays;

public class LuckyInteger {
    public static void main(String[] args) {
        System.out.println(find(new int[]{1,2,3,2,3,4,3,4,4,4,5}));
    }
    public static int find(int[] arr){
        Arrays.sort(arr);
        int occurrences = 1;
        int number = arr[arr.length-1];
        for(int i = arr.length-2; i>=0; --i){
            if(number!=arr[i]){
                if(occurrences == number){
                    return number;
                }
                number = arr[i];
                occurrences = 1;
            }else{
                ++occurrences;
            }
        }
        return -1;
    }
}
