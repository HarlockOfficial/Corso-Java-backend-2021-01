import java.util.Arrays;
import java.util.Scanner;

public class ArrayMerge {
    public static void init() {
        int[] arr1, arr2, arr3;
        Scanner sc = new Scanner(System.in);
        arr1 = initArr(sc);
        arr2 = initArr(sc);
        arr3=new int[arr1.length+arr2.length];
        for(int i=0;i<arr3.length;++i){
            arr3[i] = (i<arr1.length?arr1[i]:arr2[i-arr1.length]);
        }
        System.out.println(Arrays.toString(arr3));
    }
    private static int[] initArr(Scanner sc){
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;++i){
            arr[i]=sc.nextInt();
        }
        return arr;
    }
}
