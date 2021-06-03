
import java.util.Scanner;

public class ContaOccorrenze {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter character a:");
        String a = sc.nextLine();
        System.out.print("Enter string b:");
        String b =sc.nextLine();
        countOccurrences(a.charAt(0), b);
    }

    static void countOccurrences(char a, String b) {
        int counter = 0;
        for(char x : b.toCharArray()){
            if(x==a){
                counter +=1;
            }
        }
        System.out.println("Il carattere "+a+" e' persente in: "+b+" "+counter+" volte");
    }
}
