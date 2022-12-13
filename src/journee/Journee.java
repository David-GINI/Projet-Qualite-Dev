package journee;
import java.util.Random;
import java.util.Scanner;

public class Journee {
    public int obj_revenus;
    public int revenus;

    public Jour jour;
    public int heure_open = 11;
    public int heure_close = 00;
    public int heure = 0;


    public Journee(int obj_revenus, int revenus, Jour jour, int heure_open, int heure_close, int heure) {
        this.obj_revenus = obj_revenus;
        this.revenus = revenus;
        this.jour = jour;
        this.heure_open = heure_open;
        this.heure_close = heure_close;
        this.heure = heure;
    }

    public int getObj_revenus() {
        return obj_revenus;
    }

    public void setObj_revenus(int obj_revenus) {
        this.obj_revenus = obj_revenus;
    }

    public int getRevenus() {
        return revenus;
    }

    public void setRevenus(int revenus) {
        this.revenus = revenus;
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

    public void revenusEgalObjectif(){ //on vérifie si les revenus de la journée sont égales à l'objectif fixé
        if(revenus >= obj_revenus){ //objectif atteint
            System.out.println(revenus + "€\n" + obj_revenus + "€\n" + "Ce fut une bonne journée !");
        }
        else { //objectif non atteint
            System.out.println(revenus + "€\n" + obj_revenus +  "€\n" + "Cette journée n'était pas très bonne!");
        }
    }

    public void horloge(){
     while(true){
         this.heure = 0;
         while(this.heure <= 24){
             this.heure += 1;
         }
     }
    }


    public void fermeture(){
        if(heure == heure_close){
            Scanner jourSuivant = new Scanner(System.in);
            System.out.println("Mama's Burgeria est fermé, revenez demain ?");
            String choix = jourSuivant.nextLine();
            if(choix == "oui"){
                LUNDI ("Lundi"){

                }
            }
            else if (choix == "non") {
                System.out.println("Merci d'avoir joué !");
            }

        }
    }

    public void ouverture(){

        if (heure == heure_open){
            System.out.println("Bienvenue chez Mama's Burgeria");
        }
    }

}
