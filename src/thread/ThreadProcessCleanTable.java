package thread;

import employe.Nettoyeur;
import restaurant.Restaurant;
import restaurant.Table;
/**
 * Thread qui nettoie une table
 * @author G6
 */
public class ThreadProcessCleanTable implements  Runnable{
    private final Nettoyeur NETTOYEUR;
    private final Restaurant RESTO;
/**
     * Constructeur du Thread qui clean une table
     * @param NETTOYEUR
     * @param RESTO
     */
    public ThreadProcessCleanTable(Nettoyeur NETTOYEUR, Restaurant RESTO) {
        this.NETTOYEUR = NETTOYEUR;
        this.RESTO = RESTO;
    }
    @Override
    /**
     * Fonction qui lance le thread
     * Augmente le temps en fonction de l'efficacité de l'employe
     * Nettoie la table lorsque le temps de nettoyage est atteint
     */
    public void run() {

        int temps = 0; //le temps passé
        while(RESTO.ouvert) {
            try {
                Thread.sleep(1000); // On avance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            Table table = this.NETTOYEUR.tableToClean; // La table a nettoyé est la table attribué a l'agent d'entretien précedemment
            temps += NETTOYEUR.efficacite; //On ajoute le temps passé de "efficacité" de l'agent d'entretien
            if(temps >= 50){ // Si la progression atteint 50
                table.nettoyer(); // La table est néttoyé
                System.out.println("la table "+table.numero + " est nettoyée !");
                System.out.println();
                NETTOYEUR.occupe = false; // l'agent d'entretien est disponible
                break;
                }
            }
            }
        }