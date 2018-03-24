package fr.ua.heugue_ydee.objectfinder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

<<<<<<< HEAD
public class ObjectFinder extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
=======
import org.junit.Test;

import fr.ua.heugue_ydee.game.GameBuilder;
import fr.ua.heugue_ydee.game.GameScene;
import fr.ua.heugue_ydee.utils.DBAdapter;
import fr.ua.heugue_ydee.utils.ResourceLoader;

public class ObjectFinder extends ApplicationAdapter {
	/*SpriteBatch batch;
	Texture img;*/
	// private TestColoredTerrainScene testColoredTerrainScene;
    private GameScene gameScene;
	private int difficulty;
	private int mode;
	private DBAdapter adapter;

    public ObjectFinder(int difficulty, int mode, DBAdapter adapter) {
        super();
        this.difficulty = difficulty;
        this.mode = mode;
        this.adapter = adapter;
    }

    @Override
	public void create () {
		ResourceLoader.load();
        GameBuilder builder = new GameBuilder(difficulty, mode, adapter);
        this.gameScene = builder.buildGame();
>>>>>>> develop
	}

	@Override
	public void render () {
<<<<<<< HEAD
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
=======
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.gameScene.render();
>>>>>>> develop
	}
	
	@Override
	public void dispose () {
<<<<<<< HEAD
		batch.dispose();
		img.dispose();
=======
		this.gameScene.dispose();
>>>>>>> develop
	}
}
