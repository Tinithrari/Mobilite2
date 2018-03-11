package fr.ua.heugue_ydee.game;

/**
 * Strategy to display the score for the sprint mode
 */
public class SprintScoreDisplayer extends ScoreDisplayerStrategy {

    private SprintScoreCounter counter;

    /**
     * Build a sprint score displayer
     *
     * @param counter The Score strategy to display
     */
    public SprintScoreDisplayer(SprintScoreCounter counter) {
        super();
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
