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
		Intent intent = getIntent();
		int difficulty = intent.getIntExtra(ActivityPreGame.DIFFICULTY, 0);
		int mode = intent.getIntExtra(ActivityPreGame.GAMEMODE, 0);
		System.out.println(difficulty);
		System.out.println(mode);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ObjectFinder(difficulty, mode, new DBAdapter(new HighScoreDBHelper(this))), config);
	}
}
