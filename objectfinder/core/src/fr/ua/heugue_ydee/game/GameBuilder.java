package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.ua.heugue_ydee.environment.Terrain;
import fr.ua.heugue_ydee.environment.TerrainFactory;
import fr.ua.heugue_ydee.utils.CameraGesture;
import fr.ua.heugue_ydee.utils.DBAdapter;
import fr.ua.heugue_ydee.utils.TimeCountStrategy;
import fr.ua.heugue_ydee.utils.TimeCounterFactory;

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
    private DBAdapter adapter;

    private static final int COEFFICIENT = 2;
    private static final int WORLD_WIDTH = Gdx.graphics.getWidth() * GameBuilder.COEFFICIENT;
    private static final int WORLD_HEIGHT = Gdx.graphics.getHeight() * GameBuilder.COEFFICIENT;

    private static final float CAMERA_MASS = 0.0025f;

    /**
     * Build a game scene with a known game difficulty and a know game mode
     *
     * @param difficulty The difficulty of the game
     * @param mode The difficulty of the mode
     * @param adapter The adapter to save game data in a database
     */
    public GameBuilder(int difficulty, int mode, DBAdapter adapter) {
        this.difficulty = difficulty;
        this.mode = mode;
        this.adapter = adapter;
    }

    /**
     * Build the game
     *
     * @return The game scene using the configuration given in this builder
     */
    public GameScene buildGame() {
        // Generate the Environment
        Stage stage = new Stage(new ScreenViewport());
        CameraGesture cameraGesture = new CameraGesture(stage.getCamera(), CAMERA_MASS);
        TerrainFactory terrainFactory = new TerrainFactory();
        Terrain t;
        if (difficulty == 0)
            t = terrainFactory.createColoredTerrain(WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE, cameraGesture);
        else
            t = terrainFactory.createTiledTerrain(WORLD_WIDTH, WORLD_HEIGHT, cameraGesture);
        cameraGesture.setTerrain(t);

        // Generate the mechanics of the game
        TimeCounterFactory timeCounterFactory = new TimeCounterFactory();
        ScoreDisplayerFactory displayerFactory = new ScoreDisplayerFactory();
        TimeCountStrategy timeCountStrategy;
        ScoreDisplayerStrategy scoreDisplayerStrategy;

        if (this.mode == SPRINT_MODE) {
            timeCountStrategy = timeCounterFactory.createChronometer();
            scoreDisplayerStrategy = displayerFactory.createSprintScoreDisplayer(timeCountStrategy);
        } else {
            timeCountStrategy = timeCounterFactory.createMinuter(1, 0);
            scoreDisplayerStrategy = displayerFactory.createDefiScoreDisplayer(timeCountStrategy);
        }

        EndDialog dialog = new EndDialog(adapter, difficulty);

        GameScene scene = new GameScene(t, adapter, cameraGesture, scoreDisplayerStrategy.getScoreCounter(),
                scoreDisplayerStrategy, stage, dialog);

        return scene;
    }
}