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
     * @return A colored terrain
     */
    public Terrain createColoredTerrain(int width, int height, Color color, ClickObservable clickObservable) {
        return new ColoredTerrain(width, height, color, clickObservable);
    }

}