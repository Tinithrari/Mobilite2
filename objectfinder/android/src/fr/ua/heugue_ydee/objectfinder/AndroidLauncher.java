package fr.ua.heugue_ydee.objectfinder;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import fr.ua.heugue_ydee.model.HighScoreDBHelper;
import fr.ua.heugue_ydee.utils.DBAdapter;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int difficulty = savedInstanceState.getInt(ActivityPreGame.DIFFICULTY);
		int mode = savedInstanceState.getInt(ActivityPreGame.GAMEMODE);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ObjectFinder(difficulty, mode, new DBAdapter(new HighScoreDBHelper(this))), config);
	}
}
