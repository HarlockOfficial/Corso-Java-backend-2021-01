package CovidSimulator.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class FileWriterManager {
    private final BufferedWriter f;
    private final File file;
    public FileWriterManager(String filename) throws IOException {
        file = new File(filename);
        if(!FileManager.getInstance().addFile(file)){
            throw new FileAlreadyExistsException("File already added, cannot instantiate a file more than once");
        }
        f = new BufferedWriter(new FileWriter(file));
    }
    public synchronized void writeLine(String text) throws IOException {
        f.write(text+"\n");
    }
    public synchronized void close() throws IOException {
        f.close();
        FileManager.getInstance().removeFiles(file);
    }
}
