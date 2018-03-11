package fr.ua.heugue_ydee.game;

import fr.ua.heugue_ydee.environment.DestroyableObserver;

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
}
