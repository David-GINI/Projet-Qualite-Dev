package journee;
import java.util.Random;
import java.util.Scanner;

public class Journee {
    public double obj_revenus;

    public Jour jour;
    public int heure_open = 11;
    public int heure_close = 00;
    public int heure = 0;


    public Journee(double obj_revenus, Jour jour, int heure_open, int heure_close, int heure) {
        this.obj_revenus = obj_revenus;
        this.jour = jour;
        this.heure_open = heure_open;
        this.heure_close = heure_close;
        this.heure = heure;
    }

    public double getObj_revenus() {
        return obj_revenus;
    }

    public void setObj_revenus(double obj_revenus) {
        this.obj_revenus = obj_revenus;
    }


    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public int getHeure_open() {
        return heure_open;
    }

    public void setHeure_open(int heure_open) {
        this.heure_open = heure_open;
    }

    public int getHeure_close() {
        return heure_close;
    }

    public void setHeure_close(int heure_close) {
        this.heure_close = heure_close;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void revenusEgalObjectif(double revenus){ //on vérifie si les revenus de la journée sont égales à l'objectif fixé
        if(revenus >= obj_revenus){ //objectif atteint
            System.out.println(revenus + "€\n" + obj_revenus + "€\n" + "Ce fut une bonne journée !");
        }
        else { //objectif non atteint
            System.out.println(revenus + "€\n" + obj_revenus +  "€\n" + "Cette journée n'était pas très bonne!");
        }
    }

    public void nextDay(){
        this.jour = this.jour.nextDay();
    }

}
