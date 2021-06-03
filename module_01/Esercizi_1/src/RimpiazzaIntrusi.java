import java.util.Scanner;

public class RimpiazzaIntrusi {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter string:");
        String a=sc.nextLine();
        System.out.print("Enter string:");
        String b=sc.nextLine();
        System.out.print("Enter string:");
        String c=sc.nextLine();
        replaceIntruder(a, b, c);
    }

    private static void replaceIntruder(String a, String b , String c) {
        String[] arr = c.split(" ");
        //assumo l'array contenga almeno 3 elementi vista la constraint dei 2 spazi
        String c_2 = arr[0];
        StringBuilder builder = new StringBuilder(arr[0]);
        for(int i = 1; i<arr.length-1;++i){
            c_2 += " "+arr[i].replaceAll(a,b);
            builder.append(" ").append(arr[i].replaceAll(a,b));
        }
        builder.append(arr[arr.length-1]);
        c = builder.toString();
        c = c_2;
        System.out.println(c);
    }
}