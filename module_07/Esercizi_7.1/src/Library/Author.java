package Library;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Author implements Comparable<Author> {
    private final String id;
    private final String name;
    private final String surname;

    public Author(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(Author o) {
        int cmp = id.compareTo(o.id);
        if (cmp == 0) {
            cmp = surname.compareTo(o.surname);
            if (cmp == 0) {
                cmp = name.compareTo(o.name);
            }
        }
        return cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
