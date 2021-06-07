import java.util.Arrays;

public class MinMaxArr {
    public static void main(String[] args) {
        minMaxRecursive(new int[]{-10, 20, 100, 12, -100, 868});
    }
    public static void minMaxRecursive(int[] array){
        int[] out = new int[2];
        recCall(out, array);
        System.out.println("Min = " + out[0] + "\nMax = " + out[1]);
        recCall2(out, array, 0);
        System.out.println("Min = " + out[0] + "\nMax = " + out[1]);
    }

    private static void recCall2(int[] minMax, int[] array, int i) {
        if(i>=array.length){
            return;
        }
        if(minMax[0]>array[i]){
            minMax[0] = array[i];
        }
        if(minMax[1]<array[i]){
            minMax[1] = array[i];
        }
        recCall2(minMax, array, i+1);
        return;
    }

    private static void recCall(int[] minMax, int[] array){
        if(array.length<=0){
            return;
        }
        if(minMax[0]>array[0]){
            minMax[0] = array[0];
        }
        if(minMax[1]<array[0]){
            minMax[1] = array[0];
        }
        recCall(minMax, Arrays.copyOfRange(array, 1, array.length));
        return;
    }
}
