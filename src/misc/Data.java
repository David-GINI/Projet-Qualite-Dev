package misc;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Class Data
 */
public class Data {
    public ArrayList<String> listePrenoms = new ArrayList<String>();

    /**
     * Constructeur de la class Data qui prend le fichier de prenoms
     */
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

    /**
     * Fonction qui renvoie un prenom aléatoire parmis le fichier prenom.csv
     * @return String
     */
    public String getRandomPrenom() {
        String prenom;
        Random random = new Random();
        return listePrenoms.get(random.nextInt(11510));
    }
}
