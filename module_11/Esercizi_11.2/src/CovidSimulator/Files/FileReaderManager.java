package CovidSimulator.Files;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class FileReaderManager {
    private final BufferedReader f;
    private final File file;
    public FileReaderManager(String filename) throws IOException {
        file = new File(filename);
        if(!FileManager.getInstance().addFile(file)){
            throw new FileAlreadyExistsException("File already added, cannot instantiate a file more than once");
        }
        f = new BufferedReader(new FileReader(file));
    }
    public synchronized String readLine() throws IOException {
        return f.readLine();
    }
    public synchronized void close() throws IOException {
        f.close();
        FileManager.getInstance().removeFiles(file);
    }
    public synchronized boolean isAvailable() throws IOException {
        return f.ready();
    }
}
