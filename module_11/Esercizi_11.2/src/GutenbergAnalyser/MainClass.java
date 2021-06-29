package GutenbergAnalyser;

import CovidSimulator.Files.FileReaderManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static void main(String[] args) {
        Rank r = new Rank();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(File entry: Objects.requireNonNull((new File("/files/books")).listFiles())){
            if(!entry.isDirectory()){
                try {
                    FileReaderManager manager = new FileReaderManager(entry.getAbsolutePath());
                    Book book = new Book();
                    pool.submit(new BookParser(book, manager));
                    pool.submit(new BookParser(book, manager));
                    pool.submit(new BookParser(book, manager));
                    r.addBook(book);
                }catch(IOException ex){
                    //ignored
                }
            }
        }
        while(!pool.isTerminated()) {
            System.out.println("Current Rank: " + r.getRank());
            Thread.yield();
        }
        System.out.println("Final Rank: " + r.getRank());
    }
}
