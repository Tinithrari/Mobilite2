package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.awt.Font;

/**
 * Element that permits to display score on the screen with a readable manner
 */
public abstract class ScoreDisplayerStrategy {

    protected Texture banner;
    private static final int HEIGHT_RATIO = 10;
    private static final float ALPHA_VALUE = 0.7f;
    private static final int FONT_SIZE = 64;
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
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FONT_SIZE;
        this.font = generator.generateFont(parameter);
    }

    /**
     * Draw the score on the screen
     */
    public void draw() {
        this.batch.begin();
        this.batch.draw(banner, 0, Gdx.graphics.getHeight() - banner.getHeight());
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
