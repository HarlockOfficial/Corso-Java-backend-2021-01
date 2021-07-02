package GutenbergAnalyser;

import java.util.*;

public class Rank {
    private final List<Book> books;

    public Rank() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public List<Book> getRank(Book b) {
        double[][] matr = new double[books.size()][books.size()];
        for (int i = 0; i < books.size(); i++) {
            for (int j = 0; j < books.size(); j++) {
                matr[i][j] = getDistance(books.get(i), books.get(j));
            }
        }
        int index = books.indexOf(b);
        List<Book> rank = new ArrayList<>();
        for(int I = 0; I<5; ++I){
            double min = Double.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < matr[index].length; i++) {
                if(!rank.contains(books.get(i)) && matr[index][i]!=0 && matr[index][i]<min){
                    min = matr[index][i];
                    minIndex = i;
                }
            }
            rank.add(books.get(minIndex));
        }
        return rank;
    }

    private double getDistance(Book b1, Book b2){
        long diffB1B2 = 0;
        long[] occurrencesB1 = b1.getLetterCounter(),
                occurrencesB2 = b2.getLetterCounter();
        for (int i = 0; i < occurrencesB1.length; ++i) {
            diffB1B2 += Math.pow(Math.abs(occurrencesB2[i] - occurrencesB1[i]), 2);
        }
        return Math.sqrt(diffB1B2);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
