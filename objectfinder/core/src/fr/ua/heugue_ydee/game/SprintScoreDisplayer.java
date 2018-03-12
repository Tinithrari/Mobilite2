package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Strategy to display the score for the sprint mode
 */
public class SprintScoreDisplayer extends ScoreDisplayerStrategy {

    private SprintScoreCounter counter;
    private GlyphLayout timeLayout;

    /**
     * Build a sprint score displayer
     *
     * @param counter The Score strategy to display
     */
    public SprintScoreDisplayer(SprintScoreCounter counter) {
        super();
        this.counter = counter;
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
        this.timeLayout.setText(font, "Time: " + this.counter.getTimeCountStrategy().getTime().toString());
        font.draw(batch, this.timeLayout, Gdx.graphics.getWidth() - this.timeLayout.width, 0);
    }
}
