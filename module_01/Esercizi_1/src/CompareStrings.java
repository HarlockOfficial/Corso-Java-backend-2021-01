public class CompareStrings {
    public static void main(String[] args) {
        String a = "ciao come va ?";
        String b = "cIao come vA ?";
        String c = "ciao come va ";
        System.out.println(compareStrings(a, b, c));
        System.out.println("2".equals(""+compareStrings(a, b, c)));
        a = "ciao come va ?";
        b = "cIaoo come vA ?";
        System.out.println(compareStrings(a, b, c));
        System.out.println("1".equals(""+compareStrings(a, b, c)));
        a = "ciao come va ?";
        b = "cIao come va ?";
        c = "ciao come vA ?";
        System.out.println("3".equals(""+compareStrings(a, b, c)));
    }

    private static int compareStrings(String a, String b, String c) {
        String a_1 = a.toLowerCase();
        String b_1 = b.toLowerCase();
        String c_1 = c.toLowerCase();
        int somma = 0;
        somma += a_1.equals(b_1)?1:0;
        somma += a_1.equals(c_1)?1:0;
        somma += b_1.equals(c_1)?1:0;
        return somma<3?somma+1:somma;
    }
}
