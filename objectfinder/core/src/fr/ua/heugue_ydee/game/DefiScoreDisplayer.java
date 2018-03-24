package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Display the score for the defi mode
 */
public class DefiScoreDisplayer extends ScoreDisplayerStrategy {

    private DefiScoreCounter counter;
    private GlyphLayout scoreLayout;
    private GlyphLayout timeLayout;

    /**
     * Build a defi score displayer
     *
     * @param counter the counter for this displayer
     */
    public DefiScoreDisplayer(DefiScoreCounter counter) {
        this.counter = counter;
        this.scoreLayout = new GlyphLayout();
        this.timeLayout = new GlyphLayout();
    }

    /**
     * Get the score counter
     *
     * @return the score counter of this strategy
     */
    @Override
    public ScoreCounterStrategy getScoreCounter() {
        return this.counter;
    }

    @Override
    public void draw() {
        super.draw();
        this.batch.begin();
        this.scoreLayout.setText(this.font, "Score: " + this.counter.getScore());
        this.timeLayout.setText(this.font, "Time: " + this.counter.getTimeCountStrategy().getTime().toString());
        this.font.draw(batch, this.scoreLayout, 0, Gdx.graphics.getHeight() - (this.banner.getHeight() / 2)
                + (this.timeLayout.height / 2));
        this.font.draw(batch, this.timeLayout, Gdx.graphics.getWidth() - this.timeLayout.width, Gdx.graphics.getHeight() - (this.banner.getHeight() / 2)
                + (this.timeLayout.height / 2));
        this.batch.end();
    }
}
