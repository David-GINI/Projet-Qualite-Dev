package employe;
import restaurant.Restaurant;
import table.Etat;
import table.Table;


public class Nettoyeur extends Employe {
    public Nettoyeur(String nom, int efficacite) {
        super(nom, efficacite);
    }

    public void nettoyerRestaurant(Restaurant restaurant) {
        int tacheEnCours = 0;
        int dureeTache = 60;
        while (tacheEnCours != dureeTache) {
            this.occupe =  true;
            System.out.println(this.nom + "est en train de nettoyer la salle");
            tacheEnCours += efficacite;
        }
        this.energie -= 20;
        restaurant.etatProprete = Etat.PROPRE;
        System.out.println(this.nom + "a fini de nettoyer la salle");
        this.occupe = false;

        }
        public void nettoyerTable(Table table){
            int tacheEnCours = 0;
            int dureeTache = 60;
            while (tacheEnCours != dureeTache) {
                this.occupe =  true;
                System.out.println(this.nom + "est en train de nettoyer la table numéro " + table.numero);
                tacheEnCours += efficacite;
            }
            this.energie -= 20;
            table.etat = Etat.PROPRE;
            System.out.println(this.nom + "a fini de nettoyer la table" + table.numero);
            this.occupe = false;
        }

}
