package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Element that permits to display score on the screen with a readable manner
 */
public abstract class ScoreDisplayerStrategy {

    private Texture banner;
    private static final int HEIGHT_RATIO = 10;
    private static final float ALPHA_VALUE = 0.2f;
    protected SpriteBatch batch;
    protected BitmapFont font;

    /**
     * Build the base for score display
     */
    public ScoreDisplayerStrategy() {
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / HEIGHT_RATIO, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, ALPHA_VALUE);
        pixmap.fill();
        this.banner = new Texture(pixmap);
        pixmap.dispose();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    /**
     * Draw the score on the screen
     */
    public void draw() {
        this.batch.begin();
        this.batch.draw(banner, 0, 0);
        this.batch.end();
    }

    /**
     * Dispose the strategy
     */
    public void dispose() {
        this.batch.dispose();
    }

    /**
     * Get the score counter
     *
     * @return the score counter of this strategy
     */
    public abstract ScoreCounterStrategy getScoreCounter();
}
