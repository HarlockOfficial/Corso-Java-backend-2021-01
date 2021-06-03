import java.util.Arrays;

public class Anagrammi {
    public static void calculate(String a, String b){
        String out = "";
        if(a.length()!=b.length()){
            out = "non ";
        }else {
            char[] arr_a = a.toLowerCase().toCharArray();
            char[] arr_b = b.toLowerCase().toCharArray();
            Arrays.sort(arr_a);
            Arrays.sort(arr_b);
            for (int i = 0; i < arr_a.length; ++i) {
                if (arr_a[i] != arr_b[i]) {
                    out = "non ";
                    break;
                }
            }
        }
        System.out.println(out+"anagrammi");
    } 
}
