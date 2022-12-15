package thread;

import employe.Cuisinier;
import restaurant.Restaurant;

import java.util.ArrayList;

public class ThreadHandleClient implements Runnable {
    private final Restaurant RESTO;

    public ThreadHandleClient(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while(RESTO.ouvert){ // Tant que le resto est ouvert
            try {
                Thread.sleep(1000); //On passe une seconde
            } catch (InterruptedException ignored) {
            }
            if(!RESTO.fileDAttente.isEmpty()){
                ArrayList<Cuisinier> listeCuisinier = RESTO.listeCuisiniers; // Liste de tous les cuisinier parmis les employés
                for(int i = 0; i < listeCuisinier.size(); ++i){ // On parcourt tous les cuisiniers de la liste
                    if(!listeCuisinier.get(i).occupe){ // Si un cuisinier est dispo
                        RESTO.listeCuisiniers.get(i).occupe = true; //Le cuisinier est donc maintenant occupé
                        RESTO.fileDAttente.get(0).passerCommande(); // Ce dernier passe commande
                        listeCuisinier.get(i).setCommande(RESTO.fileDAttente.get(0).getCommande()); //On attribue cette commande au cuisinier qui l'a pris en charge
                        RESTO.fileDAttente.get(0).setEstPris(true); // On prend le premier dans la file d'attente
                        System.out.println(RESTO.fileDAttente.get(0).getNom() + " viens d'être pris en charge par le cuisinier : " + listeCuisinier.get(i).nom);
                        System.out.println(RESTO.fileDAttente.get(0).getNom() + " vient de passer sa commande !");
                        RESTO.fileDAttente.remove(0); // On le retire de la file d'attente
                        ThreadProcessCommande t1 = new ThreadProcessCommande(listeCuisinier.get(i), RESTO); //On lance le thread de preparation de commande
                        new Thread(t1).start();



                        break;
                    }
                }
            }
            }

        }
    }
