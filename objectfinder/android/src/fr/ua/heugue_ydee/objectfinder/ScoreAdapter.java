package fr.ua.heugue_ydee.objectfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.Color;

import java.util.List;

import model.Score;


/**
 * Created by Utilisateur on 01/03/2018.
 */

/**
 * Definition de notre propre adapter pour les scores
 */
public class ScoreAdapter extends ArrayAdapter<Score> {

    private List<Score> scores;
    private LayoutInflater inflater;

    /**
     * Constructeur de l'adapter de scores
     * @param context : le contexte
     * @param scores : la liste des scores
     */
    public ScoreAdapter(Context context, List<Score> scores){
        super(context,0,scores);
        this.scores = scores;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Retourne la taille de la liste des scores
     * @return la taille de la liste des scores
     */
    @Override
    public int getCount(){
        return this.scores.size();
    }

    /**
     * Retourne l'element graphique desire pour chaque case du tableau
     * @param position : la position dans la liste de donnees
     * @param convertView : le layout associe a l'element
     * @param parent : layout parent de l'element a affiche
     * @return l'element graphique desire pour chaque case du tableau
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //On recupere le score
        Score score = getItem(position);

        if (convertView == null) {
            //Initialisation de notre item à partir du layout XML
            convertView = inflater.inflate(R.layout.element_score_records, parent, false);
        }

        //On remplit la view (classement et le score associe)
        TextView tvClassement = (TextView) convertView.findViewById(R.id.Classement);
        tvClassement.setText(String.valueOf(position) + " - ");

        TextView tvRecord = (TextView) convertView.findViewById(R.id.Record);
        tvRecord.setText(score.toString());

        //Couleur de fond en fonction du classement
        switch (position){
            case 0 :
                convertView.setBackgroundColor(Color.rgb(255,215,0)); // Couleur OR
                break;
            case 1 :
                convertView.setBackgroundColor(Color.rgb(192,192,192)); // Couleur ARGENT
                break;
            case 2 :
                convertView.setBackgroundColor(Color.rgb(97,78,26)); // Couleur BRONZE
                break;
        }

        return convertView;
    }
}
