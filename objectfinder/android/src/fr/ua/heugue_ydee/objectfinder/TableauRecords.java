package fr.ua.heugue_ydee.objectfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.DefiScore;
import model.Score;

/**
 * Activite prenant en charge la gestion du tableau des records.
 */
public class TableauRecords extends AppCompatActivity {

    private List<Score> listeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_records);

        //Initialisation de la liste
        listeScore = new ArrayList<>();

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

        //Création et initialisation de l'Adapter pour les scores
        ScoreAdapter adapter = new ScoreAdapter(this, listeScore);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.listeRecords);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);
    }
}
