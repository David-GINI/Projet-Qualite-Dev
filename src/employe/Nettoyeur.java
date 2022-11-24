package employe;
import restaurant.EtatProprete;
import restaurant.Restaurant;


public class Nettoyeur extends Employe {
    public Nettoyeur(String nom, int efficacite) {
        super(nom, efficacite);
    }

    public void nettoyer(Restaurant restaurant) {
        int tacheEnCours = 0;
        int dureeTache = 60;
        while (tacheEnCours != dureeTache) {
            this.occupe =  true;
            System.out.println(this.nom + "est en train de nettoyer la salle");
            tacheEnCours += efficacite;
        }
        this.energie -= 20;
        restaurant.etatProprete = EtatProprete.PROPRE;
        System.out.println(this.nom + "a fini de nettoyer la salle");
        this.occupe = false;

        }
}
