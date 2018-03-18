package fr.ua.heugue_ydee.objectfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import model.Score;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button testButton = findViewById(R.id.jouerButton);
        testButton.setOnClickListener(this);
        Button highscoreButton = findViewById(R.id.highScoreButton);
        highscoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jouerButton:
                Intent intentTest = new Intent(this, ActivityPreGame.class);
                startActivity(intentTest);
                break;

            case R.id.highScoreButton:
                Intent intentHighscore = new Intent(this, TableauRecords.class);
                intentHighscore.putExtra("LISTE_RECORDS",new ArrayList<Score>());
                startActivity(intentHighscore);
                break;
        }
    }
}
