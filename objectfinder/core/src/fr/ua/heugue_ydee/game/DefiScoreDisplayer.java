package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Display the score for the defi mode
 */
public class DefiScoreDisplayer extends ScoreDisplayerStrategy {

    private DefiScoreCounter counter;

    /**
     * Build a defi score displayer
     *
     * @param counter the counter for this displayer
     */
    public DefiScoreDisplayer(DefiScoreCounter counter) {
        this.counter = counter;
    }

    /**
     * Get the score counter
     *
     * @return the score counter of this strategy
     */
    @Override
    public ScoreCounterStrategy getScoreCounter() {
        return this.counter;
    }

    @Override
    public void draw() {
        super.draw();
        // TODO
    }
}
