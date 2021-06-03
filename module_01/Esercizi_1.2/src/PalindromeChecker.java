public class PalindromeChecker {
    public static void main(String[] args) {
        System.out.println(isPalindrome("c"));
        System.out.println(isPalindrome("ciic"));
        System.out.println(isPalindrome("aaaa"));
        System.out.println(!isPalindrome("absca"));
        System.out.println(!isPalindrome("abbaa"));
        System.out.println(isPalindrome("zzzzz"));
        System.out.println(isPalindrome("zzczz"));
        System.out.println(isPalindrome("aabbaa"));
    }

    private static boolean isPalindrome(String s) {
        boolean result = true;
        for(int i = 0, j = s.length()-1; i<s.length()/2;++i, --j){
            if(s.charAt(i)!=s.charAt(j)){
                result = false;
                break;
            }
        }
        return result;
    }
}
