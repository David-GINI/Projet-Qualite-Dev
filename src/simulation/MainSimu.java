package simulation;

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

    static ArrayList<Employe> queueEmployes(ArrayList<Employe> listeEmployes, Restaurant restaurant, int numRerolls) {

        ArrayList<Employe> choixEmploye = new ArrayList<>();
        int lowerBound;
        int upperBound;

        switch (numRerolls) {
            case 1:
                lowerBound = 4;
                upperBound = 8;
                break;
            case 2:
                lowerBound = 8;
                upperBound = 12;
                break;
            case 3:
                lowerBound = 12;
                upperBound = 16;
                break;
            default:
                lowerBound = 0;
                upperBound = 4;
                break;
        }

        for (int i = lowerBound; i < upperBound; ++i) {
            choixEmploye.add(restaurant.listeEmployes.get(i));
        }
        return choixEmploye;
    }

    static void afficheQueueEmploye(ArrayList<Employe> quatreEmployes) {
        int numEmploye = 0;
        for (int i = 0; i < 4; ++i) {
            numEmploye += 1;
            System.out.println(numEmploye + ". " + quatreEmployes.get(i).nom + " | Efficacité: " + quatreEmployes.get(i).efficacite);
        }

    }


    static void embaucherEmploye(Restaurant restaurant, ArrayList<Employe> employesDispo, int numEmploye) {

        Scanner sc= new Scanner(System.in);
        String response;

        Cuisinier cuisinier;
        Nettoyeur nettoyeur;


        if (employesDispo.get(numEmploye).nom.equals("EMBAUCHÉ")) {
            System.out.println("Saisie invalide");
        }
        else {
            System.out.println("Embaucher " + employesDispo.get(numEmploye).nom + " en tant que Cuisinier ou Nettoyeur? (C/N)");
            response = sc.nextLine();
            if (response.toUpperCase().equals("C")) {
                System.out.println(employesDispo.get(numEmploye).nom + " a rejoint vos rangs en tant que Cuisinier!");
                cuisinier = new Cuisinier(employesDispo.get(numEmploye).nom, employesDispo.get(numEmploye).efficacite);
                restaurant.listeCuisiniers.add(cuisinier);
                employesDispo.get(numEmploye).setNom("EMBAUCHÉ");
            } else {
                System.out.println(employesDispo.get(numEmploye).nom + " a rejoint vos rangs en tant que Nettoyeur!");
                nettoyeur = new Nettoyeur(employesDispo.get(numEmploye).nom, employesDispo.get(numEmploye).efficacite);
                restaurant.listeNettoyeurs.add(nettoyeur);
                employesDispo.get(numEmploye).setNom("EMBAUCHÉ");
            }
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
        }
    }
    static void pickEmployes(Restaurant restaurant) {
        restaurant.listeEmployes.clear();
        restaurant.genereEmployes(16);
        ArrayList<Employe> employesDispo = new ArrayList<>();
        int numRerolls = 0;
        boolean isTermine = false;
        Scanner sc= new Scanner(System.in);
        String response;

        employesDispo = queueEmployes(restaurant.listeEmployes, restaurant, numRerolls);

        while (!isTermine) {

            afficheQueueEmploye(employesDispo);

            System.out.println();
            System.out.println("Le salaire de chacun de vos employés vous sera débité tous les mois.");
            System.out.println("Qui voulez-vous embaucher?");
            if (!employesDispo.get(0).nom.equals("EMBAUCHÉ")) {
                System.out.println("(1) " + employesDispo.get(0).nom);
            }
            if (!employesDispo.get(1).nom.equals("EMBAUCHÉ")) {
                System.out.println("(2) " + employesDispo.get(1).nom);
            }
            if (!employesDispo.get(2).nom.equals("EMBAUCHÉ")) {
                System.out.println("(3) " + employesDispo.get(2).nom);
            }
            if (!employesDispo.get(3).nom.equals("EMBAUCHÉ")) {
                System.out.println("(4) " + employesDispo.get(3).nom);
            }
            System.out.println();
            if (numRerolls < 3) {
                System.out.println("(R) Reroll (" + (3-numRerolls) + "/3)");
            }
            System.out.println("(T) Terminer");

            String reponse = sc.nextLine();
            switch (reponse.toUpperCase()) {

                case "1":
                    embaucherEmploye(restaurant, employesDispo, 0);
                    break;

                case "2":
                    embaucherEmploye(restaurant, employesDispo, 1);
                    break;

                case "3":
                    embaucherEmploye(restaurant, employesDispo, 2);
                    break;

                case "4":
                    embaucherEmploye(restaurant, employesDispo, 3);
                    break;
                case "R":
                    if (numRerolls < 4) {
                        numRerolls += 1;
                        employesDispo.clear();
                        employesDispo = queueEmployes(restaurant.listeEmployes, restaurant, numRerolls);
                    }
                    else {
                        System.out.println("Saisie invalide");
                    }
                    break;
                case "T":
                    isTermine = true;
            }
        }


    }

    static void trierCuisiniers(ArrayList<Cuisinier> listeCuisiniers) {

        for (int i = 1; i < listeCuisiniers.size(); ++i) {
            int j = i;

            while (j > 0 && listeCuisiniers.get(j-1).efficacite < listeCuisiniers.get(j).efficacite) {
                Cuisinier k = listeCuisiniers.get(j);
                listeCuisiniers.set(j, listeCuisiniers.get(j-1));
                listeCuisiniers.set(j-1, k);
                j = j-1;
            }
        }
    }

    static void afficherCuisiniers(ArrayList<Cuisinier> listeCuisiniers) {
        System.out.println("Cuisiniers: ");
        System.out.println();
        int numEmploye = 0;
        for (int i = 0; i < listeCuisiniers.size(); ++i) {
            numEmploye += 1;
            System.out.println(numEmploye + ". " + listeCuisiniers.get(i).nom + " | Efficacité: " + listeCuisiniers.get(i).efficacite + " | Salaire: " + listeCuisiniers.get(i).salaire + "€");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    static void trierNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs) {

        for (int i = 1; i < listeNettoyeurs.size(); ++i) {
            int j = i;

            while (j > 0 && listeNettoyeurs.get(j-1).efficacite < listeNettoyeurs.get(j).efficacite) {
                Nettoyeur k = listeNettoyeurs.get(j);
                listeNettoyeurs.set(j, listeNettoyeurs.get(j-1));
                listeNettoyeurs.set(j-1, k);
                j = j-1;
            }
        }
    }

    static void afficherNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs) {
        System.out.println("Nettoyeurs: ");
        System.out.println();
        int numEmploye = 0;
        for (int i = 0; i < listeNettoyeurs.size(); ++i) {
            numEmploye += 1;
            System.out.println(numEmploye + ". " + listeNettoyeurs.get(i).nom + " | Efficacité: " + listeNettoyeurs.get(i).efficacite + " | Salaire: " + listeNettoyeurs.get(i).salaire + "€");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
    }
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static void main(String[] args) {
        Data donnees = new Data();
        Scanner scan = new Scanner(System.in);

        System.out.println("Vous et deux de vos collègues avez fondé un restaurant à hamburgers.");
        System.out.println("Votre premier ami/e est le cuistot du groupe. Comment s'appelle-t-il/elle?");
        String userAnswer = scan.nextLine();
        Cuisinier cuisinierDepart = new Cuisinier(userAnswer, 12);
        System.out.println("Votre autre ami/e assûre la propreté du local. Comment s'appelle-t-il/elle?");
        userAnswer = scan.nextLine();
        Nettoyeur nettoyeurDepart = new Nettoyeur(donnees.getRandomPrenom(), 12);
        System.out.println("Vous êtes le manager. Choisissez un nom pour le restaurant :");
        userAnswer = scan.nextLine();
        Restaurant restaurant = new Restaurant(userAnswer);
        restaurant.listeCuisiniers.add(cuisinierDepart);
        restaurant.listeNettoyeurs.add(nettoyeurDepart);
        System.out.println(restaurant.nom + " ouvre ses portes!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();

        Journee notreSimu = new Journee(1000, Jour.LUNDI,8,12,8);
        restaurant.genereClients(3);
        restaurant.genereEmployes(16);
        System.out.println("Début de la journée. Voici les employés qui ont postulé à Mama's Burgeria:");
        System.out.println();
        pickEmployes(restaurant);
        System.out.println("[RÉCAPITULATIF STAFF]");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        trierCuisiniers(restaurant.listeCuisiniers);
        afficherCuisiniers(restaurant.listeCuisiniers);
        trierNettoyeurs(restaurant.listeNettoyeurs);
        afficherNettoyeurs(restaurant.listeNettoyeurs);

        System.out.println("Entrez n'importe quelle touche pour procéder à l'ouverture.");
        userAnswer = scan.nextLine();
        ThreadHandleOpen t1 = new ThreadHandleOpen(notreSimu, restaurant);
        new Thread(t1).start();
        }
    }
