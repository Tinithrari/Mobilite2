package fr.ua.heugue_ydee.game;

import fr.ua.heugue_ydee.environment.DestroyableObserver;

/**
 * Created by xavie on 11/03/2018.
 */

/**
 * Element that can be observed to know the end of the game
 */
public interface EndOfGameObservable extends DestroyableObserver {
    /**
     * Add an element to the list of end of game observer
     *
     * @param obs The observer to add to the list
     */
    void addEndOfGameObserver(EndOfGameObserver obs);

    /**
     * Remove an end of game observer from the list
     *
     * @param obs The observer to remove from the list
     */
    void removeEndOfGameObserver(EndOfGameObserver obs);

    /**
     * Notify the observers of the end of the game
     */
    void notifyEndOfGame();
}
