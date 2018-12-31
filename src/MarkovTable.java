import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovTable {

    private HashMap<String, ArrayList<String>> map;
    private String[] forbidden = new String[]{".",",","!","?","-"};

    public MarkovTable() {
        this.map = new HashMap<>();
    }

    public void loadDataFromFile(String filepath) {

        ArrayList<String> wordsInFile = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader("TextSources/" + filepath));

            String fileLine;
            ArrayList<String> lines = new ArrayList<>();

            while ((fileLine = br.readLine()) != null) {
                lines.add(fileLine);
            }

            for (String line : lines) {

                line += " ";
                int indexOfFirstAlphabet = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        indexOfFirstAlphabet = i;
                        break;
                    }
                }

                line = line.substring(indexOfFirstAlphabet);

                int spaceIndex = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        wordsInFile.add(line.substring(spaceIndex, i));
                        spaceIndex = i + 1;
                    }
                }
            }

            for (String forbiddenUse : forbidden) {
                for (String key : map.keySet()) {
                    if (map.get(key).contains(forbiddenUse)) map.get(key).remove(forbiddenUse);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < wordsInFile.size() - 1; i++) {
            if (!map.containsKey(wordsInFile.get(i))) {
                map.put(wordsInFile.get(i), new ArrayList<>());
            }
            map.get(wordsInFile.get(i)).add(wordsInFile.get(i + 1));
        }
    }

    public String getRandomFrom(String word) {
        return map.get(word).get((int)(Math.random() * map.get(word).size()));
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }
}
