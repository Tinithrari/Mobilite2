package fr.ua.heugue_ydee.objectfinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.ua.heugue_ydee.environment.Terrain;
import fr.ua.heugue_ydee.environment.TerrainFactory;

/**
 * Created by xavierheugue on 27/02/2018.
 */

public class TestColoredTerrainScene {
    private Stage sceneGraph;
    private static final int coefficient = 4;
    private static final int worldWidth = Gdx.graphics.getWidth() / TestColoredTerrainScene.coefficient;
    private static final int worldHeight = Gdx.graphics.getHeight() / TestColoredTerrainScene.coefficient;

    public TestColoredTerrainScene() {
        this.sceneGraph = new Stage(new ScreenViewport());
        TerrainFactory factory = new TerrainFactory();
        Terrain t = factory.createColoredTerrain(worldWidth, worldHeight, Color.BLUE);
        this.sceneGraph.addActor(t);
    }

    public void render() {
        this.sceneGraph.draw();
    }

    public void dispose() {
        this.sceneGraph.dispose();
    }
}