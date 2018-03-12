package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.LinkedList;
import java.util.List;

import fr.ua.heugue_ydee.utils.ClickObserver;

/**
 * A rectangle which can be drawn on the screen, with its collider and an animation on touch
 */
public class DrawableRectangle extends Actor implements ClickObserver, DestroyableObservable {
    private Color color;
    private Rectangle collider;
    private boolean touched;
    private List<DestroyableObserver> observers;
    private float blinkTransitionTime;
    private boolean toAlpha;
    private int blinkNumber;

    private static final float BLINK_TIME = 0.0625f;
    private static final int MAX_BLINK = 6;

    /**
     * Build a rectangle using its position, dimension and color
     *
     * @param position Position of the square
     * @param width Width of the square
     * @param height Height of the square
     * @param color Color of the square
     */
    public DrawableRectangle(Vector2 position, int width, int height, Color color) {
        this.collider = new Rectangle(position.x, position.y, width, height);
        this.color = color;
        this.touched = false;
        this.toAlpha = false;
        this.observers = new LinkedList<DestroyableObserver>();
        this.blinkTransitionTime = 0;
        this.blinkNumber = 0;
    }

    /**
     * Update the rectangle over time
     *
     * @param delta the delta time between two frame
     */
    public void update(float delta) {
        if (touched) {
            this.blinkTransitionTime += delta;
            if (this.blinkTransitionTime >= DrawableRectangle.BLINK_TIME) {
                this.blinkTransitionTime -= DrawableRectangle.BLINK_TIME;
                this.blinkNumber++;
                this.toAlpha = ! this.toAlpha;
                if (this.blinkNumber >= MAX_BLINK) {
                    this.notifyDestroyableObservers();
                }
            }
        }
    }

    /**
     * Add an observer to the list of item to notify
     *
     * @param observer The observer to add to the list
     */
    @Override
    public void addDestroyableObserver(DestroyableObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from the list of item to notify
     *
     * @param observer The observer to remove from the list
     */
    @Override
    public void removeDestroyableObserver(DestroyableObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify all object of the destruction of the current object
     */
    @Override
    public void notifyDestroyableObservers() {
        for (DestroyableObserver obs: observers) {
            obs.notifyDestroyableObserver(this);
        }
    }

    /**
     * Permit your object to be notified for a click event
     *
     * @param position The position of the click
     */
    @Override
    public void notifyClick(Vector2 position) {
        if (collider.contains(position)) {
            this.touched = true;
            this.toAlpha = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color c = new Color(this.color);
        if (touched) {
            if (this.toAlpha) {
                c.a = 1 - (this.blinkTransitionTime/DrawableRectangle.BLINK_TIME);
            } else {
                c.a = (this.blinkTransitionTime/DrawableRectangle.BLINK_TIME);
            }
        }
        Pixmap pixmap = new Pixmap((int)collider.width, (int)collider.height, Pixmap.Format.RGBA8888);
        pixmap.setColor(c);
        pixmap.fillRectangle(0, 0, (int)collider.width, (int)collider.height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, this.collider.x, this.collider.y);
    }
}