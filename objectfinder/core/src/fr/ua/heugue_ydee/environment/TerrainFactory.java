package fr.ua.heugue_ydee.environment;

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
    public Terrain createColoredTerrain(int width, int height, com.badlogic.gdx.graphics.Color color) {
        return new ColoredTerrain(width, height, color);
    }

}