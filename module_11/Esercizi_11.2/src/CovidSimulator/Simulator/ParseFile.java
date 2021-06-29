package CovidSimulator.Simulator;

import CovidSimulator.Files.FileReaderManager;
import CovidSimulator.Files.FileWriterManager;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class ParseFile implements Runnable{
    private final FileReaderManager reader;
    private final FileWriterManager writer;
    private final Semaphore semaphore;

    public ParseFile(FileReaderManager reader, FileWriterManager writer, Semaphore semaphore){
        this.reader = reader;
        this.writer = writer;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            while (reader.isAvailable()) {
                String[] arr = reader.readLine().split(";");
                User tmp = new User(UUID.fromString(arr[0]), Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),
                        Boolean.getBoolean(arr[3]), Boolean.getBoolean(arr[4]), Boolean.getBoolean(arr[5]), arr[6]);
                if(tmp.hasCovid()){
                    writer.writeLine(tmp.getId().toString());
                }
            }
        }catch(IOException ex){
            //ignored
        }
        semaphore.release();
    }
}
