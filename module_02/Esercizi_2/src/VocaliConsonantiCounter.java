public class VocaliConsonantiCounter {
    public static void count(String s){
        int vocali = 0;
        int consonanti = 0;
        for(char c: s.toLowerCase().toCharArray()){
            switch (c){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    ++vocali;
                    break;
                case 'b':
                case 'c':
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    consonanti++;
                default:
                    break;
            }
            System.out.println("il parametro "+s+" contiene "+
                    vocali+" vocali, "+consonanti+" consonanti");
        }
    }
}
