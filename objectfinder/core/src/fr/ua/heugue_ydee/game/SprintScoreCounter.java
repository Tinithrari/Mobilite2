package fr.ua.heugue_ydee.game;

import java.util.ArrayList;
import java.util.List;

import fr.ua.heugue_ydee.environment.DestroyableObservable;
import fr.ua.heugue_ydee.utils.TimeCountStrategy;

/**
 * Score counter for the sprint mode
 */
public class SprintScoreCounter implements ScoreCounterStrategy {

    private List<EndOfGameObserver> observers;
    private TimeCountStrategy timeCountStrategy;
    private boolean blocked;

    /**
     * Construct a sprint score counter
     */
    public SprintScoreCounter(TimeCountStrategy timeCountStrategy) {
        this.observers = new ArrayList<EndOfGameObserver>();
        this.timeCountStrategy = timeCountStrategy;
        this.blocked = false;
    }

    /**
     * Notify this object of the destruction of an object
     *
     * @param obs The observable which fire the event
     */
    @Override
    public void notifyDestroyableObserver(DestroyableObservable obs) {
        this.notifyEndOfGame();
    }

    /**
     * Add an element to the list of end of game observer
     *
     * @param obs The observer to add to the list
     */
    @Override
    public void addEndOfGameObserver(EndOfGameObserver obs) {
        this.observers.add(obs);
    }

    /**
     * Remove an end of game observer from the list
     *
     * @param obs The observer to remove from the list
     */
    @Override
    public void removeEndOfGameObserver(EndOfGameObserver obs) {
        this.observers.remove(obs);
    }

    /**
     * Notify the observers of the end of the game
     */
    @Override
    public void notifyEndOfGame() {
        for (EndOfGameObserver obs: observers)
            obs.notifyEndOfGame();
    }

    /**
     * Method called between two frames
     *
     * @param delta The delta time between two frames in millis
     */
    @Override
    public void update(int delta) {
        if (! blocked)
        this.timeCountStrategy.addTimeMillis(delta);
    }

    /**
     * Block the count of the score
     *
     * @param blocked is the score count blocked
     */
    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public TimeCountStrategy getTimeCountStrategy() {
        return timeCountStrategy;
    }
}
