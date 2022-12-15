package thread;

import journee.Journee;
import restaurant.Restaurant;

import java.util.Objects;
import java.util.Scanner;

import static simulation.MainSimu.*;
/**
 * Thread qui gère le jeu (heure)
 * @author G6
 */
public class ThreadHandleOpen implements Runnable{
    private final Journee JOURNEE;
    private final Restaurant RESTO;
/**
     * Constructeur du Thread Principal
     * @param JOURNEE
     * @param RESTO
     */
    public ThreadHandleOpen(Journee JOURNEE, Restaurant RESTO) {
        this.JOURNEE = JOURNEE;
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread princiaple
     * gère le temps général du restaurant (heure)
     * ferme le resto quand c'est l'heure de fermer 
     * et propose au client de relancer une journée
     */
    public void run() {
        while (true){
            System.out.println(RESTO.listeClients);
            System.out.println("Il est : "+JOURNEE.heure + " heure");
            JOURNEE.heure +=1;
            System.out.println("Il y a " +RESTO.fileDAttente.size() + " personnes en attente !") ;
            if(JOURNEE.heure >= JOURNEE.heureClose){
                RESTO.vider();
                RESTO.ouvert = false;
                System.out.println("Il est : "+JOURNEE.heure + " heure");
                Scanner jourSuivant = new Scanner(System.in);
                JOURNEE.revenuEgalObjectif(RESTO.revenuParJour);
                routineFermeture(RESTO, JOURNEE);
                System.out.println("Voulez vous passer à la journée suivante ?");
                RESTO.nombreCritique = 0;
                String choix = jourSuivant.nextLine();
                if(Objects.equals(choix, "Oui")){
                    RESTO.genereClients(3);
                    RESTO.genereEmployes(16);
                    System.out.println("Début de la journée. Voici les employés qui ont postulé à Mama's Burgeria:");
                    System.out.println();
                    pickEmployes(RESTO);
                    System.out.println("[RÉCAPITULATIF STAFF]");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    trierCuisiniers(RESTO.listeCuisiniers, "efficacite");
                    afficherCuisiniers(RESTO.listeCuisiniers, false);
                    trierNettoyeurs(RESTO.listeNettoyeurs, "efficacite");
                    afficherNettoyeurs(RESTO.listeNettoyeurs, false);
                    System.out.println("Entrez n'importe quelle touche pour procéder à l'ouverture.");
                    choix = jourSuivant.nextLine();
                    RESTO.revenuParJour = 0;
                    JOURNEE.nextDay();
                    JOURNEE.setHeure(JOURNEE.heureOpen);
                    System.out.println("Bienvenue chez " + RESTO.nom + ", nous sommes " + JOURNEE.jour);
                    Scanner newObjRevenu = new Scanner(System.in);
                    System.out.println("Quel est votre objectif de revenus aujourd'hui patron ?");
                    JOURNEE.setObjectifRevenu(newObjRevenu.nextDouble());
                    RESTO.ouvert = true;
                    ThreadTestCritique afficheCritique = new ThreadTestCritique(RESTO);
                    new Thread(afficheCritique).start();
                    ThreadHandleClient handleClient = new ThreadHandleClient(RESTO);
                    new Thread(handleClient).start();
                    System.out.println("Il est : "+JOURNEE.heure + " heure");

                }
                else if (Objects.equals(choix, "Non")) {
                    System.out.println("Merci d'avoir joué !");
                    break;
                }
            }
            else{
                for(int i = 0; i<RESTO.fileDAttente.size(); ++i){
                    RESTO.fileDAttente.get(i).attendre();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored){
            }


        }
    }
}
