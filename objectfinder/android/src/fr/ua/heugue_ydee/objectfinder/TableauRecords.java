package fr.ua.heugue_ydee.objectfinder;

import android.content.Intent;
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
import model.HighScoreDBHelper;
import model.IdentifierFoundException;
import model.Score;
import model.SprintScore;

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

        SprintScore sprintScore1 =
                new SprintScore("Numero 1", new Time(0,2,3,4));
        SprintScore sprintScore2 =
                new SprintScore("Numero 2", new Time(0,3,4,1));
        SprintScore sprintScore3 =
                new SprintScore("Numero 3", new Time(0,5,5,0));
        SprintScore sprintScore4 =
                new SprintScore("Numero 4", new Time(0,7,3,7));
        SprintScore sprintScore5 =
                new SprintScore("Numero 5", new Time(0,0,3,0));

        try {
            dateBase.addHighScoreSprint(sprintScore1,0);
            dateBase.addHighScoreSprint(sprintScore2,0);
            dateBase.addHighScoreSprint(sprintScore3,2);
            dateBase.addHighScoreSprint(sprintScore4,1);
            dateBase.addHighScoreSprint(sprintScore5,2);
        } catch (IdentifierFoundException e) {
            e.printStackTrace();
        }

        DefiScore defiScore1 = new DefiScore("Jacky",12);
        DefiScore defiScore2 = new DefiScore("Pedro",11);
        DefiScore defiScore3 = new DefiScore("Flippy",10);
        DefiScore defiScore4 = new DefiScore("Tiny",9);
        DefiScore defiScore5 = new DefiScore("Aranys",8);
        DefiScore defiScore6 = new DefiScore("SansBavure",7);
        DefiScore defiScore7 = new DefiScore("Zerro",5);
        DefiScore defiScore8 = new DefiScore("Ar Beka",4);
        DefiScore defiScore9 = new DefiScore("Horza",3);
        DefiScore defiScore10 = new DefiScore("Dix",2);
        DefiScore defiScore11 = new DefiScore("Onze",1);
        DefiScore defiScore12 = new DefiScore("Douze",0);

        try {
            dateBase.addHighScoreDefi(defiScore1,0);
            dateBase.addHighScoreDefi(defiScore2,0);
            dateBase.addHighScoreDefi(defiScore3,0);
            dateBase.addHighScoreDefi(defiScore4,0);
            dateBase.addHighScoreDefi(defiScore5,0);
            dateBase.addHighScoreDefi(defiScore6,0);
            dateBase.addHighScoreDefi(defiScore7,0);
            dateBase.addHighScoreDefi(defiScore8,0);
            dateBase.addHighScoreDefi(defiScore9,0);
            dateBase.addHighScoreDefi(defiScore10,0);
            dateBase.addHighScoreDefi(defiScore11,0);
            dateBase.addHighScoreDefi(defiScore12,0);
        } catch (IdentifierFoundException e) {
            e.printStackTrace();
        }

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

                    /*
                    listTmp.add(sprintScore1);
                    listTmp.add(sprintScore2);
                    listTmp.add(sprintScore3);
                    listTmp.add(sprintScore4);
                    listTmp.add(sprintScore5);*/
                } else {

                    this.listeScore = this.dateBase.getHighScoreDefiLimitTen((int)this.difficulty);

                    /*
                    listTmp.add(defiScore1);
                    listTmp.add(defiScore2);
                    listTmp.add(defiScore3);
                    listTmp.add(defiScore4);
                    listTmp.add(defiScore5);
                    listTmp.add(defiScore6);
                    listTmp.add(defiScore7);*/
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
