package Thread;

import employe.Nettoyeur;
import restaurant.Restaurant;
import restaurant.Etat;

import java.util.ArrayList;

public class Thread_Handle_Etat implements Runnable {
    private final Restaurant RESTO;

    public Thread_Handle_Etat(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while (RESTO.ouvert) { // Tant que le resto est ouvert
            ArrayList<Nettoyeur> listeNettoyeur = RESTO.getNettoyeur(); //Liste de tous les agents d'entretien dans tous les employés
            for (Nettoyeur nettoyeur : listeNettoyeur) { // On parcourt cette liste
                for (int j = 0; j < RESTO.listeTables.size(); ++j) { //On parcourt la liste des tables
                    if (RESTO.listeTables.get(j).etat == Etat.SALE || RESTO.listeTables.get(j).etat == Etat.CORRECT) { // Si une table n'est pas propre
                        if (!nettoyeur.occupe) { // Si un agent d'entretien est dispo
                            nettoyeur.occupe = true; // Il devient occupé
                            nettoyeur.tableToClean = RESTO.listeTables.get(j); // On lui affecte la table a nettoyé
                            System.out.println("l'agent d'entretien "+nettoyeur.nom + " va nettoyer la table numéro " + nettoyeur.tableToClean.numero);
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
