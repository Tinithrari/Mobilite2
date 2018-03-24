package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.LinkedList;
import java.util.List;

import fr.ua.heugue_ydee.animation.AnimationStore;
import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.utils.ClickObserver;
import fr.ua.heugue_ydee.utils.ResourceLoader;

/**
 * Created by xavie on 24/03/2018.
 */

public class Egg extends Actor implements DestroyableObservable, ClickObserver{

    private List<DestroyableObserver> observers;
    private Animator animator;
    private Vector2 position;
    private Vector2 size;
    private boolean touched;

    /**
     * Create an egg
     *
     * @param position The position of the egg
     * @param size The size of the egg
     */
    public Egg(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
        this.animator = new Animator(125, false, AnimationStore.get().getAnimation(ResourceLoader.EGG_ANIMATION));
        this.touched = false;
        this.observers = new LinkedList<DestroyableObserver>();
        this.animator.setWidth(size.x / 2);
        this.animator.setHeight(size.y);
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
            obs.notifyDestroyableObserver(this);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color c = batch.getColor();
        if (touched) {
            animator.update(Gdx.graphics.getDeltaTime() * 1000);
            if (animator.isFinished()) {
                notifyDestroyableObservers();
            }
        }

        batch.setColor(c);
        animator.render(position, batch, parentAlpha);
    }

    @Override
    public void notifyClick(Vector2 position) {
        Rectangle collider = new Rectangle(position.x, position.y, size.x, size.y);
        if (collider.contains(position)) {
            this.touched = true;
        }
    }
}
