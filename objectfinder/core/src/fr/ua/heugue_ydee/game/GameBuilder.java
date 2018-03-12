package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;

/**
 * An object which build a game scene
 */
public class GameBuilder {

    /**
     * The easy difficulty
     */
    public static final int EASY_DIFFICULTY = 0;

    /**
     * The normal difficulty
     */
    public static final int NORMAL_DIFFICULTY = 1;

    /**
     * The hard difficulty
     */
    public static final int HARD_DIFFICULTY = 2;

    /**
     * The sprint mode
     */
    public static final int SPRINT_MODE = 0;

    /**
     * The defi mode
     */
    public static final int DEFI_MODE = 1;

    private int difficulty;
    private int mode;

    private static final int coefficient = 2;
    private static final int worldWidth = Gdx.graphics.getWidth() * GameBuilder.coefficient;
    private static final int worldHeight = Gdx.graphics.getHeight() * GameBuilder.coefficient;

    /**
     * Build a game scene with a known game difficulty and a know game mode
     *
     * @param difficulty The difficulty of the game
     * @param mode The difficulty of the mode
     */
    public GameBuilder(int difficulty, int mode) {
        this.difficulty = difficulty;
        this.mode = mode;
    }

    /**
     * Build the game
     *
     * @return The game scene using the configuration given in this builder
     */
    public GameScene buildGame() {
        // TODO implement here
        return null;
    }

}