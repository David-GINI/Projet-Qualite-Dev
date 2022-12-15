package journee;

import restaurant.Etat;
/**
 * Enumération des jours possibles
 * @author JOLY GINI MOUMANE
 */
public enum Jour {
    LUNDI("Lundi"){
        @Override
        Jour nextDay() {
            return Jour.MARDI;
        }
    },
    MARDI("Mardi"){
        @Override
        Jour nextDay() {
            return Jour.MERCREDI;
        }
    },
    MERCREDI("Mercredi"){
        @Override
        Jour nextDay() {
            return Jour.JEUDI;
        }
    },
    JEUDI("Jeudi"){
        @Override
        Jour nextDay() {
            return Jour.VENDREDI;
        }
    },
    VENDREDI("Vendredi"){
        @Override
        Jour nextDay() {
            return Jour.SAMEDI;
        }
    },
    SAMEDI("Samedi"){
        @Override
        Jour nextDay() {
            return Jour.DIMANCHE;
        }
    },
    DIMANCHE("Dimanche"){
        @Override
        Jour nextDay() {
            return Jour.LUNDI;
        }
    };
    private final String value;

    Jour(String value) {
        this.value = value;
    }
    /**
     * Passe au jour suivant
     */
    abstract Jour nextDay();
}
