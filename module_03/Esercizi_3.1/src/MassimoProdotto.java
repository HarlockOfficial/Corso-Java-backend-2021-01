public class MassimoProdotto {
    public static long calcola(int[] arr) throws Exception{
        if(arr == null || arr.length == 0){
            throw new Exception("Cannot pass null or empty array");
        }
        if(arr.length==1){
            return arr[0];
        }
        int maxA = arr[0];
        int indexA = 0;
        for(int i = 1; i<arr.length; ++i){
            if(maxA<arr[i]){
                maxA = arr[i];
                indexA = i;
            }
        }
        int maxB = -1;
        for(int i = 1; i<arr.length; ++i){
            if(i != indexA && maxB<arr[i]){
                maxB = arr[i];
            }
        }
        return (long) maxA*maxB;
    }
}
