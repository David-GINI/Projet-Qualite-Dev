package restaurant;
import client.Client;
import client.Crtique;
import client.Preferences;
import client.Status;
import commande.Commande;
import employe.Cuisinier;
import employe.Employe;
import employe.Nettoyeur;
import misc.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Restaurant {
    public boolean ouvert;
    public boolean complet = false;
    public Etat etatProprete;
    public ArrayList<Employe> listeEmployes = new ArrayList<Employe>();
    public int nombreCritique = 0;
    public ArrayList<Cuisinier> listeCuisiniers = new ArrayList<>();
    public ArrayList<Nettoyeur> listeNettoyeurs = new ArrayList<>();
    public ArrayList<Client> listeClients = new ArrayList<Client>();
    public ArrayList<Client> listeClientsPris = new ArrayList<Client>();
    public ArrayList<Client> fileDAttente = new ArrayList<Client>();
    public ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
    public ArrayList<Table> listeTables = new ArrayList<Table>();
    public List<Double> listeNotes = new ArrayList<Double>();
    public double note;
    public double caisse;
    public double revenuParJour;
    public Data donnees;

    public String nom;

    public Restaurant(String nom) {

        this.nom = nom;
        this.etatProprete = Etat.PROPRE;
        this.caisse = 5000;
        this.donnees = new Data();
    }
    public void actualiserNote(){
        int moyenne = 0;
        for(int i = 0; i<this.listeNotes.size(); ++i){
            moyenne+= this.listeNotes.get(i);
        }
        moyenne = moyenne / this.listeNotes.size();
        this.note = moyenne;
    }
    public void genereClients(int nbClients) {
        Random randomizer = new Random();
        Commande commande;
        double pourboire;
        double pourboireRounded;
        Preferences preferences;
        int attente;
        String nom;
        boolean surPlace;
        Client client;

        for (int i = 0; i < nbClients; ++i) {
            pourboire = randomizer.nextDouble(0.00, 6.99);
            pourboireRounded = Math.round(pourboire * 100.0)/100.0;
            nom = donnees.getRandomPrenom();
            attente = randomizer.nextInt(50, 200); //Définition de la patience du client

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
            int clientOrCritique = randomizer.nextInt(100);
            if(clientOrCritique>=10){
                client = new Client(this, pourboireRounded, nom, attente, Status.NORMAL, preferences, surPlace);
            }
            else{
                client = new Crtique(this, pourboireRounded, nom, attente, Status.NORMAL, preferences, surPlace);
            }
            listeClients.add(client);
            fileDAttente.add(client);
        }
    }

    public void genereEmployes(int nbEmployes) {
        Random randomizer = new Random();
        String nom;
        Employe employe;
        int efficacite;
        for (int i = 0; i < nbEmployes; ++i) {
            nom = donnees.getRandomPrenom();
            efficacite = randomizer.nextInt(1,21);
            employe = new Employe(nom, efficacite);
            listeEmployes.add(employe);
        }
    }

    //Fonctions dépréciées -> Utiliser listeCuisinier et listeNettoyeur a la place

    /*public ArrayList<Cuisinier> getCuisinier(){
        ArrayList<Cuisinier> listeCuisinier = new ArrayList<>();
        for(int i = 0; i<this.listeEmployes.size(); ++i){
            if(listeEmployes.get(i) instanceof Cuisinier){
                listeCuisinier.add((Cuisinier) listeEmployes.get(i));
            }
        }
        return listeCuisinier;//renvoit  la liste des cuisiniers a partir de la liste des gens employés
    }
    public ArrayList<Nettoyeur> getNettoyeur(){
        ArrayList<Nettoyeur> listeNettoyeur = new ArrayList<>();
        for(int i = 0; i<this.listeEmployes.size(); ++i){
            if(listeEmployes.get(i) instanceof Nettoyeur){
                listeNettoyeur.add((Nettoyeur) listeEmployes.get(i));
            }
        }
        return listeNettoyeur; // renvoit  la liste des agents d'entretien a partir de la liste des gens employés
    }*/

    public void ajouterArgent(double argent){
        this.revenuParJour += argent;
        this.caisse += argent;
    }
    public void vider(){
        this.fileDAttente.clear();
        this.listeClientsPris.clear();
    }


    public void salir(){
        etatProprete = this.etatProprete.salir();
    }
    public void nettoyer(){
        etatProprete = this.etatProprete.nettoyer();
    }
}
