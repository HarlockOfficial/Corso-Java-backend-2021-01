import java.util.HashSet;
import java.util.Set;

public class MainClass {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.insertWord("retina", "Rete, spesso elastica, indossata sopra i capelli lunghi per tenerli in posizione");
        dictionary.insertWord("retina", "La rètina è la membrana più interna del bulbo oculare");
        dictionary.insertWord("calcio", "sport di squadra");
        dictionary.insertWord("calcio", "elemento chimico");
        dictionary.insertWord("calcio", "colpo dato col piede");
        dictionary.insertWord("calcio", "comune di 5353 abitanti");
        dictionary.insertWord("calcio", "impugnatura del fucile");
        dictionary.insertWord("circuito", "circuito elettrico");
        dictionary.insertWord("circuito", "circuito chiuso dove si svolgono corse");
        Set<String> meanings = new HashSet<>();
        meanings.add("circuito commerciale");
        try {
            dictionary.addMeanings("circuito", meanings);
        } catch (LetterNotPresentException e) {
            System.out.println("letter c not found");
        } catch (WordNotPresentException e) {
            System.out.println("word circuito not found");
        }
        System.out.println(dictionary);
    }
}
