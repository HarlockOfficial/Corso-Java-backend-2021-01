package GutenbergAnalyser;

public class Book {
    private final long[] alf = new long[26];
    public synchronized void incLetter(Character c){
        c = c.toString().toLowerCase().toCharArray()[0];
        if(c<'a' || c>'z') return;
        ++alf[c-'a'];
    }
    public long[] getLetterCounter() {
        return alf.clone();
    }
}
