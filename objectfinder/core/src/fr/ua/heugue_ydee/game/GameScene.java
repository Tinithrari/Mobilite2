package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.ua.heugue_ydee.environment.Terrain;
import fr.ua.heugue_ydee.utils.CameraGesture;
import fr.ua.heugue_ydee.utils.DBAdapter;
import fr.ua.heugue_ydee.utils.TimeCountStrategy;

/**
 * The game scene
 */
public class GameScene {

    private Stage sceneGraph;
    private CameraGesture camera;
    private DBAdapter database;
    private TimeCountStrategy time;

    private static final float CAMERA_WEIGHT = 0.005f;

    /**
     * Build a gamescene using its terrain and its database
     *
     * @param terrain The terrain to use for the game
     * @param database The database to use to register scores
     */
    public GameScene(Terrain terrain, DBAdapter database, TimeCountStrategy time) {;
        this.database = database;
        this.sceneGraph = new Stage(new ScreenViewport());
        this.camera = new CameraGesture(this.sceneGraph.getCamera(), GameScene.CAMERA_WEIGHT, terrain);
        this.sceneGraph.addActor(terrain);
        Gdx.input.setInputProcessor(this.camera);
        this.time = time;
    }

    /**
     * Render the scene
     */
    public void render() {
        this.camera.update(Gdx.graphics.getDeltaTime());
        this.sceneGraph.draw();
    }

    /**
     * Dispose the scene
     */
    public void dispose() {
        this.sceneGraph.dispose();
    }

}