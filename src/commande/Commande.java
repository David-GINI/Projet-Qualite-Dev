package commande;

import java.util.ArrayList;
/**
 * Class Commande qui gère les commandes de chaque client
 * @author JOLY, GINI, MOUMANE
 */
public class Commande {
    public double prix; //Prix de la commande
    public Viandes viande; //Type de viande
    public ArrayList<Condiments> condiments = new ArrayList<Condiments>(); //Liste des condiments
    public Sauces sauce; //Sauce du burger
    public boolean surPlace; //true = sur place, false = à emporter
    public int temps; //Unité arbitraire de temps que prendra la commande
    public int progress; //Progression de la commande
    public boolean isDone; //Indique si la commande est prete

    /**
     * Constructeur de la classe Commande
     * @param viande
     * @param condiments
     * @param sauce
     * @param surPlace
     */
    public Commande(Viandes viande, ArrayList<Condiments> condiments, Sauces sauce, boolean surPlace) {

        double prix = 0;
        if(viande == Viandes.BOEUF || viande == Viandes.BOEUF_CASHER || viande == Viandes.BOEUF_HALAL){
            prix = 5.80;
            this.temps = 60;
        }
        else if(viande == Viandes.POULET) {
            prix = 5.20;
            this.temps = 80;
        }
        else if(viande == Viandes.POISSON) {
            prix = 6.15;
            this.temps = 70;
        }
        else if(viande == Viandes.VEGETARIEN) {
            prix = 6.00;
            this.temps = 50;
        }
        else {
            System.out.println("Paramètre viande invalide.");
        }
        this.prix = prix;
        this.viande = viande;
        this.condiments = condiments;
        this.sauce = sauce;
        this.surPlace = surPlace;
        this.progress = 0;
        this.isDone = false;
    }

    
    /** 
     * Renvoie le prix de chaque commande
     * @return double
     */
    public double getPrix() {
        return prix;
    }

    
    /** 
     * Renvoie la viande présente dans la commande
     * @return Viandes
     */
    public Viandes getViande() {
        return viande;
    }

    
    /** 
     * Renvoie la liste des condiments présents dans la commande
     * @return ArrayList<Condiments>
     */
    public ArrayList<Condiments> getCondiments() {
        return condiments;
    }

    
    /** 
     * Renvoie la sauce présente dans la commande
     * @return Sauces
     */
    public Sauces getSauce() {
        return sauce;
    }

    
    /** 
     * Renvoie true si la commande est sur place
     * @return boolean
     */
    public boolean isSurPlace() {
        return surPlace;
    }

    
    /** 
     * Renvoie le temps de preparation de la commande
     * @return int
     */
    public int getTemps() {
        return temps;
    }

    
    /** 
     * Renvoie la progression actuelle de la commande
     * @return int
     */
    public int getProgress() {
        return progress;
    }

    
    /** 
     * Setter de la progression
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    
    /** 
     * Renvoie true si la commande est prête
     * @return boolean
     */
    public boolean isDone() {
        return isDone;
    }

    
    /** 
     * Setter pour "si la commande est prête"
     * @param done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    
    /** 
     * toString de la class Commande
     * @return String
     */
    @Override
    public String toString() {
        return "Commande{" +
                "prix=" + prix +
                ", viande=" + viande +
                ", condiments=" + condiments +
                ", sauce=" + sauce +
                ", surPlace=" + surPlace +
                ", temps=" + temps +
                ", progress=" + progress +
                ", isDone=" + isDone +
                '}';
    }
}
