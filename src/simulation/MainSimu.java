package simulation;

import client.Client;
import client.Preferences;
import client.Status;
import journee.Jour;
import journee.Journee;
import restaurant.Etat;
import restaurant.Restaurant;
import Thread.ThreadHandleOpen;

import java.util.Scanner;

public class MainSimu {
    public static void main(String[] args) {
        String answer_user = "";
        Restaurant NotreRestaurant = new Restaurant(Etat.PROPRE, 5000);
        Journee NotreSimu = new Journee(1000, Jour.LUNDI,8,12,8);

        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez vous commencer ?");
        answer_user = scan.next();
        Client c1 = new Client(NotreRestaurant, 2.50,"Lucas", 50, Status.NORMAL, Preferences.LAMBDA, false);
        NotreRestaurant.fileDAttente.add(c1);
        ThreadHandleOpen t1 = new ThreadHandleOpen(NotreSimu, NotreRestaurant);
        new Thread(t1).start();


        }
    }
