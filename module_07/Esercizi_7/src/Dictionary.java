import java.util.*;

public class Dictionary {
    private final SortedMap<Character, SortedMap<String, SortedSet<String>>> dict;
    public Dictionary(){
        dict = new TreeMap<>();
    }
    public void insertWord(String word, String meaning){
        SortedMap<String, SortedSet<String>> words;
        SortedSet<String> lst;
        if(dict.containsKey(word.charAt(0))){
            words = dict.get(word.charAt(0));
            if(words.containsKey(word)){
                lst = words.get(word);
            }else{
                lst = new TreeSet<>();
            }
        }else{
            words = new TreeMap<>();
            lst = new TreeSet<>();
        }
        lst.add(meaning);
        words.put(word, lst);
        dict.put(word.charAt(0), words);
    }

    public void addMeanings(String word, Collection<String> meanings) throws LetterNotPresentException, WordNotPresentException {
        if(!dict.containsKey(word.charAt(0))){
            throw new LetterNotPresentException("Lettera non presente");
        }
        SortedMap<String, SortedSet<String>> words = dict.get(word.charAt(0));
        if(!words.containsKey(word)){
            throw new WordNotPresentException("Parola non presente");
        }
        SortedSet<String> definizioni = words.get(word);
        definizioni.addAll(meanings);
        words.put(word, definizioni);
        dict.put(word.charAt(0), words);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Character key: dict.keySet()){
            sb.append(key).append(":[");
            SortedMap<String, SortedSet<String>> words = dict.get(key);
            for(String word: words.keySet()){
                sb.append(word).append(": (");
                for(String meaning: words.get(word)){
                    sb.append(meaning).append(';');
                }
                sb.append("), ");
            }
            sb.append("\b\b]\n");
        }
        return sb.toString();
    }
}
