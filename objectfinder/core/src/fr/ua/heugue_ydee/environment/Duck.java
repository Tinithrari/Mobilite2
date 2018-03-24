package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import fr.ua.heugue_ydee.animation.AnimationStore;
import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.physics.ParticlePhysics;
import fr.ua.heugue_ydee.utils.ResourceLoader;

/**
 * Created by xavie on 24/03/2018.
 */

public class Duck extends ParticlePhysics{
    private Animator animator;

    private static final Vector2[] gradient = {new Vector2(0, 1f), new Vector2(1f, 0),
            new Vector2(0, -1f), new Vector2(-1, 0)};

    private static final int MAX_DELTA = 2000;
    private Vector2 direction;
    private float delta;
    private int deltaChange;
    private Rectangle collider;

    private void changeDir() {
        this.delta = 0;
        Random gen = new Random();
        this.deltaChange = gen.nextInt(MAX_DELTA);

        this.direction = gradient[gen.nextInt(gradient.length)];

        if (direction.x == -1)
            this.animator.setAnimation(AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_LEFT));
        else if (direction.x == 1)
            this.animator.setAnimation(AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_RIGHT));
        else if (direction.y == -1)
            this.animator.setAnimation(AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_DOWN));
        else
            this.animator.setAnimation(AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_UP));
    }

    /**
     * Initialize an animal
     *
     * @param position Position of the particle
     * @param mass     Mass of the particle
     * @param size     The size of the tile
     */
    public Duck(Vector2 position, float mass, Vector2 size) {
        super(position, mass);
        animator = new Animator(83.3f, true, AnimationStore.get().getAnimation(ResourceLoader.WHITE_DUCK_DOWN));
        changeDir();
        animator.setWidth(size.x / 2);
        animator.setHeight(size.y);
        this.collider = new Rectangle(getPosition().x, getPosition().y, size.x / 2, size.y);
    }

    /**
     * Update the animal
     */
    public void update() {
        delta += Gdx.graphics.getDeltaTime() * 1000;
        if (delta >= deltaChange)
            changeDir();
        addForce(direction);
        update(Gdx.graphics.getDeltaTime());
        animator.update( Gdx.graphics.getDeltaTime() * 1000);
        this.collider.x = getPosition().x;
        this.collider.y = getPosition().y;
    }

    /**
     * Return the collider of the animal
     *
     * @return The collider of the animal
     */
    public Rectangle getCollider() {
        return collider;
    }

    /**
     * Draw the animal on the screen
     *
     * @param batch The draw interface
     * @param parentAlpha The alpha value of the parent
     */
    public void draw(Batch batch, float parentAlpha) {
        this.animator.render(getPosition(), batch, parentAlpha);
    }
}
