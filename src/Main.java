import java.io.File;
import java.util.Scanner;

public class Main {

    private static FileParser fp = new FileParser();
    private static MarkovTable mt = new MarkovTable();

    private static File textSources = new File("TextSources");

    public static void main(String[] args) {
        fixFiles();
        loadAllFiles();

        Scanner scanner = new Scanner(System.in);
        String var;
        while (true) {
            var = scanner.nextLine();
            System.out.println(mt.getRandomFrom(var));
        }
    }

    private static void loadAllFiles() {
        for (File file : textSources.listFiles()) {
            mt.loadDataFromFile(file.getName());
        }
    }

    private static void fixFiles() {
        for (File file : textSources.listFiles()) {
            fp.fixFile(file.getName());
        }
    }
}
