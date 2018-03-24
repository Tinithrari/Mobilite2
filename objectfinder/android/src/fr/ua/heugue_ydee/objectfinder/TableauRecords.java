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
import fr.ua.heugue_ydee.model.DefiScore;
import fr.ua.heugue_ydee.model.HighScoreDBHelper;
import fr.ua.heugue_ydee.model.IdentifierFoundException;
import fr.ua.heugue_ydee.model.Score;
import fr.ua.heugue_ydee.model.SprintScore;

/**
 * Activite prenant en charge la gestion du tableau des records.
 */
public class TableauRecords extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private long difficulty;
    private long modeJeu;

    private List<Score> listeScore;
    private HighScoreDBHelper dateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_records);

        this.listeScore = new ArrayList<>();
        this.dateBase = new HighScoreDBHelper(this);
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

        ListView list = (ListView)findViewById(R.id.listeRecords);;

        //On accede pas au composant selectionne
        switch (parent.getId()){
            case R.id.spinnerMode :
                this.modeJeu = id;
                //ArrayList<Score> listTmp = new ArrayList<>();

                if(id == 0) {
                    this.listeScore = this.dateBase.getHighScoreSprintLimitTen((int)this.difficulty);

                } else {
                    this.listeScore = this.dateBase.getHighScoreDefiLimitTen((int)this.difficulty);
                }

                break;

            case R.id.spinnerDifficulte :
                this.difficulty = id;

                if(this.modeJeu == 0){
                    this.listeScore = this.dateBase.getHighScoreSprintLimitTen((int)this.difficulty);
                } else {
                    this.listeScore = this.dateBase.getHighScoreDefiLimitTen((int)this.difficulty);
                }

                break;
        }
        ((ScoreAdapter)list.getAdapter()).setScores(listeScore);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
