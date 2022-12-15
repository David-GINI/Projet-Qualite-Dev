package client;

import restaurant.Restaurant;
import restaurant.Etat;
/**
 * Class critique
 * @author G6
 */
public class Crtique extends Client implements Critique {
    private double note;
    /**
     * Constructeur de la classe critique, reprend le constructeur de Client
     * @param resto
     * @param pourboire
     * @param nom
     * @param tempsAttenteMax
     * @param status
     * @param preferences
     * @param surPlace
     */
    public Crtique(Restaurant resto, double pourboire, String nom, int tempsAttenteMax, Status status, Preferences preferences, boolean surPlace) {
        super(resto, pourboire, nom, tempsAttenteMax, status, preferences, surPlace);
        this.note = 5;
    }

    @Override
    /**
     * Override de la fonction noter dans l'interface Critique
     * @param restaurant
     */
    public void noter(Restaurant restaurant) {

        if(restaurant.etatProprete == Etat.SALE){
            this.note -=2;
        }
        if(restaurant.etatProprete == Etat.CORRECT){
            this.note -=0.5;
        }
        for(int i =0; i<restaurant.listeTables.size(); ++i){
            if(restaurant.listeTables.get(i).etat == Etat.SALE){
                this.note -=0.5;
            }
            if(restaurant.listeTables.get(i).etat == Etat.CORRECT){
                this.note -=0.1;
            }
        }

        if(this.attenteActuelle == this.tempsAttenteMax){
            this.note = 0;
        }
        if(this.note <0){
            this.note = 0;
        }
        restaurant.listeNotes.add(this.note);
        restaurant.actualiserNote();
    }
}
