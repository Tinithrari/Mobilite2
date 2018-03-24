package fr.ua.heugue_ydee.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.ua.heugue_ydee.animation.Animation;
import fr.ua.heugue_ydee.animation.AnimationStore;
import fr.ua.heugue_ydee.animation.Animator;
import fr.ua.heugue_ydee.game.DrawableAdaptable;
import fr.ua.heugue_ydee.model.Score;
import fr.ua.heugue_ydee.pcg.ErosionAutomata;
import fr.ua.heugue_ydee.utils.ClickObservable;
import fr.ua.heugue_ydee.utils.ResourceLoader;
import fr.ua.heugue_ydee.utils.TextureStore;

/**
 * A terrain composed of tile
 */
public class TiledTerrain extends Terrain {

    private  ClickObservable clickEventManager;
    private DrawableAdaptable[][] grid;
    private Egg egg;
    private EasyHazard hazard;
    private List<DestroyableObserver> observers;
    public static final int TILE_WIDTH = 192;
    public static final int TILE_HEIGHT = 108;

    private void generateGrid() {
        int widthGrid = (int) (getWidth() / TILE_WIDTH);
        int heightGrid = (int) (getHeight() / TILE_HEIGHT);

        ErosionAutomata automata = new ErosionAutomata(widthGrid, heightGrid);
        List<List<Integer>> map = automata.simulate(5);

        grid = new DrawableAdaptable[widthGrid][heightGrid];

        for (int i = 0; i < widthGrid; ++i) {
            for (int j = 0; j < heightGrid; ++j) {
                DrawableAdaptable adaptable;
                switch (map.get(i).get(j)) {
                    case 0:
                        adaptable = new Tile(TextureStore.get().getTexture(ResourceLoader.GRASS_TEXTURE));
                        break;
                    case 1:
                        adaptable = new Tile(TextureStore.get().getTexture(ResourceLoader.ROCKS_TEXTURE));
                        break;
                    default:
                        adaptable = new Animator(100, true, AnimationStore.get().getAnimation(ResourceLoader.WATER_ANIMATION));
                }

                this.grid[i][j] = adaptable;
                this.grid[i][j].setWidth(TILE_WIDTH);
                this.grid[i][j].setHeight(TILE_HEIGHT);
            }
        }
    }

    private void generateNewEgg() {
        int widthGrid = (int) (getWidth() / TILE_WIDTH);
        int heightGrid = (int) (getHeight() / TILE_HEIGHT);

        int x = -1;
        int y = -1;

        Random gen = new Random();

        while ((x == -1 && y == -1) || (grid[x][y] instanceof Animator)) {
            x = gen.nextInt(widthGrid);
            y = gen.nextInt(heightGrid);
        }

        this.egg = new Egg(new Vector2(x * TILE_WIDTH, y * TILE_HEIGHT), new Vector2(TILE_WIDTH, TILE_HEIGHT));
        this.egg.addDestroyableObserver(this);
        this.clickEventManager.addClickObserver(egg);
        this.egg.setWidth(TILE_WIDTH);
        this.egg.setHeight(TILE_HEIGHT);
    }

    private void generateHazards(int difficulty) {
        if (difficulty == 1)
            this.hazard = new EasyHazard(grid, TILE_WIDTH, TILE_HEIGHT, (int)getWidth() - TILE_WIDTH, (int)getHeight() - TILE_HEIGHT);
        if (difficulty == 2)
            this.hazard = new NormalHazard(grid, TILE_WIDTH, TILE_HEIGHT, (int)getWidth() - TILE_WIDTH, (int)getHeight() - TILE_HEIGHT);
        if (difficulty == 3)
            this.hazard = new HardHazard(grid, TILE_WIDTH, TILE_HEIGHT, (int)getWidth() - TILE_WIDTH, (int)getHeight() - TILE_HEIGHT);
    }

    /**
     * Build a terrain using its width and its height
     *
     * @param width  The width of the terrain
     * @param height The height of the terrain
     * @param difficulty The difficulty of the level
     */
    public TiledTerrain(int width, int height, ClickObservable clickEventManager, int difficulty) {
        super(width, height);
        this.clickEventManager = clickEventManager;
        this.observers = new LinkedList<DestroyableObserver>();

        generateGrid();
        generateNewEgg();
        generateHazards(difficulty);
    }

    @Override
    public void dispose() {

    }

    /**
     * Notify this object of the destruction of an object
     *
     * @param obs The observable which fire the event
     */
    @Override
    public void notifyDestroyableObserver(DestroyableObservable obs) {
        this.egg.removeDestroyableObserver(this);
        this.clickEventManager.removeClickObserver(this.egg);
        notifyDestroyableObservers();
        generateNewEgg();
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
            obs.notifyDestroyableObserver(this.egg);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        int widthGrid = (int) (getWidth() / TILE_WIDTH);
        int heightGrid = (int) (getHeight() / TILE_HEIGHT);

        for (int i = 0; i < widthGrid; ++i) {
            for (int j = 0; j < heightGrid; ++j) {
                grid[i][j].update(Gdx.graphics.getDeltaTime() * 1000);
                grid[i][j].render(new Vector2(i * TILE_WIDTH, j * TILE_HEIGHT), batch, parentAlpha);
            }
        }
        this.egg.draw(batch, parentAlpha);
        this.hazard.render(batch, parentAlpha);
    }
}
