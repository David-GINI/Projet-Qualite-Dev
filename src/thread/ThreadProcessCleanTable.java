package thread;

import employe.Nettoyeur;
import restaurant.Restaurant;
import restaurant.Table;

public class ThreadProcessCleanTable implements  Runnable{
    private final Nettoyeur NETTOYEUR;
    private final Restaurant RESTO;

    public ThreadProcessCleanTable(Nettoyeur NETTOYEUR, Restaurant RESTO) {
        this.NETTOYEUR = NETTOYEUR;
        this.RESTO = RESTO;
    }
    @Override
    public void run() {
        int temps = 0; //le temps passé
        while(true) {
            try {
                Thread.sleep(1000); // On avance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            Table table = this.NETTOYEUR.tableToClean; // La table a nettoyé est la table attribué a l'agent d'entretien précedemment
            temps += NETTOYEUR.efficacite; //On ajoute le temps passé de "efficacité" de l'agent d'entretien
            if(temps >= 50){ // Si la progression atteint 50
                table.nettoyer(); // La table est néttoyé
                System.out.println("la table "+table.numero + " est nettoyée !");
                NETTOYEUR.occupe = false; // l'agent d'entretien est disponible
                break;
                }
            }
            }
        }