package Library;

import java.util.*;

public class LibraryV2 {
    private final SortedMap<Author, SortedSet<Book>> authors;
    public LibraryV2(){
        authors = new TreeMap<>();
    }

    public void insertBook(Author a, Book b) {
        SortedSet<Book> bookSet = new TreeSet<>();
        Author auth = a;
        if(authors.containsKey(a)) {
            //null value is impossible here
            auth = getAuthor(a);
            bookSet = authors.get(a);
        }
        bookSet.add(b);
        authors.put(auth, bookSet);
    }

    private Author getAuthor(Author a) {
        for(Author auth: authors.keySet()){
            if(a.equals(auth)){
                return auth;
            }
        }
        return null;
    }

    public Set<Book> getBooks(Author a) throws AuthorNotFoundException{
        if(!authors.containsKey(a)){
            throw new AuthorNotFoundException();
        }
        return authors.get(a);
    }

    public Book findBookByCode(String code){
        for(SortedSet<Book> bookList: authors.values()){
            for(Book book: bookList){
                if(code.equals(book.getId())){
                    return book;
                }
            }
        }
        return null;
    }

    public Book findBookByTitle(String bookTitle){
        for(SortedSet<Book> bookList: authors.values()){
            for(Book book: bookList){
                if(bookTitle.equals(book.getTitle())){
                    return book;
                }
            }
        }
        return null;
    }

    public void removeBook(Book b) {
        for(SortedSet<Book> bookSet: authors.values()){
            if(bookSet.contains(b)){
                bookSet.remove(b);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Author a: authors.keySet()){
            sb.append(a.toString()).append(":\n");
            for(Book book: authors.get(a)){
                sb.append('\t').append(book.toString()).append('\n');
            }
        }
        return sb.toString();
    }
}
