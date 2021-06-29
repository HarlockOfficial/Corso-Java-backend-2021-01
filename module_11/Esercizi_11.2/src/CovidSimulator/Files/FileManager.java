package CovidSimulator.Files;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

class FileManager {
    private final Set<File> filesList;
    private final static FileManager fm = new FileManager();
    private FileManager(){
        filesList = new HashSet<>();
    }
    public static FileManager getInstance() {
        return fm;
    }
    public synchronized boolean addFile(File f){
        if(f==null) return false;
        return filesList.add(f);
    }
    public synchronized boolean removeFiles(File f){
        if(f==null) return false;
        return filesList.remove(f);
    }
}
