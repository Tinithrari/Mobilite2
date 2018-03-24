package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Interface pour le dessin
 */
public interface DrawableAdaptable {
    /**
     * Update between two frame
     *
     * @param delta The time between two frame
     */
    void update(float delta);

    /**
     * Render the drawable
     *
     * @param position  The position where the drawable will drawn
     * @param batch The renderer
     * @param parentAlpha The alpha of the parent
     */
    void render(Vector2 position, Batch batch, float parentAlpha);

    /**
     * Set the width
     *
     * @param width The width of the object
     */
    void setWidth(float width);

    /**
     * Set the height
     *
     * @param height The height of the object
     */
    void setHeight(float height);
}
