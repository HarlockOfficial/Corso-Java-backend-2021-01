package Library;

import java.util.Set;

public class MainClass {
    public static void main(String[] args) {
        LibraryV2 library = new LibraryV2();
        Book b = new Book(
                "123456",
                "Il signore degli anelli",
                "Il Signore degli Anelli (titolo originale in inglese: " +
                        "The Lord of the Rings) è un romanzo high fantasy epico scritto da J. R. R. Tolkien" +
                        " e ambientato alla fine della Terza Era dell'immaginaria Terra di Mezzo"
        );
        Author a = new Author(
                "abcdef",
                "John Ronald Reuel",
                "Tolkien"
        );
        library.insertBook(a, b);
        Book b2 = new Book(
                "111111",
                "Lo Hobbit",
                "Lo Hobbit o la riconquista del tesoro (titolo originale: The Hobbit, sottotitolato" +
                        " There and Back Again, ossia \"Andata e ritorno\")," +
                        " noto anche semplicemente come Lo Hobbit, è un romanzo fantasy scritto da J. R. R. Tolkien."
        );
        library.insertBook(a, b2);
        Set<Book> books = null;
        try {
            books = library.getBooks(a);
        } catch (AuthorNotFoundException e) {
            System.out.println("My bad");
        }
        System.out.println(books != null);
        assert books != null;
        System.out.println((long) books.size() == 2);
        Book b3 = new Book(
                "119929",
                "1984",
                "1984 (Nineteen Eighty-Four) è uno dei più celebri romanzi di George Orwell, " +
                        "pubblicato nel 1949 ma iniziato a scrivere nel" +
                        " 1948 (anno da cui deriva il titolo, ottenuto appunto dall'inversione delle ultime due cifre)."
        );
        Author a2 = new Author(
                "aaabbb",
                "George",
                "Orwell"
        );
        library.removeBook(b);
        try {
            books = library.getBooks(a);
        } catch (AuthorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println((long) books.size() == 1);
        library.insertBook(a2, b3);
        try {
            books = library.getBooks(a2);
        } catch (AuthorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println((long) books.size() == 1);
        Book b4 = new Book(
                "454545",
                "La fattoria degli animali",
                "La fattoria degli animali è una novella allegorica di George Orwell pubblicata " +
                        "per la prima volta il 17 agosto 1945. Secondo Orwell, il libro riflette sugli " +
                        "eventi che portarono alla Rivoluzione russa e successivamente all'era staliniana " +
                        "dell'Unione sovietica"
        );
        library.insertBook(a2, b4);
        System.out.println(library);
    }
}
