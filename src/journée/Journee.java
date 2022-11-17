package journée;

public class Journee {
    public int obj_revenus;
    public int revenus;
    public jour jour;
    public String heure_open = "11h";
    public String heure_close = "00h";

    public Journee(int obj_revenus, int revenus, jour jour, String heure_open, String heure_close) {
        this.obj_revenus = obj_revenus;
        this.revenus = revenus;
        this.jour = jour;
        this.heure_open = heure_open;
        this.heure_close = heure_close;
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

    public jour getJour() {
        return jour;
    }

    public void setJour(jour jour) {
        this.jour = jour;
    }

    public String getHeure_open() {
        return heure_open;
    }

    public void setHeure_open(String heure_open) {
        this.heure_open = heure_open;
    }

    public String getHeure_close() {
        return heure_close;
    }

    public void setHeure_close(String heure_close) {
        this.heure_close = heure_close;
    }

    public void revenusEgalObjectif(){ //on vérifie si les revenus de la journée sont égales à l'objectif fixé
        if(revenus >= obj_revenus){ //objectif atteint
            System.out.println(revenus + "€\n" + obj_revenus + "€\n" + "Ce fut une bonne journée !");
        }
        else { //objectif non atteint
            System.out.println(revenus + "€\n" + obj_revenus +  "€\n" + "Cette journée n'était pas très bonne!");
        }
    }




}
