package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import fr.ua.heugue_ydee.game.DrawableAdaptable;

/**
 * Created by xavie on 24/03/2018.
 */

public class NormalHazard extends EasyHazard {

    private Duck[] ducks;
    private static final int NB_DUCKS = 10;
    /**
     * Hazard which can happen in the easy mode
     *
     * @param grid        The map grid
     * @param tile_width  The tile width
     * @param tile_height The tile height
     * @param maxWidth
     * @param maxHeight
     */
    public NormalHazard(DrawableAdaptable[][] grid, int tile_width, int tile_height, int maxWidth, int maxHeight) {
        super(grid, tile_width, tile_height, maxWidth, maxHeight);
        this.ducks = new Duck[NB_DUCKS];
        for (int i = 0; i < NB_DUCKS; ++i) {
            int x;
            int y;

            Random gen = new Random();
            x = gen.nextInt(grid.length);
            y = gen.nextInt(grid[0].length);

            this.ducks[i] = new Duck(new Vector2(x * tile_width, y * tile_height), 0.002f, new Vector2(tile_width, tile_height));
        }
    }

    /**
     * Render the hazards
     *
     * @param batch       The draw interface
     * @param parentAlpha The alpha value of the parent
     */
    @Override
    public void render(Batch batch, float parentAlpha) {
        super.render(batch, parentAlpha);
        for (Duck d : ducks) {
            Vector2 position = new Vector2(d.getPosition());

            d.update();

            if (d.getPosition().x < 0 || d.getPosition().x > maxWidth - 1
                    || d.getPosition().y < 0 || d.getPosition().y > maxHeight - 1) {
                d.setPosition(position);
            }
            d.draw(batch, parentAlpha);
        }
    }
}
