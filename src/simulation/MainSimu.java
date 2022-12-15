package simulation;

import employe.Cuisinier;
import employe.Employe;
import employe.Nettoyeur;
import journee.Jour;
import journee.Journee;
import misc.Data;
import restaurant.Restaurant;
import thread.ThreadHandleClient;
import thread.ThreadHandleOpen;
import thread.ThreadHandleSurPlace;
import thread.ThreadTestCritique;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Simulation principale du jeu
 * @author G6
 */
public class MainSimu {

    
    /** 
     * Crée une liste de 4 Employe en fonction du nombre de rerolls. Liste crée à partir de la listeEmployes du restaurant.
     * @param listeEmployes
     * @param restaurant
     * @param numRerolls
     * @return ArrayList<Employe>
     */
    public static ArrayList<Employe> queueEmployes(ArrayList<Employe> listeEmployes, Restaurant restaurant, int numRerolls) {

        ArrayList<Employe> choixEmploye = new ArrayList<>();
        //Déclaration des variables qui vont définir les bornes de la boucle
        int lowerBound;
        int upperBound;

        switch (numRerolls) { //On affecte ces variables à des valeurs différentes en fonction du nombre de rerolls
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

        for (int i = lowerBound; i < upperBound; ++i) { //On peut maintenant ajouter quatre employés à la liste pour les préparer à l'affichage
            choixEmploye.add(restaurant.listeEmployes.get(i));
        }
        return choixEmploye; //On renvoie le tableau d'Employe
    }

    
    /** 
     * Affiche les 4 employés dispo dans 1 Roll pour le recrutement 
     * @param quatreEmployes
     */
    public static void afficheQueueEmploye(ArrayList<Employe> quatreEmployes) {
        int numEmploye = 0;
        for (int i = 0; i < 4; ++i) { //Parcours de la liste
            numEmploye += 1;
            System.out.println(numEmploye + ". " + quatreEmployes.get(i).nom + " | Efficacité: " + quatreEmployes.get(i).efficacite); //Formatage de l'affichage de chaque Employe
        }

    }


    
    /** 
     * Fonction qui gère le recrutement d'un employé en fonction de son indice dans la queueEmploye
     * @param restaurant
     * @param employesDispo
     * @param numEmploye
     */
    public static void embaucherEmploye(Restaurant restaurant, ArrayList<Employe> employesDispo, int numEmploye) {
        //Déclaration de Scanner pour saisie utilisateur
        Scanner sc= new Scanner(System.in);
        String response;

        Cuisinier cuisinier;
        Nettoyeur nettoyeur;


        if (employesDispo.get(numEmploye).nom.equals("EMBAUCHÉ")) { //On ne peut pas embaucher un employé que l'on a déjà recruté
            System.out.println("Saisie invalide");
        }
        else {
            System.out.println("Embaucher " + employesDispo.get(numEmploye).nom + " en tant que Cuisinier ou Nettoyeur? (C/N)");    //L'utilisateur choisit la fonction de cet employé
            response = sc.nextLine();
            if (response.toUpperCase().equals("C")) {                                                                               //Si Cuisinier, on crée un objet Cuisinier avec les mêmes attributs que cet employé
                System.out.println(employesDispo.get(numEmploye).nom + " a rejoint vos rangs en tant que Cuisinier!");
                cuisinier = new Cuisinier(employesDispo.get(numEmploye).nom, employesDispo.get(numEmploye).efficacite);
                restaurant.listeCuisiniers.add(cuisinier);                                                                          //Puis on l'ajoute à la listeCuisiniers de Restaurant et on change son nom parmi les employesDispo
                employesDispo.get(numEmploye).setNom("EMBAUCHÉ");
            } else {                                                                                                                //Si Nettoyeur, on crée un objet Nettoyeur avec les mêmes attributs que cet employé
                System.out.println(employesDispo.get(numEmploye).nom + " a rejoint vos rangs en tant que Nettoyeur!");
                nettoyeur = new Nettoyeur(employesDispo.get(numEmploye).nom, employesDispo.get(numEmploye).efficacite);
                restaurant.listeNettoyeurs.add(nettoyeur);                                                                          //Puis on l'ajoute à la listeNettoyeurs de Restaurant et on change son nom parmi les employesDispo
                employesDispo.get(numEmploye).setNom("EMBAUCHÉ");
            }
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
        }
    }
    
    /** 
     * Fonction qui encadre le processus de recrutement en début de chaque journée. (Affichage des Employes, embaucher, reroll, terminer)
     * @param restaurant
     */
    public static void pickEmployes(Restaurant restaurant) {
        restaurant.listeEmployes.clear();           //On vide la liste d'Employe précédente pour en générer 16 nouveaux
        restaurant.genereEmployes(16);

        ArrayList<Employe> employesDispo = new ArrayList<>(); //Cette liste va contenir quatre Employe que l'on aura le choix d'embaucher
        int numRerolls = 0; //Indique le nombre de Rerolls qu'à effectué l'utilisateur (Max 3)
        boolean isTermine = false;  // true si l'utilisateur a termine la phase de recrutement

        Scanner sc= new Scanner(System.in);
        String response;

        employesDispo = queueEmployes(restaurant.listeEmployes, restaurant, numRerolls); //On remplit cette liste des quatre premiers Employe dans listeEmploye

        while (!isTermine) { //Tant que l'utilisateur n'a pas terminé, on procède au recrutement

            afficheQueueEmploye(employesDispo); //On affiche quatre employés

            System.out.println();
            System.out.println("Le salaire de chacun de vos employés vous sera débité tous les mois.");
            System.out.println("Qui voulez-vous embaucher?");

            //Pour chaque Employe, on propose de l'embaucher seulement si il n'est pas déjà recruté
            //Sinon, le choix n'apparaît pas

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

            //On propose à l'utilisateur de Reroll les employés uniquement s'il l'a fait moins de trois fois
            System.out.println();
            if (numRerolls < 3) {
                System.out.println("(R) Reroll (" + (3-numRerolls) + "/3)");
            }

            //On propose à tout moment de terminer le recrutement
            System.out.println("(T) Terminer");

            String reponse = sc.nextLine();
            switch (reponse.toUpperCase()) {

                //On embauche l'Employe en fonction du choix de l'utilisateur
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
                case "R": //L'utilisateur Reroll
                    if (numRerolls < 4) { //On vérifie qu'il ait Reroll au plus trois fois

                        numRerolls += 1;                                                                    //Dans ce cas on incrémente numRerolls et on passe aux quatre employés suivants
                        employesDispo.clear();
                        employesDispo = queueEmployes(restaurant.listeEmployes, restaurant, numRerolls);
                    }
                    else { //Sinon, l'utilisateur a déjà Reroll trois fois et rien ne se produit
                        System.out.println("Saisie invalide");
                    }
                    break;
                case "T": //L'utilisateur finit de recruter, on sort de la boucle
                    isTermine = true;
            }
        }


    }

   
   /** 
    * Algo qui trie les cuisiniers en fonction de leur efficacité
    * @param listeCuisiniers
    */
   public static void trierCuisiniers(ArrayList<Cuisinier> listeCuisiniers) {

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

    
    /** 
     * Affiche les cuisiniers 
     * @param listeCuisiniers
     */
    public static void afficherCuisiniers(ArrayList<Cuisinier> listeCuisiniers) {
        System.out.println("Cuisiniers: ");
        System.out.println();
        int numEmploye = 0;
        for (int i = 0; i < listeCuisiniers.size(); ++i) {
            numEmploye += 1;
            System.out.println(numEmploye + ". " + listeCuisiniers.get(i).nom + " | Efficacité: " + listeCuisiniers.get(i).efficacite + " | Salaire: " + listeCuisiniers.get(i).salaire + "€");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    
    /** 
     * Algo qui trie les nettoyeurs en fonction de leur efficacité
     * @param listeNettoyeurs
     */
    public static void trierNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs) {

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

    
    /** 
     * Affiche les nettoyeurs
     * @param listeNettoyeurs
     */
    public static void afficherNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs) {
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
    
    /** 
     * Fonction qui lance le jeu
     * @param args
     */
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static void main(String[] args) {

        //On prépare d'abord l'environnement pour démarrer la simulation
        Data donnees = new Data();
        Scanner scan = new Scanner(System.in);

        System.out.println("Vous et deux de vos collègues avez fondé un restaurant à hamburgers.");

        //L'utilisateur doit disposer d'au moins un Cuisinier et un Nettoyeur au début de la partie.
        System.out.println("Votre premier ami/e est le cuistot du groupe. Comment s'appelle-t-il/elle?");
        String userAnswer = scan.nextLine();
        Cuisinier cuisinierDepart = new Cuisinier(userAnswer, 12);
        System.out.println("Votre autre ami/e assûre la propreté du local. Comment s'appelle-t-il/elle?");
        userAnswer = scan.nextLine();
        Nettoyeur nettoyeurDepart = new Nettoyeur(userAnswer, 12);

        System.out.println("Vous êtes le manager. Choisissez un nom pour le restaurant :");
        userAnswer = scan.nextLine();

        //Création des objets nécessaires pour démarrer la partie
        Restaurant restaurant = new Restaurant(userAnswer);
        restaurant.listeCuisiniers.add(cuisinierDepart);
        restaurant.listeNettoyeurs.add(nettoyeurDepart);

        System.out.println(restaurant.nom + " ouvre ses portes!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();

        Journee notreSimu = new Journee(1000, Jour.LUNDI,8,12,8);
        restaurant.genereClients(3);
        System.out.println("Début de la journée. Voici les employés qui ont postulé à Mama's Burgeria:");
        System.out.println();
        //La journée commence et on choisit les Employes à recruter
        pickEmployes(restaurant);
        System.out.println("[RÉCAPITULATIF STAFF]");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        //Avant l'ouverture, on affiche la liste de Nettoyeurs et Cuisiniers
        trierCuisiniers(restaurant.listeCuisiniers);
        afficherCuisiniers(restaurant.listeCuisiniers);
        trierNettoyeurs(restaurant.listeNettoyeurs);
        afficherNettoyeurs(restaurant.listeNettoyeurs);

        System.out.println("Entrez n'importe quelle touche pour procéder à l'ouverture.");
        userAnswer = scan.nextLine();
        restaurant.ouvert = true;
        ThreadHandleOpen clock = new ThreadHandleOpen(notreSimu, restaurant);
        new Thread(clock).start();
        ThreadTestCritique afficheCritique = new ThreadTestCritique(restaurant);
        new Thread(afficheCritique).start();
        ThreadHandleClient handleClient = new ThreadHandleClient(restaurant);
        new Thread(handleClient).start();
        ThreadHandleSurPlace handleSurPlace = new ThreadHandleSurPlace(restaurant);
        new Thread(handleSurPlace).start();



    }
    }
