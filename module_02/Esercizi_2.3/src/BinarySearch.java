import java.util.Arrays;

public class BinarySearch {
    public static boolean search(int[] arr, int n){
        if(arr.length<=0){
            return false;
        }
        if(arr[arr.length/2]<n){
            return search(Arrays.copyOfRange(arr, (arr.length/2)+1, arr.length), n);
        }
        if(arr[arr.length/2]>n){
            return search(Arrays.copyOfRange(arr, 0, arr.length/2), n);
        }
        return arr[arr.length/2]==n;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(search(arr, 7));
        System.out.println(search(arr, 3));
    }
}
