package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * An abstract representation of what is terrain
 */
public abstract class Terrain extends Actor implements Disposable{

    private int width;
    private int height;

    /**
     * Build a terrain using its width and its height
     *
     * @param width The width of the terrain
     * @param height The height of the terrain
     */
    public Terrain(int width, int height) {
        super();
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The height and the width must be strictly positive");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Getter for the width of the terrain
     *
     * @return The width of the terrain
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Getter for the height of the terrain
     *
     * @return The height of the terrain
     */
    public float getHeight() {
        return this.height;
    }

}