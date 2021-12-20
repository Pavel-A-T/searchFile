import java.io.File;
import java.util.ArrayList;

public class Manager implements Runnable {
    private String regularString;
    private File path;
    private ArrayList<File> found = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();
    private File[] files;
    private Thread thr;
    
    public Manager(File root, String reg) {
        this.regularString = reg;
        this.path = root;
        files = path.listFiles();
        thr = new Thread(this);
        thr.start();
    }

    public void searching(File[] files) {
        if (files == null) return;
        for (File file : files) {
            if (file.getName().matches(regularString)) {
                found.add(file);
            }
            if (file.isDirectory()) {
                Thread newThread = new Thread(new SearchingThread(file.listFiles(), this));
                newThread.start();
                threads.add(newThread);
            }
        }
    }

    @Override
    public void run() {
        searching(files);
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (found.size() == 0) {
            System.out.println("Nothing was found!");
        } else {
            System.out.println();
            for (File i : found) {
                System.out.println(i.getAbsoluteFile());
            }
        }
    }
}