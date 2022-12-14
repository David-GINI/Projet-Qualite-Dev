package commande;

import java.util.ArrayList;

public class Commande {
    public double prix; //Prix de la commande
    public Viandes viande; //Type de viande
    public ArrayList<Condiments> condiments = new ArrayList<Condiments>(); //Liste des condiments
    public Sauces sauce; //Sauce du burger
    public boolean surPlace; //true = sur place, false = à emporter
    public int temps; //Unité arbitraire de temps que prendra la commande
    public int progress; //Progression de la commande
    public boolean isDone; //Indique si la commande est prete

    public Commande(Viandes viande, ArrayList<Condiments> condiments, Sauces sauce, boolean surPlace) {

        double prix = 0;
        switch (viande) {
            case BOEUF:
            case BOEUF_CASHER:
            case BOEUF_HALAL: {
                prix = 5.80;
                this.temps = 60;
            }
            case POULET:
                prix = 5.20;
                this.temps = 80;
            case POISSON:
                prix = 6.15;
                this.temps = 70;
            case VEGETARIEN:
                prix = 6.00;
                this.temps = 50;
            default:
                System.out.println("Paramètre viande invalide.");
                break;
        }

        this.prix = prix;
        this.viande = viande;
        this.condiments = condiments;
        this.sauce = sauce;
        this.surPlace = surPlace;
        this.progress = 0;
        this.isDone = false;
    }

    public double getPrix() {
        return prix;
    }

    public Viandes getViande() {
        return viande;
    }

    public ArrayList<Condiments> getCondiments() {
        return condiments;
    }

    public Sauces getSauce() {
        return sauce;
    }

    public boolean isSurPlace() {
        return surPlace;
    }

    public int getTemps() {
        return temps;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

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
