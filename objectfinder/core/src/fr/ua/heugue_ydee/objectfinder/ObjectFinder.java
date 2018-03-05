package fr.ua.heugue_ydee.objectfinder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.junit.Test;

public class ObjectFinder extends ApplicationAdapter {
	/*SpriteBatch batch;
	Texture img;*/
	TestColoredTerrainScene testColoredTerrainScene;
	
	@Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/
		this.testColoredTerrainScene = new TestColoredTerrainScene();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
		this.testColoredTerrainScene.render();
	}
	
	@Override
	public void dispose () {
		this.testColoredTerrainScene.dispose();
		/*batch.dispose();
		img.dispose();*/
	}
}
