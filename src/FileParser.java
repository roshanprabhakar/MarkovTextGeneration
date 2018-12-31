import java.io.*;
import java.util.ArrayList;

public class FileParser {

    public void fixFile(String path) {

        ArrayList<String> lines = loadData(path);
        removeNewLines(lines);
        insertSpaces(lines);
        trimSpaces(lines);

        writeDataToFile("TextSources/" + path, lines);

    }

    private void trimSpaces(ArrayList<String> lines) {
        for (int line = 0; line < lines.size(); line++) {
            String out = lines.get(line).replaceAll("[ ]{2,}", " ");
            lines.set(line, out);
        }
    }

    private void insertSpaces(ArrayList<String> lines) {
        for (int line = 0; line < lines.size(); line++) {
            String out = "";
            for (int i = 0; i < lines.get(line).length() - 1; i++) {
                if (lines.get(line).charAt(i + 1) == '.') {
                    out += lines.get(line).charAt(i) + " . ";
                    i++;
                } else {
                    out += lines.get(line).charAt(i);
                }
            }
            lines.set(line, out);
        }
    }

    private ArrayList<String> loadData(String path) {

        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("TextSources/" + path));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("could not read data from file!");
        }

        return lines;
    }

    private void removeNewLines(ArrayList<String> lines) {

        for (int line = 0; line < lines.size(); line++) {
            String out = "";
            for (int i = 0; i < lines.get(line).length(); i++) {
                out += lines.get(line).charAt(i);
            }

            out += " ";
            lines.set(line, out);
        }
    }

    private void writeDataToFile(String path, ArrayList<String> lines) {

        try {

            PrintWriter out = new PrintWriter(new FileWriter(path));
            for (String line : lines) {
                out.print(line + " ");
            }
            out.close();

        } catch (Exception e) {
            System.out.println("could not write updated contents to file!");
        }
    }
}
