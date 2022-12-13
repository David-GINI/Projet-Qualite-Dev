package restaurant;

public enum Etat {
    PROPRE("Propre") {
        @Override
        Etat salir() {
            return Etat.CORRECT;
        }

        @Override
        Etat nettoyer() {
            return Etat.PROPRE;
        }
    },
    CORRECT("Correct") {
        @Override
        Etat salir() {
            return Etat.SALE;
        }

        @Override
        Etat nettoyer() {
            return Etat.PROPRE;
        }
    },
    SALE("Sale") {
        @Override
        Etat salir() {
            return Etat.SALE;
        }

        @Override
        Etat nettoyer() {
            return Etat.PROPRE;
        }
    };

    private final String value;

    Etat(String value) {
        this.value = value;
    }
    abstract Etat salir();
    abstract Etat nettoyer();
}
