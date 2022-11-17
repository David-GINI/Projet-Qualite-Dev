package commande;

import java.util.ArrayList;

public class Commande {
    public double prix; //Prix de la commande
    public Viandes viande; //Type de viande
    public ArrayList<Condiments> condiments = new ArrayList<Condiments>(); //Liste des condiments
    public Sauces sauce; //Sauce du burger
    public boolean surPlace; //true = sur place, false = à emporter
    public int temps; //Unité arbitraire de temps que prendra la commande
    public int progress; //Progres de la commande
    public boolean isDone; //Indique si la commande est prete

    public Commande(Viandes viande, ArrayList<Condiments> condiments, Sauces sauce, boolean surPlace, int temps) {

        double prix = 0;
        switch (viande) {
            case BOEUF:
            case BOEUF_CASHER:
            case BOEUF_HALAL: {
                prix = 5.80;
            }
            case POULET:
                prix = 5.20;
            case POISSON:
                prix = 6.15;
            case VEGETARIEN:
                prix = 6.00;
            default:
                System.out.println("Paramètre viande invalide.");
                break;
        }

        this.prix = prix;
        this.viande = viande;
        this.condiments = condiments;
        this.sauce = sauce;
        this.surPlace = surPlace;
        this.temps = temps;
        this.progress = 0;
        this.isDone = false;
    }
}
