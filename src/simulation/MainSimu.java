package simulation;

import client.Client;
import client.Preferences;
import client.Status;
import restaurant.Etat;
import restaurant.Restaurant;

import java.util.Scanner;

public class MainSimu {
    public static void main(String[] args) {
        String answer_user = "";
        Restaurant NotreRestaurant = new Restaurant(Etat.PROPRE, 5000);
        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez vous commencer ?");
        answer_user = scan.next();
        Client c1 = new Client(NotreRestaurant, 2.50,"Lucas", 50, Status.NORMAL, Preferences.LAMBDA, false);
       NotreRestaurant.fileDAttente.add(c1);
        while(answer_user != "Non"){
            NotreRestaurant.ouvert = true;
            for(int i = 0; i<NotreRestaurant.fileDAttente.size(); ++i){
                NotreRestaurant.fileDAttente.get(i).attendre();
            }

            System.out.println("Voulez vous passer a la journée suivante ?");
            answer_user = scan.next();

        }
    }
}
