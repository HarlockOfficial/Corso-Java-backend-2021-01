public class Tabelline {
    public static int[] convert(int n, int m){
        int[] arr = new int[m];
        for(int i = 0; i<m; ++i){
            arr[i] = n*i;
        }
        return arr;
    }
}
