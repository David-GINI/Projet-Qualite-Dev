package Thread;

import employe.Cuisinier;
import restaurant.Restaurant;

import java.util.ArrayList;

public class Thead_Handle_Client implements Runnable {
    private final Restaurant RESTO;

    public Thead_Handle_Client(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while(RESTO.ouvert){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
                ArrayList<Cuisinier> listeCuisinier = RESTO.getCuisinier();
                for(int i = 0; i < listeCuisinier.size(); ++i){
                        if(!RESTO.listeEmployes.get(i).occupe){
                            RESTO.fileDAttente.get(0).setEstPris(true);
                            RESTO.fileDAttente.get(0).Passer_Commander();
                            listeCuisinier.get(i).setCommande(RESTO.fileDAttente.get(0).getCommande());
                            ThreadProcessCommande t1 = new ThreadProcessCommande(listeCuisinier.get(i), RESTO);
                            new Thread(t1).start();
                            RESTO.listeClientsPris.add(RESTO.fileDAttente.get(0));
                            RESTO.fileDAttente.remove(0);
                            RESTO.listeEmployes.get(i).occupe = true;
                            break;
                    }
                }
            }

        }
    }
