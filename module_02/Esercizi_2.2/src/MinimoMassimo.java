import java.util.Scanner;

public class MinimoMassimo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numbers (comma-separated):");
        String a = sc.nextLine();
        int[] values = parseNumbers(a);
        computeMinAndMax(values);
    }

    static int[] parseNumbers(String s) throws NumberFormatException{
        String[] arr = s.split(",");
        int[] out = new int[arr.length];
        for(int i = 0; i< arr.length; ++i){
            //May Throw exception
            out[i] = Integer.parseInt(arr[i].trim());
        }
        return out;
    }

    static void computeMinAndMax(int[] values) {
        if(values == null || values.length<=0){
           System.out.println("Valori non validi");
        }
        int min = values[0];
        int max = values[0];
        for(int x: values){
            if(min>x){
                min = x;
            }
            if(max<x){
                max = x;
            }
        }
        System.out.println("Minimum value: "+min+" Max value: "+max);
    }
}