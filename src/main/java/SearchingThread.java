import java.io.File;

public class SearchingThread implements Runnable {
    private Manager manager;
    private File[] files;
    
    public SearchingThread(File[] files, Manager manager) {
        this.manager = manager;
        this.files = files;
    }
    
    @Override
    public void run() {
        manager.searching(files);
    }
}