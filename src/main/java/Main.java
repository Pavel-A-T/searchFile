import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\");
        String reg = ".*\\.jpg$";
        Manager search = new Manager(file, reg);
    }
}