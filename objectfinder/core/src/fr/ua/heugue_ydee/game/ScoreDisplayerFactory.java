package fr.ua.heugue_ydee.game;

import fr.ua.heugue_ydee.utils.TimeCountStrategy;

/**
 * A factory to build Score displayer with its strategy
 */
public class ScoreDisplayerFactory {

    /**
     * Create a score displayer for the sprint game mode
     *
     * @param strategy the strategy that the score displayer will use
     * @return The score displayer ready-to-use
     */
    public ScoreDisplayerStrategy createSprintScoreDisplayer(TimeCountStrategy strategy) {
        SprintScoreCounter counter = new SprintScoreCounter(strategy);
        ScoreDisplayerStrategy displayerStrategy = new SprintScoreDisplayer(counter);

        return displayerStrategy;
    }

    /**
     * Create a score displayer for the defi game mode
     *
     * @param strategy the strategy that the score displayer will use
     * @return The score displayer ready-to-use
     */
    public ScoreDisplayerStrategy createDefiScoreDisplayer(TimeCountStrategy strategy) {
        DefiScoreCounter counter = new DefiScoreCounter(strategy);
        ScoreDisplayerStrategy displayerStrategy = new DefiScoreDisplayer(counter);

        return displayerStrategy;
    }
}
