package journee;

public class Journee {
    public double objectifRevenu;

    public Jour jour;
    public int heureOpen = 11;
    public int heureClose = 00;
    public int heure = 0;


    public Journee(double objectifRevenu, Jour jour, int heureOpen, int heureClose, int heure) {
        this.objectifRevenu = objectifRevenu;
        this.jour = jour;
        this.heureOpen = heureOpen;
        this.heureClose = heureClose;
        this.heure = heure;
    }

    public double getObjectifRevenu() {
        return objectifRevenu;
    }

    public void setObjectifRevenu(double objectifRevenu) {
        this.objectifRevenu = objectifRevenu;
    }


    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public int getHeureOpen() {
        return heureOpen;
    }

    public void setHeureOpen(int heureOpen) {
        this.heureOpen = heureOpen;
    }

    public int getHeureClose() {
        return heureClose;
    }

    public void setHeureClose(int heureClose) {
        this.heureClose = heureClose;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void revenuEgalObjectif(double revenus){ //on vérifie si les revenus de la journée sont égales à l'objectif fixé
        if(revenus >= objectifRevenu){ //objectif atteint
            System.out.println(revenus + "€\n" + objectifRevenu + "€\n" + "Ce fut une bonne journée !");
        }
        else { //objectif non atteint
            System.out.println(revenus + "€\n" + objectifRevenu +  "€\n" + "Cette journée n'était pas très bonne!");
        }
    }

    public void nextDay(){
        this.jour = this.jour.nextDay();
    }

}
