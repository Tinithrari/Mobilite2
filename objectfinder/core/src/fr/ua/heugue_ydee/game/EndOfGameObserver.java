package fr.ua.heugue_ydee.game;

/**
 * An interface to listen the end of the game event
 */
public interface EndOfGameObserver {
    /**
     * Method called on the end of the game
     */
    void notifyEndOfGame();
}
