package restaurant;
import client.Client;
import client.Preferences;
import client.Status;
import commande.Commande;
import employe.Employe;
import table.Table;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
    public EtatProprete etatProprete;
    public ArrayList<Employe> listeEmployes = new ArrayList<Employe>();
    public ArrayList<Client> listeClients = new ArrayList<Client>();
    public ArrayList<Client> fileDAttente = new ArrayList<Client>();
    public ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
    public ArrayList<Table> listeTables = new ArrayList<Table>();
    public double caisse;

    public Restaurant(EtatProprete etatProprete, float caisse) {
        this.etatProprete = EtatProprete.PROPRE;
        this.caisse = 0;
    }

    public void genereClient() {
        Random randomizer = new Random();

        Commande commande;
        double pourboire = randomizer.nextDouble(0.00,6.99);;
        Preferences preferences;
        String nom = "PLACEHOLDER";
        int attente = randomizer.nextInt(50, 200); //Définition de la patience du client
        boolean surPlace;

        switch (randomizer.nextInt(5)) { //Choix des préférences du client
            case 1 -> preferences = Preferences.CASHER;
            case 2 -> preferences = Preferences.HALAL;
            case 3 -> preferences = Preferences.POISSON;
            case 4 -> preferences = Preferences.VEGETARIEN;
            default -> preferences = Preferences.LAMBDA;
        }

        switch (randomizer.nextInt(2)) { //Est ce que le client mange sur place?
            case 1 -> surPlace = true;
            default -> surPlace = false;
        }

        Client client = new Client(pourboire, nom, attente, Status.NORMAL, preferences, surPlace);
        listeClients.add(client);
    }

}
