package client;

import commande.Commande;
import commande.Condiments;
import commande.Sauces;
import commande.Viandes;
import Thread.ThreadAttendre;
import restaurant.Restaurant;

import java.util.ArrayList;
import java.util.Random;

public   class Client extends Thread {
    public final int  TEMPS_MANGER = 50;
    protected Restaurant resto;
    protected boolean attend = false;
    protected Commande commande; //Commande du client
    protected double pourboire;
    protected boolean estPris;

    protected final Preferences PREFERENCES;
    protected String nom;
    protected final int tempsAttenteMax; // Si le temps est dépassé il quitte le restaurant
    protected int attenteActuelle = 0;
    protected Status status; //client.Client habitué ou non si oui il aura toujours la meme commande
    protected boolean parti;
    protected boolean surPlace; //true = sur place, false = à emporter


    public Client(Restaurant resto, double pourboire, String nom, int tempsAttenteMax, Status status, Preferences PREFERENCES, boolean surPlace) {
        this.resto = resto;
        this.status = status;
        this.tempsAttenteMax = tempsAttenteMax;
        this.pourboire = pourboire;
        this.PREFERENCES = PREFERENCES;
        this.nom = nom;
        this.commande = null;
        this.surPlace =surPlace;
        this.parti = false;
    }

    public int getAttenteActuelle() {
        return attenteActuelle;
    }

    public int getTempsAttenteMax() {
        return tempsAttenteMax;
    }

    public void setAttenteActuelle(int attenteActuelle) {
        this.attenteActuelle = attenteActuelle;
    }

    public void passerCommande() { // Fonction qui randomize la création de la commande du client
        // On s'occupe de la viande
        Viandes viandeCommande = null;
        Random randomCommande = new Random(); // nombre aléatoire pour choisir les ingredients de la commande aléatoirement
        int nombreAleatoireViande = randomCommande.nextInt(100); // Nombre aléatoire entre 0 et 100
        switch (this.PREFERENCES) {
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

        Commande commandeClient = new Commande(viandeCommande, listeCondiments, sauceCommande, this.surPlace);
        this.commande = commandeClient;
    }

    public Commande getCommande() {
        return commande;
    }

    public Preferences getPREFERENCES() {
        return PREFERENCES;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void attendre(){
        // On lance le thread d'attente pour le client
        ThreadAttendre t1 = new ThreadAttendre(this, resto);
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
