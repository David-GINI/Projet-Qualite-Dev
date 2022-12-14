package misc;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Data {
    public ArrayList<String> listePrenoms = new ArrayList<String>();

    public Data() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/misc/Prenoms.csv"));
            for (String line : allLines) {
                listePrenoms.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.listePrenoms = listePrenoms;
    }

    public String getRandomPrenom() {
        String prenom;
        Random random = new Random();
        return listePrenoms.get(random.nextInt(11510));
    }
}
