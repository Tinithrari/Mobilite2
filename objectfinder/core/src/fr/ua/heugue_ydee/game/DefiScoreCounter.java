package fr.ua.heugue_ydee.game;

import java.util.ArrayList;
import java.util.List;

import fr.ua.heugue_ydee.environment.DestroyableObservable;
import fr.ua.heugue_ydee.model.DefiScore;
import fr.ua.heugue_ydee.model.Score;
import fr.ua.heugue_ydee.utils.TimeCountStrategy;

/**
 * Created by xavie on 11/03/2018.
 */

public class DefiScoreCounter implements ScoreCounterStrategy {

    private int score;
    private List<EndOfGameObserver> observers;
    private TimeCountStrategy timeCountStrategy;
    private boolean blocked;

    /**
     * Create a score counter for defi mode
     *
     * @param timeCountStrategy The time counter to use with this strategy
     */
    public DefiScoreCounter(TimeCountStrategy timeCountStrategy) {
        this.timeCountStrategy = timeCountStrategy;
        this.score = 0;
        this.observers = new ArrayList<EndOfGameObserver>();
    }

    /**
     * Method called between two frames
     *
     * @param delta The delta time between two frames in millis
     */
    @Override
    public void update(int delta) {
        if (! blocked) {
            this.timeCountStrategy.addTimeMillis(delta);

            if (timeCountStrategy.getTime().getHours() == 0 &&
                    timeCountStrategy.getTime().getMilliseconds() == 0 &&
                    timeCountStrategy.getTime().getSeconds() == 0 &&
                    timeCountStrategy.getTime().getMinutes() == 0)
                notifyEndOfGame();
        }
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

    /**
     * Return the score of the game
     *
     * @return The score of the game
     */
    @Override
    public Score getScoreData() {
        DefiScore scoreData = new DefiScore();
        scoreData.setScore(score);
        return scoreData;
    }

    /**
     * Notify this object of the destruction of an object
     *
     * @param obs The observable which fire the event
     */
    @Override
    public void notifyDestroyableObserver(DestroyableObservable obs) {
        if (! blocked)
            this.score += 1;
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

    public int getScore() {
        return score;
    }



    public TimeCountStrategy getTimeCountStrategy() {
        return timeCountStrategy;
    }
}
