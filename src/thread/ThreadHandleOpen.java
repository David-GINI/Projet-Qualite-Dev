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
            System.out.println("----------------------------");
            System.out.println("Il est : "+JOURNEE.heure + " heure");
            System.out.println("----------------------------");
            JOURNEE.heure +=1;
            System.out.println("Il y a " +RESTO.fileDAttente.size() + " personnes en attente !") ;
            System.out.println("Il y a " +RESTO.listeClientsPris.size() + " personnes prises !") ;
            if(JOURNEE.heure >= JOURNEE.heureClose+1){
                RESTO.vider();
                RESTO.ouvert = false;
                Scanner jourSuivant = new Scanner(System.in);
                routineFermeture(RESTO, JOURNEE);
                System.out.println("Voulez vous passer à la journée suivante ?");
                RESTO.nombreCritique = 0;
                String choix = jourSuivant.nextLine();
                if(Objects.equals(choix, "Oui")){
                    RESTO.genereClients(3);

                    routineRecrutement(RESTO, JOURNEE);

                    System.out.println("----------------------------");
                    System.out.println("Il est : "+JOURNEE.heure + " heure");
                    System.out.println("----------------------------");
                    JOURNEE.heure+=1;
                    ThreadTestCritique afficheCritique = new ThreadTestCritique(RESTO);
                    new Thread(afficheCritique).start();
                    ThreadHandleClient handleClient = new ThreadHandleClient(RESTO);
                    new Thread(handleClient).start();
                    ThreadClientWait attenteClient = new ThreadClientWait(RESTO);
                    new Thread(attenteClient).start();
                    ThreadHandleSurPlace handleSurPlace = new ThreadHandleSurPlace(RESTO);
                    new Thread(handleSurPlace).start();
                    ThreadHandleTableRestaurant servirSurPlace = new ThreadHandleTableRestaurant(RESTO);
                    new Thread(servirSurPlace).start();
                    ThreadHandleEtat handleEtat = new ThreadHandleEtat(RESTO);
                    new Thread(handleEtat).start();

                }
                else if (Objects.equals(choix, "Non")) {
                    System.out.println("Merci d'avoir joué !");
                    break;
                }
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ignored){
            }


        }
    }
}
