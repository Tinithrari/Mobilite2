package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.ua.heugue_ydee.animation.AnimationStore;
import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.game.DrawableAdaptable;
import fr.ua.heugue_ydee.utils.ResourceLoader;

/**
 * Created by xavie on 24/03/2018.
 */

public class HardHazard extends NormalHazard {

    private List<Animator> rains;
    private List<Vector2> positions;
    private float delta;
    private int deltaChange;

    private static int MAX_DELTA = 50;

    /**
     * Hazard which can happen in the easy mode
     *
     * @param grid        The map grid
     * @param tile_width  The tile width
     * @param tile_height The tile height
     * @param maxWidth
     * @param maxHeight
     */
    public HardHazard(DrawableAdaptable[][] grid, int tile_width, int tile_height, int maxWidth, int maxHeight) {
        super(grid, tile_width, tile_height, maxWidth, maxHeight);
        this.rains = new LinkedList<Animator>();
        this.positions = new LinkedList<Vector2>();
        this.delta = 0;
        this.deltaChange = 0;
    }

    private void updateRain() {
        delta += Gdx.graphics.getDeltaTime() * 1000;
        for (int i = 0; i < rains.size(); ++i) {
            if (rains.get(i).isFinished()) {
                positions.remove(i);
                rains.remove(i);
                --i;
            }
        }

        if (delta >= deltaChange) {
            Random gen = new Random();
            this.delta = 0;
            this.deltaChange = gen.nextInt(MAX_DELTA);
            Animator a = new Animator(83.3f, false, AnimationStore.get().getAnimation(ResourceLoader.RAIN_ANIMATION));
            a.setWidth(tile_width * 2);
            a.setHeight(tile_height * 2);
            this.rains.add(a);
            this.positions.add(new Vector2(gen.nextInt(maxWidth), gen.nextInt(maxHeight)));
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
        updateRain();

        for (Animator a : rains) {
            a.update(Gdx.graphics.getDeltaTime() * 1000);
            a.render(positions.get(rains.indexOf(a)), batch, parentAlpha);
        }
    }
}
