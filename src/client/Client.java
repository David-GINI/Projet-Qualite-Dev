package client;

import commande.Commande;
import commande.Viandes;

public   class Client {
    private Commande commande; //Commande du client
    private int pourboire;
    private final Preferences preferences;
    private String nom;
    private final int temp_attente_max; // Si le temps est dépassé il quitte le restaurant
    private int attente_actuel = 0;
    private Status status; //client.Client habitué ou non si oui il aura toujours la meme commande
    private boolean parti = false;
    public boolean surPlace; //true = sur place, false = à emporter


    public Client(int pourboire, String nom, int temp_attente_max, Status status, Preferences preferences,boolean surPlace) {
        this.status = status;
        this.temp_attente_max = temp_attente_max;
        this.pourboire = pourboire;
        this.preferences = preferences;
        this.nom = nom;
        this.commande = null;
        this.surPlace =surPlace;
    }

    public void Passer_Commander(Commande commande) {
        if (this.preferences.equals(Preferences.VEGETARIEN)) {
            if (!commande.viande.equals(Viandes.VEGETARIEN)) {
                System.err.println("Le client n'accepte que de la viande végé");
            } else {
                this.commande = commande;
            }
        } else if (this.preferences.equals(Preferences.HALAL)) {
            if ((!(commande.viande == Viandes.POISSON) && !(commande.viande == Viandes.BOEUF_HALAL) && !(commande.viande == Viandes.VEGETARIEN))) {
                System.err.println("Le client n'accpete que le boeuf halal / poisson / Végé");
            }else {
                this.commande = commande;
            }
        }
        else if (this.preferences.equals(Preferences.CASHER)) {
            if ((!(commande.viande == Viandes.POISSON) && !(commande.viande == Viandes.BOEUF_CASHER) && !(commande.viande == Viandes.VEGETARIEN))) {
                System.err.println("Le client n'accpete que le boeuf halal / poisson / Végé");
            }else {
                this.commande = commande;
            }
        }
        else{
            this.commande = commande;
        }
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
