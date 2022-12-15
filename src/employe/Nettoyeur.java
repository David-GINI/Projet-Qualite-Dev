package employe;
import restaurant.Restaurant;
import restaurant.Etat;
import restaurant.Table;

/**
 * Class Nettoyeur qui utilise Employe
 * @author G6
 */
public class Nettoyeur extends Employe {
    public Table tableToClean;
    public int tablesNettoyees;

    /**
     * Constructeur de la class Nettoyeur : reprend le constructeur de la Employe
     * @param nom
     * @param efficacite
     */
    public Nettoyeur(String nom, int efficacite) {
        super(nom, efficacite);
        this.tablesNettoyees = 0;
    }

    /**
     * Setter de l'attribut tablesNettoyees
     * @param tablesNettoyees
     */
    public void setTablesNettoyees(int tablesNettoyees) {
        this.tablesNettoyees = tablesNettoyees;
    }

    /**
     * Fonction qui change l'état de propreté du restaurant
     * @param restaurant
     */
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
        
        /** 
         * Fonction qui change l'état de propreté de la table en paramètre
         * @param table
         */
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
