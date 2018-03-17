package fr.ua.heugue_ydee.objectfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 *
 */
public class ActivityPreGame extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final String DIFFICULTY = "Difficulte";
    public static final String GAMEMODE = "Game_mode";

    private int difficulty;
    private int modeJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        this.difficulty = 0;
        this.modeJeu = 0;

        Spinner gameModeSpinner = findViewById(R.id.gamemode_spinner);
        ArrayAdapter<CharSequence> adapterGameMode = ArrayAdapter.createFromResource(this,
                R.array.gamemode_array, android.R.layout.simple_spinner_item);

        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gameModeSpinner.setAdapter(adapterGameMode);
        gameModeSpinner.setOnItemSelectedListener(this);

        Spinner difficultyModeSpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);

        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        difficultyModeSpinner.setAdapter(adapterDifficulty);
        difficultyModeSpinner.setOnItemSelectedListener(this);

        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startGameButton:
                Intent intentTest = new Intent(this, AndroidLauncher.class);
                intentTest.putExtra(DIFFICULTY,this.difficulty);
                intentTest.putExtra(GAMEMODE,this.modeJeu);
                startActivity(intentTest);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.difficulty_spinner :
                this.difficulty = position;
                break;
            case R.id.gamemode_spinner :
                this.modeJeu = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
