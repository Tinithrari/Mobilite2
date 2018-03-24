package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.ua.heugue_ydee.game.DrawableAdaptable;

/**
 * Drawable Tile
 */
public class Tile extends Actor implements DrawableAdaptable {
    private Texture texture;

    /**
     * Build a tile using its texture
     *
     * @param texture The texture to render
     */
    public Tile(Texture texture) {
        this.texture = texture;
    }

    /**
     * Update between two frame
     *
     * @param delta The time between two frame
     */
    @Override
    public void update(float delta) {

    }

    /**
     * Render the drawable
     *
     * @param position    The position where the drawable will drawn
     * @param batch       The renderer
     * @param parentAlpha The alpha of the parent
     */
    @Override
    public void render(Vector2 position, Batch batch, float parentAlpha) {
        batch.draw(texture, position.x, position.y, getWidth(), getHeight());
    }
}
