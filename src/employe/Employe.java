package employe;

public class Employe {
    public String nom; //Nom de l'employe
    public int efficacite; //Unite arbitraire representant l'efficacite d'un employe
    public int energie; //Unite arbitraire representant l'energie qui reste à l'employe
    public double salaire; //Salaire de l'employe

    public Employe(String nom, int efficacite) {
        this.nom = nom;
        this.efficacite = efficacite;
        this.energie = 100;
        this.salaire = 1329.00;
    }

    public void augmentation (double montant) {
        this.salaire = this.salaire + montant;
        this.efficacite = this.efficacite + (int)Math.ceil(0.02*montant);
    }
}
