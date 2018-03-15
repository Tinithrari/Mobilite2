package fr.ua.heugue_ydee.objectfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fr.ua.heugue_ydee.utils.Time;
import model.DefiScore;
import model.Score;
import model.SprintScore;

/**
 * Activite prenant en charge la gestion du tableau des records.
 */
public class TableauRecords extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private int difficulty;
    private int modeJeu;

    private List<Score> listeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_records);

        this.difficulty = 0;
        this.modeJeu = 0;

        Spinner gameModeSpinner = findViewById(R.id.spinnerMode);
        ArrayAdapter<CharSequence> adapterGameMode = ArrayAdapter.createFromResource(this,
                R.array.gamemode_array, android.R.layout.simple_spinner_item);

        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gameModeSpinner.setAdapter(adapterGameMode);
        gameModeSpinner.setOnItemSelectedListener(this);

        Spinner difficultyModeSpinner = findViewById(R.id.spinnerDifficulte);
        ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);

        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        difficultyModeSpinner.setAdapter(adapterDifficulty);
        difficultyModeSpinner.setOnItemSelectedListener(this);

        //Création et initialisation de l'Adapter pour les scores
        ScoreAdapter adapter = new ScoreAdapter(this, listeScore);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.listeRecords);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.spinnerDifficulte :
                this.difficulty = position;
                break;
            case R.id.spinnerMode :
                this.modeJeu = position;
                listeScore = new ArrayList<>();

                if(position == 0) {
                    SprintScore sprintScore1 = new SprintScore("Numero 1", new Time(0,2,3,4));
                    SprintScore sprintScore2 = new SprintScore("Numero 2", new Time(0,3,4,1));
                    SprintScore sprintScore3 = new SprintScore("Numero 3", new Time(0,5,5,0));
                    SprintScore sprintScore4 = new SprintScore("Numero 4", new Time(0,7,3,7));
                    SprintScore sprintScore5 = new SprintScore("Numero 5", new Time(0,0,3,0));

                    listeScore.add(sprintScore1);
                    listeScore.add(sprintScore2);
                    listeScore.add(sprintScore3);
                    listeScore.add(sprintScore4);
                    listeScore.add(sprintScore5);

                } else {
                    DefiScore defiScore1 = new DefiScore("Jacky",12);
                    DefiScore defiScore2 = new DefiScore("Pedro",10);
                    DefiScore defiScore3 = new DefiScore("Flippy",9);
                    DefiScore defiScore4 = new DefiScore("Tiny",8);
                    DefiScore defiScore5 = new DefiScore("Aranys",7);
                    DefiScore defiScore6 = new DefiScore("SansBavure",1);
                    DefiScore defiScore7 = new DefiScore("Zerro",0);

                    listeScore.add(defiScore1);
                    listeScore.add(defiScore2);
                    listeScore.add(defiScore3);
                    listeScore.add(defiScore4);
                    listeScore.add(defiScore5);
                    listeScore.add(defiScore6);
                    listeScore.add(defiScore7);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
