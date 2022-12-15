package thread;

import restaurant.Restaurant;

public class ThreadHandleSurPlace implements Runnable {
    private Restaurant RESTO;

    public ThreadHandleSurPlace(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while (RESTO.ouvert) {// Tant que le resto est ouvert
            int nombreTablesCompletes = 0;
            if (!RESTO.listeClientsPris.isEmpty()) {
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
                    for(int i = 0; i< RESTO.listeClientsPris.size(); ++i){
                        if (RESTO.listeClientsPris.get(i).isSurPlace()) { // Si le client i dans la liste des clients pris mange sur place
                            for (int j = 0; j < RESTO.listeTables.size(); ++j) {// On parcourt la liste des tables
                                if (RESTO.listeTables.get(j).nbClients != RESTO.listeTables.get(j).CAPACITE_MAX) { // Si la table "i" n'est pas pleine
                                        RESTO.listeTables.get(j).clientsATable.add(RESTO.listeClientsPris.get(i)); // On l'affecte à cette table
                                        RESTO.listeTables.get(j).nbClients += 1;
                                        System.out.println(RESTO.listeClientsPris.get(i) + " va à la table numéro " + RESTO.listeTables.get(j).numero + " et sera bientot servi");
                                        RESTO.listeClientsPris.remove(i); // On le retire de la liste des clients pris
                                    }
                            }
                        }
                        else {// Si il ne mange pas sur place
                            if (RESTO.listeClientsPris.get(i).getCommande().isDone) { //Si leurs commandes sont prêtes
                                RESTO.listeClientsPris.get(i).setParti(true); // Ils partent
                                System.out.println(RESTO.listeClientsPris.get(i).getNom() + " a eu sa commande a emporté, il va payer puis quitter le restaurant ! ");
                                RESTO.ajouterArgent(RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(0).getCommande().prix); // il paye et cela s'ajoute dans la caisse du restaurant
                                System.out.println(RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(0).getCommande().prix + " euros viennent d'être ajouté à la caisse");
                                RESTO.listeClientsPris.remove(i); // On les retire de la liste des gens pris
                            }
                        }
                    }




            }

        }

    }

}