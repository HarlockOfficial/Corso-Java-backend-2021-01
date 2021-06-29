package GutenbergAnalyser;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private final List<Book> books;

    public Rank() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public List<Book> getRank() {
        books.sort((b1, b2) -> {
            long diffB1B2 = 0;
            long[] occurrencesB1 = b1.getLetterCounter(),
                    occurrencesB2 = b2.getLetterCounter();
            for (int i = 0; i < occurrencesB1.length; ++i) {
                diffB1B2 += Math.pow(occurrencesB2[i] - occurrencesB1[i], 3);
            }
            double res = Math.pow(diffB1B2, 1. / 3.);
            if (res > 0) {
                return 1;
            }
            if (res < 0) {
                return -1;
            }
            return 0;
        });
        return new ArrayList<>(books);
    }
}
