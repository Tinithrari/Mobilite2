package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import fr.ua.heugue_ydee.environment.Terrain;
import fr.ua.heugue_ydee.utils.CameraGesture;
import fr.ua.heugue_ydee.utils.DBAdapter;

/**
 * The game scene
 */
public class GameScene implements EndOfGameObserver{

    private Stage sceneGraph;
    private CameraGesture camera;
    private DBAdapter database;
    private ScoreCounterStrategy scoreCounter;
    private ScoreDisplayerStrategy scoreDisplayerStrategy;
    private EndDialog dialog;

    private static final int TO_MILLIS_COEFFICIENT = 1000;

    /**
     * Build a gamescene using its terrain and its database
     *
     * @param terrain The terrain to use for the game
     * @param database The database to use to register scores
     * @param scoreCounter The strategy to count scores
     */
    public GameScene(Terrain terrain, DBAdapter database, CameraGesture camera, ScoreCounterStrategy scoreCounter,
                     ScoreDisplayerStrategy scoreDisplayerStrategy, Stage sceneGraph, EndDialog dialog) {
        this.database = database;
        this.camera = camera;
        this.sceneGraph = sceneGraph;
        this.sceneGraph.addActor(terrain);
        Gdx.input.setInputProcessor(this.camera);
        this.scoreCounter = scoreCounter;
        this.scoreDisplayerStrategy = scoreDisplayerStrategy;
        terrain.addDestroyableObserver(this.scoreCounter);
        this.scoreCounter.addEndOfGameObserver(this);
        this.dialog = dialog;
        this.database = database;
    }

    /**
     * Render the scene
     */
    public void render() {
        this.camera.update(Gdx.graphics.getDeltaTime());
        this.scoreCounter.update((int) (Gdx.graphics.getDeltaTime() * TO_MILLIS_COEFFICIENT));
        this.sceneGraph.draw();
        this.scoreDisplayerStrategy.draw();
    }

    /**
     * Dispose the scene
     */
    public void dispose() {
        this.sceneGraph.dispose();
        this.scoreDisplayerStrategy.dispose();
    }

    /**
     * Method called on the end of the game
     */
    @Override
    public void notifyEndOfGame() {
        this.camera.setBlocked(true);
        this.scoreCounter.setBlocked(true);

        this.dialog.setScore(scoreCounter.getScoreData());

        Gdx.input.setInputProcessor(sceneGraph);

        this.dialog.addContent();
        this.sceneGraph.addActor(dialog);
        this.dialog.pack();

    }
}