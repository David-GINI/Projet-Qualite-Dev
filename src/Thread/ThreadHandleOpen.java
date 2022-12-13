package Thread;

import journee.Journee;
import restaurant.Restaurant;

import java.util.Objects;
import java.util.Scanner;

public class ThreadHandleOpen implements Runnable{
    private final Journee JOURNEE;
    private final Restaurant RESTO;

    public ThreadHandleOpen(Journee JOURNEE, Restaurant RESTO) {
        this.JOURNEE = JOURNEE;
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Il est : "+JOURNEE.heure + " heure");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored){
            }

            JOURNEE.heure +=1;
            if(JOURNEE.heure == JOURNEE.heure_close){
                Scanner jourSuivant = new Scanner(System.in);

                JOURNEE.revenusEgalObjectif(RESTO.revenuPerDay);
                System.out.println("Mama's Burgeria est fermé ! ");
                System.out.println("Voulez vous passer à la journée suivante ?");
                String choix = jourSuivant.nextLine();
                RESTO.vider();
                RESTO.ouvert = false;
                if(Objects.equals(choix, "Oui")){
                    RESTO.revenuPerDay = 0;
                    JOURNEE.nextDay();
                    JOURNEE.setHeure(JOURNEE.heure_open);
                    System.out.println("Bienvenue chez Mama's Burgeria, nous sommes " + JOURNEE.jour);
                    Scanner newObjRevenu = new Scanner(System.in);
                    System.out.println("Quel est votre objectif de revenus aujourd'hui patron ?");
                    JOURNEE.setObj_revenus(newObjRevenu.nextDouble());
                    RESTO.ouvert = true;
                }
                else if (Objects.equals(choix, "Non")) {
                    System.out.println("Merci d'avoir joué !");

                    break;
                }

            }
        }
    }
}
