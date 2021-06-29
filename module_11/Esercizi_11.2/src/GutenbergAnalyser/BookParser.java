package GutenbergAnalyser;

import CovidSimulator.Files.FileReaderManager;

import java.io.IOException;

public class BookParser implements Runnable {
    private final Book book;
    private final FileReaderManager file;
    public BookParser(Book book, FileReaderManager file){
        this.book = book;
        this.file = file;
    }
    @Override
    public void run() {
        try {
            while (file.isAvailable()) {
                String line = file.readLine();
                for(Character letter: line.toCharArray()){
                    book.incLetter(letter);
                }
            }
        }catch(IOException ex){
            //ignored
        }
    }
}
