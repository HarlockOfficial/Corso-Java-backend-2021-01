package Library;

import java.util.Objects;

public class Book implements Comparable<Book>{
    private final String id, title, synopsis;
    public Book(String id, String title, String synopsis) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int compareTo(Book o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title + ": " + synopsis;
    }
}
