package client;

import restaurant.Restaurant;
import restaurant.Etat;

public class Crtique extends Client implements Critique {
    private double note;
    public Crtique(Restaurant resto, double pourboire, String nom, int tempsAttenteMax, Status status, Preferences preferences, boolean surPlace) {
        super(resto, pourboire, nom, tempsAttenteMax, status, preferences, surPlace);
        this.note = 5;
    }

    @Override
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
