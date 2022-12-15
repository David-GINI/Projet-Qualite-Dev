package thread;

import restaurant.Restaurant;
/**
 * Thread qui gère la gestion de remise de commande sur place ou non
 * @author G6
 */
public class ThreadHandleSurPlace implements Runnable {
    private Restaurant RESTO;
/**
     * Constructeur du Thread Handle Sur place
     * @param RESTO
     */
    public ThreadHandleSurPlace(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * sers le client si il mange sur place et lui attribue une table
     * ou sers le client qui paye et pars si il mange a emporter
     */
    public void run() {
        while (RESTO.ouvert) {// Tant que le resto est ouvert
            int nombreTablesCompletes = 0;
                for (int i = 0; i < RESTO.listeTables.size(); ++i) {
                    if (RESTO.listeTables.get(i).nbClients == RESTO.listeTables.get(i).CAPACITE_MAX) {
                        nombreTablesCompletes += 1;

                    }
                }
                    // On test jusque la si le resto est plein
                    if (nombreTablesCompletes == RESTO.listeTables.size() && !RESTO.complet) {
                        System.out.println("Restautant Complet !! ");
                        RESTO.complet = true;

                    }
                    if(!RESTO.listeClientsPris.isEmpty()){
                        for(int i = 0; i< RESTO.listeClientsPris.size(); ++i){
                            if (RESTO.listeClientsPris.get(i).isSurPlace()) { // Si le client i dans la liste des clients pris mange sur place
                                for (int j = 0; j < RESTO.listeTables.size(); ++j) {// On parcourt la liste des tables
                                    if (RESTO.listeTables.get(j).nbClients != RESTO.listeTables.get(j).CAPACITE_MAX) { // Si la table "i" n'est pas pleine
                                        RESTO.listeTables.get(j).clientsATable.add(RESTO.listeClientsPris.get(i)); // On l'affecte à cette table
                                        RESTO.listeTables.get(j).clientsATable2.add(RESTO.listeClientsPris.get(i)); // On l'affecte à cette table
                                        RESTO.listeTables.get(j).nbClients += 1;
                                        System.out.println("Le client : "+RESTO.listeClientsPris.get(i).getNom() + " va à la table numéro " + RESTO.listeTables.get(j).numero + " et sera bientot servi");
                                        System.out.println();
                                        RESTO.listeClientsPris.remove(i); // On le retire de la liste des clients pris
                                        break;
                                    }
                                }
                            }
                            else {// Si il ne mange pas sur place
                                if (RESTO.listeClientsPris.get(i).getCommande().isDone) { //Si leurs commandes sont prêtes
                                    RESTO.listeClientsPris.get(i).setParti(true); // Ils partent
                                    double prix = (double) ((RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(i).getCommande().prix)*100.0)/100.0;
                                    System.out.println(RESTO.listeClientsPris.get(i).getNom() + " a eu sa commande a emporté, "+ prix + " euros viennent d'être ajouté à la caisse");
                                    System.out.println();
                                    RESTO.ajouterArgent(RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(i).getCommande().prix); // il paye et cela s'ajoute dans la caisse du restaurant
                                    RESTO.listeClientsPris.remove(i); // On les retire de la liste des gens pris
                                }
                            }
                        }
                    }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored){
            }
        }

    }