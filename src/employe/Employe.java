package employe;
/**
 * Class Employé, gestion d'un employé et toutes ses méthodes
 * @author G6
 */
public class Employe {
    public String nom; //Nom de l'employe
    public int efficacite; //Unite arbitraire representant l'efficacite d'un employe
    public int energie; //Unite arbitraire representant l'energie qui reste à l'employe
    public double salaire; //Salaire de l'employe
    public boolean occupe;

    /**
     * Constructeur de la class Employe
     * @param nom
     * @param efficacite
     */
    public Employe(String nom, int efficacite) {
        this.nom = nom;
        this.efficacite = efficacite;
        this.energie = 100;
        this.salaire = 1329.00;
        this.occupe = false;
    }

    
    /** 
     * Setter du nom de l'employé
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /** 
     * setter de l'efficacité
     * @param efficacite
     */
    public void setEfficacite(int efficacite) {
        this.efficacite = efficacite;
    }

    
    /** 
     * Fonction qui augmente de "montant" le salaire de l'employé et qui améliore son efficacité en conséquence
     * @param montant
     */
    public void augmentation (double montant) {
        if (montant > 0) {
            this.salaire = this.salaire + montant;
            this.efficacite = this.efficacite + (int) Math.round(0.015 * montant);
        }
        else {
            System.out.println("Saisie invalide");
        }
    }

    
    /** 
     * toString de la Class Employé
     * @return String
     */
    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", efficacite=" + efficacite +
                ", salaire=" + salaire +
                '}';
    }
}
