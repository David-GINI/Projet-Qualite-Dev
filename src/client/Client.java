package client;

import commande.Commande;
import commande.Condiments;
import commande.Sauces;
import commande.Viandes;
import thread.ThreadAttendre;
import restaurant.Restaurant;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class qui gère un client
 * @author JOLY GINI MOUMANE
 */
public   class Client{
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

    /**
     * Constructeur de la classe Client
     * @param resto
     * @param pourboire
     * @param nom
     * @param tempsAttenteMax
     * @param status
     * @param PREFERENCES
     * @param surPlace
     */
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

    
    /** 
     * Renvoie l'attente actuelle du client
     * @return int
     */
    public int getAttenteActuelle() {
        return attenteActuelle;
    }

    
    /** 
     * Renvoie le temps max que le client peut attendre
     * @return int
     */
    public int getTempsAttenteMax() {
        return tempsAttenteMax;
    }

    
    /** 
     * Setter de l'attente actuelle du client
     * @param attenteActuelle
     */
    public void setAttenteActuelle(int attenteActuelle) {
        this.attenteActuelle = attenteActuelle;
    }

    
    /** 
     * Fonction pour créer la commande du client
     * Chaque partie de la commande est aléatoire et choisis en fonction de la préférence du client
     */
    public void passerCommande() { // Fonction qui randomize la création de la commande du client
        // On s'occupe de la viande
        Viandes viandeCommande;
        Random randomCommande = new Random(); // nombre aléatoire pour choisir les ingredients de la commande aléatoirement
        int nombreAleatoireViande = randomCommande.nextInt(100); // Nombre aléatoire entre 0 et 100
        if(this.PREFERENCES == Preferences.VEGETARIEN){
            viandeCommande = Viandes.VEGETARIEN; // On lui attribut la viande végé
        }
        else if(this.PREFERENCES == Preferences.POISSON){
            viandeCommande = Viandes.POISSON; // On lui attribut la viande végé
        }
        else if(this.PREFERENCES == Preferences.HALAL){
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
        else if(this.PREFERENCES == Preferences.CASHER){
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
        else if(this.PREFERENCES == Preferences.LAMBDA){
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
        else{
            System.out.println("Préférences invalide");
            viandeCommande = Viandes.BOEUF;
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

    
    /** 
     * toString de la classe Client
     * @return String
     */
    @Override
    public String toString() {
        return "Client{" +
                "PREFERENCES=" + PREFERENCES +
                ", nom='" + nom + '\'' +
                ", status=" + status +
                ", surPlace=" + surPlace +
                '}';
    }

    
    /** 
     * Renvoie la commande du client
     * @return Commande
     */
    public Commande getCommande() {
        return commande;
    }

    
    /** 
     * Renvoie la preference du client
     * @return Preferences
     */
    public Preferences getPREFERENCES() {
        return PREFERENCES;
    }

    
    /** 
     * Renvoie le nom du client
     * @return String
     */
    public String getNom() {
        return nom;
    }

    
    /** 
     * Setter du nom du client
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Lance le thread d'attente pour le client
     */
    public void attendre(){
        // On lance le thread d'attente pour le client
        ThreadAttendre t1 = new ThreadAttendre(this, resto);
        new Thread(t1).start();

        }

    
    /** 
     * Renvoie le pourboire du client
     * @return double
     */
    public double getPourboire() {
        return pourboire;
    }

    
    /** 
     * Setter du pourboire du client
     * @param pourboire
     */
    public void setPourboire(double pourboire) {
        this.pourboire = pourboire;
    }

    
    /** 
     * Renvoie true si le client est pris
     * @return boolean
     */
    public boolean isEstPris() {
        return estPris;
    }

    
    /** 
     * Setter du "si le client est pris"
     * @param estPris
     */
    public void setEstPris(boolean estPris) {
        this.estPris = estPris;
    }

    
    /** 
     * Renvoie le statue du client
     * @return Status
     */
    public Status getStatus() {
        return status;
    }

    
    /** 
     * Setter du status du client
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    
    /** 
     * Renvoie true si le client est parti ou non
     * @return boolean
     */
    public boolean isParti() {
        return parti;
    }

    
    /** 
     * setter de "si le client est parti"
     * @param parti
     */
    public void setParti(boolean parti) {
        this.parti = parti;
    }

    
    /** 
     * Renvoie true si le client mange sur place
     * @return boolean
     */
    public boolean isSurPlace() {
        return surPlace;
    }

    
    /** 
     * setter de "si le client mange surplace"
     * @param surPlace
     */
    public void setSurPlace(boolean surPlace) {
        this.surPlace = surPlace;
    }
}
