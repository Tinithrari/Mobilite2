package fr.ua.heugue_ydee.environment;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.ua.heugue_ydee.animation.Animation;
import fr.ua.heugue_ydee.animation.AnimationStore;
import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.utils.ClickObservable;
import fr.ua.heugue_ydee.utils.ResourceLoader;

/**
 * A monochrome terrain
 */
public class ColoredTerrain extends Terrain implements DestroyableObserver {

    private Texture terrain;
    private DrawableRectangle rectangle;
    private ClickObservable clickEventManager;
    private List<DestroyableObserver> observers;
    private Animator animator;

    private static final int REDUCTION_FACTOR = 40;
    private static final float DUCK_FRAMERATE = 83.3f;

    /**
     * Create a monochrome terrain
     *
     * @param width The width of the terrain
     * @param height The height of the terrain
     * @param color The color of the terrain
     */
    public ColoredTerrain(int width, int height, Color color, ClickObservable clickEventManager) {
        super(width, height);

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        this.terrain = new Texture(pixmap);
        pixmap.dispose();
        this.clickEventManager = clickEventManager;
        this.generateNewSquare();
        this.observers = new ArrayList<DestroyableObserver>();
        this.animator = new Animator(DUCK_FRAMERATE, true, AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_RIGHT));
        this.animator.setWidth(512);
        this.animator.setHeight(512);
    }

    private void generateNewSquare() {
        Random gen = new Random();

        int width = gen.nextInt((int)this.getWidth() / REDUCTION_FACTOR) + ((int)this.getWidth() / REDUCTION_FACTOR);
        int height = gen.nextInt((int)this.getHeight() / REDUCTION_FACTOR) + ((int)this.getHeight() / REDUCTION_FACTOR);

        Vector2 position = new Vector2(gen.nextInt((int)this.getWidth()-width),
                gen.nextInt((int)this.getHeight() - height));
        this.rectangle = new DrawableRectangle(position, width, height, Color.BLACK);
        this.rectangle.addDestroyableObserver(this);
        this.clickEventManager.addClickObserver(this.rectangle);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.rectangle.update(Gdx.graphics.getDeltaTime());
        this.animator.update(Gdx.graphics.getDeltaTime() * 1000);
        batch.draw(terrain, 0, 0);
        this.rectangle.draw(batch, parentAlpha);
        this.animator.render(new Vector2(0, 0), batch, parentAlpha);
    }

    @Override
    public void dispose() {
        this.terrain.dispose();
    }

    /**
     * Notify this object of the destruction of an object
     *
     * @param obs
     */
    @Override
    public void notifyDestroyableObserver(DestroyableObservable obs) {
        this.rectangle.removeDestroyableObserver(this);
        this.clickEventManager.removeClickObserver(this.rectangle);
        notifyDestroyableObservers();
        generateNewSquare();
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
        for (DestroyableObserver obs : observers) {
            obs.notifyDestroyableObserver(this.rectangle);
        }
    }
}