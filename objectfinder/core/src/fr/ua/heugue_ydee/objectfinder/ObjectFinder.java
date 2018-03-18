package fr.ua.heugue_ydee.objectfinder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.junit.Test;

import fr.ua.heugue_ydee.game.GameBuilder;
import fr.ua.heugue_ydee.game.GameScene;
import fr.ua.heugue_ydee.utils.DBAdapter;

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
        GameBuilder builder = new GameBuilder(difficulty, mode, adapter);
        this.gameScene = builder.buildGame();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.gameScene.render();
	}
	
	@Override
	public void dispose () {
		this.gameScene.dispose();
	}
}
