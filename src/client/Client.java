package client;

import commande.Commande;
import commande.Condiments;
import commande.Sauces;
import commande.Viandes;
import Thread.Thread_Attendre1;

import java.util.ArrayList;
import java.util.Random;

public   class  Client extends Thread {
    protected Commande commande; //Commande du client
    protected double pourboire;
    protected boolean estPris;

    protected final Preferences preferences;
    protected String nom;
    protected final int temp_attente_max; // Si le temps est dépassé il quitte le restaurant
    protected int attente_actuel = 0;
    protected Status status; //client.Client habitué ou non si oui il aura toujours la meme commande
    protected boolean parti;
    protected boolean surPlace; //true = sur place, false = à emporter


    public Client(double pourboire, String nom, int temp_attente_max, Status status, Preferences preferences,boolean surPlace) {
        this.status = status;
        this.temp_attente_max = temp_attente_max;
        this.pourboire = pourboire;
        this.preferences = preferences;
        this.nom = nom;
        this.commande = null;
        this.surPlace =surPlace;
        this.parti = false;
    }

    public int getAttente_actuel() {
        return attente_actuel;
    }

    public int getTemp_attente_max() {
        return temp_attente_max;
    }

    public void setAttente_actuel(int attente_actuel) {
        this.attente_actuel = attente_actuel;
    }

    public void Passer_Commander() { // Fonction qui randomize la création de la commande du client
        // On s'occupe de la viande
        Viandes viandeCommande = null;
        Random randomCommande = new Random(); // nombre aléatoire pour choisir les ingredients de la commande aléatoirement
        int nombreAleatoireViande = randomCommande.nextInt(100); // Nombre aléatoire entre 0 et 100
        switch (this.preferences) {
            case VEGETARIEN: {
                viandeCommande = Viandes.VEGETARIEN; // On lui attribut la viande végé
            }
            case HALAL: {
                if (nombreAleatoireViande >= 66) { // 34% de chance que sa viande soit : boeuf halal
                    viandeCommande = Viandes.BOEUF_HALAL;
                }
                else if (nombreAleatoireViande >= 33){ // 33% que sa viande soit : poisson
                    viandeCommande = Viandes.POISSON;
                }
                else{
                    viandeCommande = Viandes.VEGETARIEN; // 33% que sa viande soit végé
                }
            }
            case CASHER: {
                if (nombreAleatoireViande >= 66) { // 34% de chance que sa viande soit : boeuf casher
                    viandeCommande = Viandes.BOEUF_CASHER;
                }
                else if (nombreAleatoireViande >= 33){ // 33% que sa viande soit : poisson
                    viandeCommande = Viandes.POISSON;
                }
                else{
                    viandeCommande = Viandes.VEGETARIEN; // 33% que sa viande soit végé
                }
            }
            case LAMBDA: {
                if (nombreAleatoireViande >= 83) { // 18% de chance que sa viande soit : boeuf
                    viandeCommande = Viandes.BOEUF;
                }
                else if (nombreAleatoireViande >= 67){ // 16% que sa viande soit : poisson
                    viandeCommande = Viandes.POISSON;
                }
                else if (nombreAleatoireViande >= 49){ // 18% que sa viande soit : poulet
                    viandeCommande = Viandes.POULET;
                }
                else if (nombreAleatoireViande >= 32){ // 17% que sa viande soit : boeuf_halal
                    viandeCommande = Viandes.BOEUF_HALAL;
                }
                else if (nombreAleatoireViande >= 16){ // 16% que sa viande soit : boeuf_casher
                    viandeCommande = Viandes.BOEUF_CASHER;
                }
                else{
                    viandeCommande = Viandes.VEGETARIEN; // 16% que sa viande soit végé
                }
            }
            default:
                System.out.println("Paramètre de préférences invalide.");
                break;
        }

        // On s'occupe cette fois des condiments

        ArrayList<Condiments> listeCondiments = new ArrayList<Condiments>();
        for(int i = 0; i < Condiments.values().length; ++i){
            int nombreAleatoireCondiments = randomCommande.nextInt(2);
            if(nombreAleatoireCondiments == 0){
                listeCondiments.add(Condiments.values()[i]);
            }
        }
        // On termine par la sauce
        Sauces sauceCommande = null;
        int nombreAleatoireSauce = randomCommande.nextInt(5); // Nombre aléatoire entre 0 et 5
        if(nombreAleatoireSauce == 0){
            sauceCommande = Sauces.BARBECUE;
        }
        else if(nombreAleatoireSauce == 1){
            sauceCommande = Sauces.MAYONNAISE;
        }
        else if(nombreAleatoireSauce == 2){
            sauceCommande = Sauces.KETCHUP;
        }
        else if(nombreAleatoireSauce == 3){
            sauceCommande = Sauces.MOUTARDE;
        }
        else{
            sauceCommande = Sauces.BIGGY;
        }

        Commande commandeClient = new Commande(viandeCommande,listeCondiments,sauceCommande,this.surPlace);
        this.commande = commandeClient;
    }

    public Commande getCommande() {
        return commande;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void attendre(){
        Thread_Attendre1 t1 = new Thread_Attendre1(this);
        new Thread(t1).start();

        }

    public double getPourboire() {
        return pourboire;
    }

    public void setPourboire(double pourboire) {
        this.pourboire = pourboire;
    }

    public boolean isEstPris() {
        return estPris;
    }

    public void setEstPris(boolean estPris) {
        this.estPris = estPris;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isParti() {
        return parti;
    }

    public void setParti(boolean parti) {
        this.parti = parti;
    }

    public boolean isSurPlace() {
        return surPlace;
    }

    public void setSurPlace(boolean surPlace) {
        this.surPlace = surPlace;
    }
}
