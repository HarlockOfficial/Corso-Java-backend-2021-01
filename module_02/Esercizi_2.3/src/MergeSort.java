import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] arr){
        if(arr.length<=1){
            return arr;
        }
        int[] pt1 = mergeSort(Arrays.copyOfRange(arr, 0, arr.length/2));
        int[] pt2 = mergeSort(Arrays.copyOfRange(arr, arr.length/2, arr.length));
        return impera(pt1, pt2);
    }
    private static int[] impera(int[] arr, int[] arr2){
        int[] out = new int[arr.length+arr2.length];
        for(int i = 0, j = 0, k = 0; i<out.length; ++i){
            if(j<arr.length && k<arr2.length){
                if(arr[j]>arr2[k]){
                    out[i]=arr2[k++];
                }else{
                    out[i]=arr[j++];
                }
            }else if(j<arr.length){
                out[i]=arr[j++];
            }else{
                out[i]=arr2[k++];
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int[] arr = {6,5,4,3,2,0,1};
        mergeSort(arr);
    }
}
