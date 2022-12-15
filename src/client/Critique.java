package client;

import restaurant.Restaurant;
/**
 * Interface du Critique
 * @author G6
 */
public interface Critique {
    /**
     * Note le Restaurant rentré en paramètre
     * @param restaurant
     */
    public void noter(Restaurant restaurant);
}
