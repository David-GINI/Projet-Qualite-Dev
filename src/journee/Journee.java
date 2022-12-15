package journee;
/**
 * Class Journee
 * @author G6
 */
public class Journee {
    public double objectifRevenu;

    public Jour jour;
    public int heureOpen = 11;
    public int heureClose = 00;
    public int heure = 0;

    /**
     * Constructeur de la class Journée
     * @param objectifRevenu
     * @param jour
     * @param heureOpen
     * @param heureClose
     * @param heure
     */
    public Journee(double objectifRevenu, Jour jour, int heureOpen, int heureClose, int heure) {
        this.objectifRevenu = objectifRevenu;
        this.jour = jour;
        this.heureOpen = heureOpen;
        this.heureClose = heureClose;
        this.heure = heure;
    }

    
    /** 
     * Renvoie l'objectif de revenus pour la journée
     * @return double
     */
    public double getObjectifRevenu() {
        return objectifRevenu;
    }

    
    /** 
     * Setter de l'objectif des revenus pour la journée
     * @param objectifRevenu
     */
    public void setObjectifRevenu(double objectifRevenu) {
        this.objectifRevenu = objectifRevenu;
    }


    
    /** 
     * Renvoie le jour de la journée (Lundi, Mardi,...)
     * @return Jour
     */
    public Jour getJour() {
        return jour;
    }

    
    /** 
     * Setter du jour selon l'enumaration JOUR
     * @param jour
     */
    public void setJour(Jour jour) {
        this.jour = jour;
    }

    
    /** 
     * Renvoie l'heure d'ouverture de la journée
     * @return int
     */
    public int getHeureOpen() {
        return heureOpen;
    }

    
    /** 
     * Setter de l'heure d'ouverture de la journée
     * @param heureOpen
     */
    public void setHeureOpen(int heureOpen) {
        this.heureOpen = heureOpen;
    }

    
    /** 
     * Renvoie l'heure de fermeture de la journée
     * @return int
     */
    public int getHeureClose() {
        return heureClose;
    }

    
    /** 
     * Setter de l'heure de fermeture de la journée
     * @param heureClose
     */
    public void setHeureClose(int heureClose) {
        this.heureClose = heureClose;
    }

    
    /** 
     * Renvoie l'heure de la journée
     * @return int
     */
    public int getHeure() {
        return heure;
    }

    
    /** 
     * Setter de l'heure de la journée
     * @param heure
     */
    public void setHeure(int heure) {
        this.heure = heure;
    }

    
    /** 
     * Fonction qui compare l'objectif de revenus et les revenus réels en dans une journée
     * @param revenus(
     */
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
