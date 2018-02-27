package fr.ua.heugue_ydee.environment;


import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * A monochrome terrain
 */
public class ColoredTerrain extends Terrain {

    private Texture terrain;

    /**
     * Create a monochrome terrain
     *
     * @param width The width of the terrain
     * @param height The height of the terrain
     * @param color The color of the terrain
     */
    public ColoredTerrain(int width, int height, com.badlogic.gdx.graphics.Color color) {
        super(width, height);

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        this.terrain = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(terrain, 0, 0);
    }

    @Override
    public void dispose() {
        this.terrain.dispose();
    }
}