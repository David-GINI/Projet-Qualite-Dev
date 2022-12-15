package thread;

import employe.Nettoyeur;
import restaurant.Restaurant;
import restaurant.Etat;

import java.util.ArrayList;
/**
 * Thread qui gère la gestion de la propreté 
 * @author G6
 */
public class ThreadHandleEtat implements Runnable {
    private final Restaurant RESTO;
/**
     * Constructeur du Thread Handle Etat
     * @param RESTO
     */
    public ThreadHandleEtat(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * Si un nettoyeur est dispo et qu'une table ou le restaurant n'est pas propre alors il lance le nettoyage (ThreadProcessCleanRestaurant ou Table)
     */
    public void run() {
        while (RESTO.ouvert) { // Tant que le resto est ouvert
            int nombreTablesSales = 0;
            int nombreTablesCorrectes = 0;
            ArrayList<Nettoyeur> listeNettoyeur = RESTO.listeNettoyeurs; //Liste de tous les agents d'entretien dans tous les employés
            for(int i = 0; i<RESTO.listeTables.size(); ++i){
                if(RESTO.listeTables.get(i).etat == Etat.SALE){
                    nombreTablesSales +=1;
                }
                else if(RESTO.listeTables.get(i).etat == Etat.CORRECT){
                    nombreTablesCorrectes +=1;
                }
            }
            if(nombreTablesSales >= RESTO.listeTables.size()/2){
                RESTO.etatProprete = Etat.SALE;
            }
            if(nombreTablesCorrectes >= RESTO.listeTables.size()/2){
                RESTO.etatProprete = Etat.CORRECT;
            }
            for (Nettoyeur nettoyeur : listeNettoyeur) { // On parcourt cette liste
                for (int j = 0; j < RESTO.listeTables.size(); ++j) { //On parcourt la liste des tables
                    if (RESTO.listeTables.get(j).etat == Etat.SALE || RESTO.listeTables.get(j).etat == Etat.CORRECT) { // Si une table n'est pas propre
                        if (!nettoyeur.occupe) { // Si un agent d'entretien est dispo
                            nettoyeur.occupe = true; // Il devient occupé
                            nettoyeur.tablesNettoyees += 1;
                            nettoyeur.tableToClean = RESTO.listeTables.get(j); // On lui affecte la table a nettoyé
                            System.out.println("l'agent d'entretien "+nettoyeur.nom + " va nettoyer la table numéro " + nettoyeur.tableToClean.numero);
                            System.out.println();
                            ThreadProcessCleanTable t1 = new ThreadProcessCleanTable(nettoyeur, RESTO); // On lance le thread de nettoyage de la table
                            new Thread(t1).start();
                        }
                    }
                }

                if (RESTO.etatProprete == Etat.CORRECT || RESTO.etatProprete == Etat.SALE) { //Si le resto n'est pas propre
                    if (!nettoyeur.occupe) { // Si un agent d'entretien est disponible
                        nettoyeur.occupe = true; // Il devient occupé
                        System.out.println("l'agent d'entretien "+nettoyeur.nom + " va nettoyer le restaurant");
                        ThreadProcessCleanRestaurant t1 = new ThreadProcessCleanRestaurant(nettoyeur, RESTO); //On lance le thread de nettoyage du restaurant
                        new Thread(t1).start();
                    }
                }
            }
        }
    }
}
