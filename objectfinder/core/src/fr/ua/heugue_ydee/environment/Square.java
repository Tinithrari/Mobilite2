package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.ua.heugue_ydee.utils.ClickObserver;

/**
 * 
 */
public class Square implements ClickObserver, DestroyableObservable {

    private Color color;
    private Rectangle collider;

    /**
     * @param position 
     * @param width 
     * @param height 
     * @param color
     */
    public void Square(Vector2 position, int width, int height, Color color) {
        // TODO implement here
    }

    /**
     * @param delta 
     * @return
     */
    public void update(float delta) {
    }

    /**
     * @param position
     */
    public void notifyClick(Vector2 position) {
        // TODO implement here
    }

    /**
     * @param observer
     */
    public void addDestroyableObserver(DestroyableObserver observer) {
        // TODO implement here
    }

    /**
     * @param observer
     */
    public void removeDestroyableObserver(DestroyableObserver observer) {
        // TODO implement here
    }

    /**
     * 
     */
    public void notifyDestroyableObservers() {
        // TODO implement here
    }

}