package client;

import commande.Commande;
import commande.Condiments;
import commande.Sauces;
import commande.Viandes;

import java.util.ArrayList;
import java.util.Random;

public   class Client {
    private Commande commande; //Commande du client
    private double pourboire;
    private final Preferences preferences;
    private String nom;
    private final int temp_attente_max; // Si le temps est dépassé il quitte le restaurant
    private int attente_actuel = 0;
    private Status status; //client.Client habitué ou non si oui il aura toujours la meme commande
    private boolean parti = false;
    public boolean surPlace; //true = sur place, false = à emporter


    public Client(double pourboire, String nom, int temp_attente_max, Status status, Preferences preferences,boolean surPlace) {
        this.status = status;
        this.temp_attente_max = temp_attente_max;
        this.pourboire = pourboire;
        this.preferences = preferences;
        this.nom = nom;
        this.commande = null;
        this.surPlace =surPlace;
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

    public void attendre(){

        if(this.attente_actuel == this.temp_attente_max){
            System.out.println("Je me casse c'est trop long !");
            this.parti = true;
        }
        else if(this.commande.isDone){
            if (this.surPlace){

            }
            else{
                System.out.println("Au revoir, Merci !");
                this.parti = true;
            }
        }
        else{
            this.attente_actuel += 1;
        }

    }
}
