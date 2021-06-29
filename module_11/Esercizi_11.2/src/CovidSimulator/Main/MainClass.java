package CovidSimulator.Main;

import CovidSimulator.Files.FileReaderManager;
import CovidSimulator.Files.FileWriterManager;
import CovidSimulator.Simulator.ParseFile;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        FileReaderManager readerNord = new FileReaderManager("files/nord.txt");
        FileReaderManager readerCentro = new FileReaderManager("files/centro.txt");
        FileReaderManager readerSud = new FileReaderManager("files/sud.txt");
        FileWriterManager output = new FileWriterManager("files/output.txt");
        Semaphore semaphore = new Semaphore(0);
        pool.submit(new ParseFile(readerNord, output, semaphore));
        pool.submit(new ParseFile(readerNord, output, semaphore));
        pool.submit(new ParseFile(readerNord, output, semaphore));
        pool.submit(new ParseFile(readerCentro, output, semaphore));
        pool.submit(new ParseFile(readerCentro, output, semaphore));
        pool.submit(new ParseFile(readerCentro, output, semaphore));
        pool.submit(new ParseFile(readerSud, output, semaphore));
        pool.submit(new ParseFile(readerSud, output, semaphore));
        pool.submit(new ParseFile(readerSud, output, semaphore));
        semaphore.acquire(9);
        readerNord.close();
        readerCentro.close();
        readerSud.close();
        output.close();
    }
}
