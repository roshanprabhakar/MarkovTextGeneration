import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MarkovTable mt = new MarkovTable();
        mt.loadDataFromFile("mobydick.txt");
        mt.loadDataFromFile("aliceinwonderland.utf8");
        mt.loadDataFromFile("Siddarta.txt");

        Scanner scanner = new Scanner(System.in);
        String word;
        while (true) {
            word = scanner.nextLine();
            System.out.println(mt.getRandomFrom(word));
        }
    }
}
