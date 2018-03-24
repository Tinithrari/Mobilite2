package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.game.DrawableAdaptable;

/**
 * Hazard that can happen in the easy mode
 */
public class EasyHazard {
    private DrawableAdaptable[][] grid;
    protected int tile_width;
    protected int tile_height;
    protected int maxWidth;
    protected int maxHeight;

    private Sheep[] sheeps;
    private static final int NB_SHEEP = 10;

    /**
     * Hazard which can happen in the easy mode
     *
     * @param grid The map grid
     * @param tile_width The tile width
     * @param tile_height The tile height
     */
    public EasyHazard(DrawableAdaptable[][] grid, int tile_width, int tile_height,
                      int maxWidth, int maxHeight) {
        this.grid = grid;
        this.tile_width = tile_width;
        this.tile_height = tile_height;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        this.sheeps = new Sheep[NB_SHEEP];

        for (int i = 0; i < NB_SHEEP; ++i) {
            int x = -1;
            int y = -1;

            Random gen = new Random();

            while ((x == -1 && y == -1) || (grid[x][y] instanceof Animator)) {
                x = gen.nextInt(grid.length);
                y = gen.nextInt(grid[0].length);
            }

            this.sheeps[i] = new Sheep(new Vector2(x * tile_width, y * tile_height), 0.001f, new Vector2(tile_width, tile_height));
        }
    }

    /**
     * Render the hazards
     *
     * @param batch The draw interface
     * @param parentAlpha The alpha value of the parent
     */
    public void render(Batch batch, float parentAlpha) {
        for (Sheep s : sheeps) {
            Vector2 position = new Vector2(s.getPosition());

            s.update();

            if (s.getPosition().x < 0 || s.getPosition().x >= maxWidth
                    || s.getPosition().y < 0 || s.getPosition().y >= maxHeight) {
                s.setPosition(position);
            } else {
                int x = (int) (s.getPosition().x / tile_width);
                int y = (int) (s.getPosition().y / tile_height);

                Rectangle zone = new Rectangle(x * tile_width, y * tile_height, tile_width, tile_height);

                if (zone.contains(s.getPosition()) &&
                        grid[x][y] instanceof Animator) {
                    s.setPosition(position);
                }

                x = (int) ((s.getPosition().x + tile_width) / tile_width);
                y = (int) ((s.getPosition().y + tile_height) / tile_height);

                zone = new Rectangle(x * tile_width, y * tile_height, tile_width, tile_height);

                if (x < grid.length && y < grid[0].length) {
                    if (zone.contains(new Vector2(s.getPosition().x + tile_width, s.getPosition().y + tile_height)) &&
                            grid[x][y] instanceof Animator) {
                        s.setPosition(position);
                    }
                }
            }
            s.draw(batch, parentAlpha);
        }
    }
}
