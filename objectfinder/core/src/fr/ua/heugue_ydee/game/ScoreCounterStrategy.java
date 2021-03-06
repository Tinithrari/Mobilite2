package fr.ua.heugue_ydee.game;

import fr.ua.heugue_ydee.environment.DestroyableObserver;
import fr.ua.heugue_ydee.model.Score;

/**
 * A strategy for the scoring management
 */
public interface ScoreCounterStrategy extends EndOfGameObservable, DestroyableObserver {

    /**
     * Method called between two frames
     *
     * @param delta The delta time between two frames in millis
     */
    void update(int delta);

    /**
     * Block the count of the score
     *
     * @param blocked is the score count blocked
     */
    void setBlocked(boolean blocked);

    /**
     * Return the score of the game
     *
     * @return The score of the game
     */
    Score getScoreData();
}
