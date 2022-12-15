package simulation;

import employe.Cuisinier;
import employe.Employe;
import employe.Nettoyeur;
import journee.Jour;
import journee.Journee;
import misc.Data;
import restaurant.Restaurant;
import restaurant.Table;
import thread.*;

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
            } else if (response.toUpperCase().equals("N")){                                                                                                                //Si Nettoyeur, on crée un objet Nettoyeur avec les mêmes attributs que cet employé
                System.out.println(employesDispo.get(numEmploye).nom + " a rejoint vos rangs en tant que Nettoyeur!");
                nettoyeur = new Nettoyeur(employesDispo.get(numEmploye).nom, employesDispo.get(numEmploye).efficacite);
                restaurant.listeNettoyeurs.add(nettoyeur);                                                                          //Puis on l'ajoute à la listeNettoyeurs de Restaurant et on change son nom parmi les employesDispo
                employesDispo.get(numEmploye).setNom("EMBAUCHÉ");
            }
            else {
                System.out.println("Saisie invalide");
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
    * Algo qui trie les cuisiniers en fonction de leur efficacité ou de l'argent qu'ils ont généré
    * @param listeCuisiniers
    * @param param
    */
   public static void trierCuisiniers(ArrayList<Cuisinier> listeCuisiniers, String param) {
        for (int i = 1; i < listeCuisiniers.size(); ++i) {
            int j = i;

            if (param.equals("argentGenere")) {
                while (j > 0 && listeCuisiniers.get(j-1).argentGenere < listeCuisiniers.get(j).argentGenere) {
                    Cuisinier k = listeCuisiniers.get(j);
                    listeCuisiniers.set(j, listeCuisiniers.get(j - 1));
                    listeCuisiniers.set(j - 1, k);
                    j = j - 1;
                }
            }
            else {
                while (j > 0 && listeCuisiniers.get(j-1).efficacite < listeCuisiniers.get(j).efficacite) {
                    Cuisinier k = listeCuisiniers.get(j);
                    listeCuisiniers.set(j, listeCuisiniers.get(j - 1));
                    listeCuisiniers.set(j - 1, k);
                    j = j - 1;
                }
            }
        }
    }

    /**
     * Fonction qui remet l'attribut argentGenere à 0 pour tous les Cuisiniers
     * @param listeCuisiniers
     */
    public static void clearArgentGenere (ArrayList<Cuisinier> listeCuisiniers) {
       for (int i = 0; i < listeCuisiniers.size(); ++i) {
           listeCuisiniers.get(i).setArgentGenere(0);
       }
    };

    /**
     * Fonction qui remet l'attribut tablesNettoyees à 0 pour tous les Nettoyeurs
     * @param listeNettoyeurs
     */
    public static void clearTablesNettoyees (ArrayList<Nettoyeur> listeNettoyeurs) {
        for (int i = 0; i < listeNettoyeurs.size(); ++i) {
            listeNettoyeurs.get(i).setTablesNettoyees(0);
        }
    };

    
    /** 
     * Affiche les cuisiniers (affichage différent si la journée touche à sa fin)
     * @param listeCuisiniers
     * @param isDayEnd
     */
    public static void afficherCuisiniers(ArrayList<Cuisinier> listeCuisiniers, boolean isDayEnd) {
        System.out.println("Cuisiniers: ");
        System.out.println();
        int numEmploye = 0;
        for (int i = 0; i < listeCuisiniers.size(); ++i) {
            numEmploye += 1;
            if (isDayEnd) {
                System.out.println(numEmploye + ". " + listeCuisiniers.get(i).nom + " | Efficacité: " + listeCuisiniers.get(i).efficacite + " | Argent généré: " + listeCuisiniers.get(i).argentGenere +  " | Salaire: " + listeCuisiniers.get(i).salaire + "€");
            }
            else {
                System.out.println(numEmploye + ". " + listeCuisiniers.get(i).nom + " | Efficacité: " + listeCuisiniers.get(i).efficacite + " | Salaire: " + listeCuisiniers.get(i).salaire + "€");
            }
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    
    /** 
     * Algo qui trie les nettoyeurs en fonction de leur efficacité ou du nombre de tables qu'ils ont nettoyé
     * @param listeNettoyeurs
     * @param param
     */
    public static void trierNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs, String param) {
        for (int i = 1; i < listeNettoyeurs.size(); ++i) {
            int j = i;

            if (param.equals("tablesNettoyees")) {
                while (j > 0 && listeNettoyeurs.get(j-1).tablesNettoyees < listeNettoyeurs.get(j).tablesNettoyees) {
                    Nettoyeur k = listeNettoyeurs.get(j);
                    listeNettoyeurs.set(j, listeNettoyeurs.get(j - 1));
                    listeNettoyeurs.set(j - 1, k);
                    j = j - 1;
                }
            }
            else {
                while (j > 0 && listeNettoyeurs.get(j-1).efficacite < listeNettoyeurs.get(j).efficacite) {
                    Nettoyeur k = listeNettoyeurs.get(j);
                    listeNettoyeurs.set(j, listeNettoyeurs.get(j - 1));
                    listeNettoyeurs.set(j - 1, k);
                    j = j - 1;
                }
            }
        }
    }

    
    /** 
     * Affiche les nettoyeurs (affichage différent si la journée touche à sa fin)
     * @param listeNettoyeurs
     * @param isDayEnd
     */
    public static void afficherNettoyeurs(ArrayList<Nettoyeur> listeNettoyeurs, boolean isDayEnd) {
        System.out.println("Nettoyeurs: ");
        System.out.println();
        int numEmploye = 0;
        for (int i = 0; i < listeNettoyeurs.size(); ++i) {
            numEmploye += 1;
            if (isDayEnd) {
                System.out.println(numEmploye + ". " + listeNettoyeurs.get(i).nom + " | Efficacité: " + listeNettoyeurs.get(i).efficacite + " | Tables nettoyées: " + listeNettoyeurs.get(i).tablesNettoyees + " | Salaire: " + listeNettoyeurs.get(i).salaire + "€");
            }
            else {
                System.out.println(numEmploye + ". " + listeNettoyeurs.get(i).nom + " | Efficacité: " + listeNettoyeurs.get(i).efficacite + " | Salaire: " + listeNettoyeurs.get(i).salaire + "€");
            }
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
    }

    /**
     * Fonction qui effectue les appels nécessaires pour fermer le restaurant avant de passer à la journée suivante
     * @param restaurant
     * @param journee
     */
    public static void routineFermeture(Restaurant restaurant, Journee journee) {

        Scanner sc = new Scanner(System.in);
        String response;
        boolean isTermine = false;

        System.out.println(restaurant.nom + " ferme ses portes. Beau travail tout le monde!");
        System.out.println();

        System.out.println("Argent généré: " + restaurant.revenuParJour + "€ / " + journee.objectifRevenu + "€");
        System.out.println();
        System.out.println("[RÉCAPITULATIF STAFF - FIN DE JOURNÉE]");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        while (!isTermine) {
            trierCuisiniers(restaurant.listeCuisiniers, "argentGenere");
            afficherCuisiniers(restaurant.listeCuisiniers, true);
            System.out.println("Qui souhaitez-vous promouvoir?");
            System.out.println("Entrez le numéro de cet employé. Entrez (T) pour terminer.");
            response = sc.nextLine();
            if (response.toUpperCase().equals("T")) {
                isTermine = true;
            }
            else {
                int index = Integer.parseInt(response);
                promotionCuisnier(restaurant.listeCuisiniers, index-1);
            }
        }
        isTermine = false;
        while (!isTermine) {
            trierNettoyeurs(restaurant.listeNettoyeurs, "tablesNettoyees");
            afficherNettoyeurs(restaurant.listeNettoyeurs, true);
            System.out.println("Qui souhaitez-vous promouvoir?");
            System.out.println("Entrez le numéro de cet employé. Entrez (T) pour terminer.");
            response = sc.nextLine();
            if (response.toUpperCase().equals("T")) {
                isTermine = true;
            } else {
                int index = Integer.parseInt(response);
                promotionNettoyeur(restaurant.listeNettoyeurs, index - 1);
            }
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        isTermine = false;
        while (!isTermine) {
                System.out.println();
                System.out.println("Argent en caisse: " + restaurant.caisse + "€");
                System.out.println("Vous disposez de " + restaurant.listeTables.size() + "/" + restaurant.NB_TABLES_MAX + " tables.");
                double prixTable = Math.round((restaurant.listeTables.size() * 150) * 100.0) / 100.0;
                System.out.println();
                if (restaurant.listeTables.size() < restaurant.NB_TABLES_MAX) {
                    System.out.println("(A) Acheter une table pour " + prixTable + "€");
                }
                System.out.println("(T) Terminer");
                response = sc.nextLine();

                switch (response.toUpperCase()) {
                    case "A":
                        addTable(restaurant, prixTable);
                        break;
                    case "T":
                        isTermine = true;
                        break;
                    default:
                        System.out.println("Saisie invalide");
                        break;
                }
        }
        clearArgentGenere(restaurant.listeCuisiniers);
        clearTablesNettoyees(restaurant.listeNettoyeurs);
    }

    /**
     * Fonction qui gère la promotion d'un Cuisinier
     * @param listeCuisiniers
     * @param index
     */
    public static void promotionCuisnier (ArrayList<Cuisinier> listeCuisiniers, int index) {
        Scanner sc = new Scanner(System.in);

        double montant;
        double oldSalaire;
        int oldEfficacite;

        if (0 <= index && index < listeCuisiniers.size()) {
            System.out.println("Entrez le montant de l'augmentation pour " + listeCuisiniers.get(index).nom + ".");
            montant = sc.nextDouble();
            oldSalaire = listeCuisiniers.get(index).salaire;
            oldEfficacite = listeCuisiniers.get(index).efficacite;
            listeCuisiniers.get(index).augmentation(montant);

            System.out.println("Félicitations à " + listeCuisiniers.get(index).nom + " pour son augmentation!");
            System.out.println("Salaire: " + oldSalaire + " -> " + listeCuisiniers.get(index).salaire);
            System.out.println("Efficacite: " + oldEfficacite + " -> " + listeCuisiniers.get(index).efficacite);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

        else {
            System.out.println("Saisie invalide");
        }
    }

    /**
     * Fonction qui gère la promotion d'un Nettoyeur
     * @param listeNettoyeurs
     * @param index
     */
    public static void promotionNettoyeur (ArrayList<Nettoyeur> listeNettoyeurs, int index) {
        Scanner sc = new Scanner(System.in);

        double montant;
        double oldSalaire;
        int oldEfficacite;

        if (0 <= index && index < listeNettoyeurs.size()) {
            System.out.println("Entrez le montant de l'augmentation pour " + listeNettoyeurs.get(index).nom + ".");
            montant = sc.nextDouble();
            oldSalaire = listeNettoyeurs.get(index).salaire;
            oldEfficacite = listeNettoyeurs.get(index).efficacite;
            listeNettoyeurs.get(index).augmentation(montant);

            System.out.println("Félicitations à " + listeNettoyeurs.get(index).nom + " pour son augmentation!");
            System.out.println("Salaire: " + oldSalaire + " -> " + listeNettoyeurs.get(index).salaire);
            System.out.println("Efficacite: " + oldEfficacite + " -> " + listeNettoyeurs.get(index).efficacite);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

        else {
            System.out.println("Saisie invalide");
        }
    }

    public static void addTable (Restaurant restaurant, double prix) {
        if ((restaurant.caisse - prix) < 0) {
            System.out.println("Vous ne disposez pas des fonds nécessaires.");
        }
        else {
            restaurant.caisse = restaurant.caisse - prix;
            Table table = new Table( restaurant.listeTables.size() + 1, 4);
            restaurant.listeTables.add(table);
        }
    };

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    /**
     * Fonction qui lance le jeu
     * @param args
     */
    public static void main(String[] args) {

        //On prépare d'abord l'environnement pour démarrer la simulation
        Data donnees = new Data();
        Scanner scan = new Scanner(System.in);
        Table t1 = new Table(1,5);
        Table t2 = new Table(2,5);
        Table t3 = new Table(3,5);

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
        restaurant.listeTables.add(t1);
        restaurant.listeTables.add(t2);
        restaurant.listeTables.add(t3);
        System.out.println(restaurant.nom + " ouvre ses portes!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();

        Journee notreSimu = new Journee(1000, Jour.LUNDI,8,12,8);
        System.out.println("Début de la journée. Voici les employés qui ont postulé à Mama's Burgeria:");
        System.out.println();
        //La journée commence et on choisit les Employes à recruter
        pickEmployes(restaurant);
        System.out.println("[RÉCAPITULATIF STAFF]");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        //Avant l'ouverture, on affiche la liste de Nettoyeurs et Cuisiniers
        trierCuisiniers(restaurant.listeCuisiniers, "efficacite");
        afficherCuisiniers(restaurant.listeCuisiniers, false);
        trierNettoyeurs(restaurant.listeNettoyeurs, "efficacite");
        afficherNettoyeurs(restaurant.listeNettoyeurs, false);

        System.out.println("Entrez n'importe quelle touche pour procéder à l'ouverture.");
        userAnswer = scan.nextLine();
        System.out.println("Bienvenue chez " + restaurant.nom + ", nous sommes " + notreSimu.jour);
        Scanner newObjRevenu = new Scanner(System.in);
        System.out.println("Quel est votre objectif de revenus aujourd'hui patron ?");
        notreSimu.setObjectifRevenu(newObjRevenu.nextDouble());
        restaurant.ouvert = true;
        ThreadHandleOpen clock = new ThreadHandleOpen(notreSimu, restaurant);
        new Thread(clock).start();
        ThreadClientWait attenteClient = new ThreadClientWait(restaurant);
        new Thread(attenteClient).start();

        ThreadTestCritique afficheCritique = new ThreadTestCritique(restaurant);
        new Thread(afficheCritique).start();
        ThreadArriveeClient arriveeClient = new ThreadArriveeClient(restaurant);
        new Thread(arriveeClient).start();
        ThreadHandleClient handleClient = new ThreadHandleClient(restaurant);
        new Thread(handleClient).start();
        ThreadHandleSurPlace handleSurPlace = new ThreadHandleSurPlace(restaurant);
        new Thread(handleSurPlace).start();
        ThreadHandleTableRestaurant servirSurPlace = new ThreadHandleTableRestaurant(restaurant);
        new Thread(servirSurPlace).start();
        ThreadHandleEtat handleEtat = new ThreadHandleEtat(restaurant);
        new Thread(handleEtat).start();

    }
}
