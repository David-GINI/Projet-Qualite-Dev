package simulation;

import client.Client;
import client.Preferences;
import client.Status;
import employe.Cuisinier;
import employe.Employe;
import employe.Nettoyeur;
import journee.Jour;
import journee.Journee;
import misc.Data;
import restaurant.Etat;
import restaurant.Restaurant;
import Thread.ThreadHandleOpen;

import java.util.ArrayList;
import java.util.Scanner;

public class MainSimu {

    static void manageEmployes(ArrayList<Employe> listeEmp, Restaurant restaurant) {
        Scanner sc= new Scanner(System.in);
        Cuisinier cuisinier;
        Nettoyeur nettoyeur;
        for (int i = 0; i < restaurant.listeEmployes.size(); ++i) {
            System.out.println(listeEmp.get(i).nom + " a un taux d'efficaticé de " + listeEmp.get(i).efficacite + "/20 voulez-vous l'embaucher? (y/n)");
            String reponse = sc.nextLine();
            if (reponse.equals("y")) {
                System.out.println(reponse);
                System.out.println("Souhaitez-vous l'embaucher en tant que (Cuisinier/Nettoyeur)");
                reponse = sc.nextLine();
                if (reponse.equals("Cuisinier")) {
                    System.out.println(listeEmp.get(i).nom + " a rejoint vos rangs en tant que Cuisinier");
                    cuisinier = new Cuisinier(listeEmp.get(i).nom, listeEmp.get(i).efficacite);
                    restaurant.listeCuisiniers.add(cuisinier);
                }
                else {
                    System.out.println(listeEmp.get(i).nom + " a rejoint vos rangs en tant que Nettoyeur");
                    nettoyeur = new Nettoyeur(listeEmp.get(i).nom, listeEmp.get(i).efficacite);
                    restaurant.listeNettoyeurs.add(nettoyeur);
                }
            }
            else {
                System.out.println(reponse);
                System.out.println("Très bien, employé suivant!");
            }
        }
    }
    public static void main(String[] args) {
        Data donnees = new Data();
        String answer_user = "";
        Restaurant NotreRestaurant = new Restaurant(Etat.PROPRE, 5000);
        Journee NotreSimu = new Journee(1000, Jour.LUNDI,8,12,8);

        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez vous commencer ?");
        answer_user = scan.nextLine();
        NotreRestaurant.genereClients(10);
        System.out.println(NotreRestaurant.listeClients);
        NotreRestaurant.genereEmployes(10);
        System.out.println(NotreRestaurant.listeEmployes);
        ArrayList<Employe> listeEmp = NotreRestaurant.listeEmployes;
        manageEmployes(listeEmp, NotreRestaurant);
        System.out.println(NotreRestaurant.listeCuisiniers);
        System.out.println(NotreRestaurant.listeNettoyeurs);
        ThreadHandleOpen t1 = new ThreadHandleOpen(NotreSimu, NotreRestaurant);
        new Thread(t1).start();


        }
    }
