public class StringaPalindroma {
    public static boolean recPalindoma(String s){
        if(s.length()<=1){
            return true;
        }
        return s.charAt(0)==s.charAt(s.length()-1) && recPalindoma(s.substring(1, s.length()-1));
    }
}
