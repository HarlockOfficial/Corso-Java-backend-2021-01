public class ToPari {
    public static int[] convert(int[] arr){
        for(int i = 0; i<arr.length; ++i){
            arr[i] = (arr[i]%2==0?arr[i]:2*arr[i]);
        }
        return arr;
    }
}
