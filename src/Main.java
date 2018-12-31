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
        String input;
        while (true) {
            System.out.println("---------- Enter a word ----------");
            input = scanner.nextLine();
            System.out.println(mt.getRandomFrom(input) + " could possibly follow this entry");
            System.out.println("----------------------------------");
            System.out.println();
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
