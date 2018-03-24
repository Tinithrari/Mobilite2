package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.Color;

import fr.ua.heugue_ydee.utils.ClickObservable;

/**
 * Terrain factory
 */
public final class TerrainFactory {

    /**
     * Create a colored terrain
     *
     * @param width The width of the terrain
     * @param height The height of the terrain
     * @param color The color of the terrain
     * @param clickObservable The click event source
     * @return A colored terrain
     */
    public Terrain createColoredTerrain(int width, int height, Color color, ClickObservable clickObservable) {
        return new ColoredTerrain(width, height, color, clickObservable);
    }

    /**
     * Create a tiled terrain
     *
     * @param width The width of the terrain
     * @param height The height of the terrain
     * @param clickObservable The click event source
     * @param difficulty The difficulty of the level
     * @return A tiled terrain
     */
    public Terrain createTiledTerrain(int width, int height, ClickObservable clickObservable, int difficulty) {
        return new TiledTerrain(width, height, clickObservable, difficulty);
    }
}