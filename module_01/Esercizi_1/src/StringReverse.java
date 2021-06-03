public class StringReverse {
    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(reverseString(s));
    }
    public static String reverseString(String s){
        String[] arr = s.split(" ");
        return arr[1] + " " + arr[0];
    }
}
