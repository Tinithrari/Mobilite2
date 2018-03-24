package fr.ua.heugue_ydee.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.ua.heugue_ydee.game.DrawableAdaptable;

/**
 * Class to display an animation
 */
public class Animator extends Actor implements DrawableAdaptable {
    private float framerate;
    private boolean looped;
    private Animation animation;
    private int index;
    private float elapsed;

    /**
     * Build an animator
     *
     * @param framerate The framerate to play the animation
     * @param looped Is the animation looped?
     * @param animation The animation to play
     */
    public Animator(float framerate, boolean looped, Animation animation) {
        this.framerate = framerate;
        this.looped = looped;
        this.animation = animation;
        this.index = 0;
        this.elapsed = 0;
    }

    /**
     * Change the current animation and reset the frame number
     *
     * @param animation The new animation to use
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.index = 0;
        this.elapsed = 0;
    }

    /**
     * Is the animation looped?
     *
     * @return true if the animation is looped, false otherwise
     */
    public boolean isLooped() {
        return looped;
    }

    /**
     * Set if the animation should be looped
     *
     * @param looped true if the animation should be looped, false otherwise
     */
    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    /**
     * Update the animator state
     *
     * @param delta The time elapsed between two frames
     */
    public void update(float delta) {
        this.elapsed += delta;
        float totalDuration = framerate * animation.size();

        if (elapsed >= totalDuration && looped) {
            this.elapsed = elapsed - totalDuration;
        }

        if (totalDuration > elapsed) {
            this.index = (int) (elapsed / framerate);
        }
    }

    /**
     * Render the animation on the screen
     *
     * @param position The position where to draw the animation
     * @param batch The renderer
     * @param alphaParent The alpha parent
     */
    public void render(Vector2 position, Batch batch, float alphaParent) {
        super.draw(batch, alphaParent);
        TextureRegion frame = animation.getFrame(index);
        batch.draw(frame, position.x, position.y, getWidth(), getHeight());
    }

    /**
     * Return the animation state
     *
     * @return true if the animation is finished, false otherwise
     */
    public boolean isFinished() {
        return framerate * animation.size() < elapsed;
    }
}
