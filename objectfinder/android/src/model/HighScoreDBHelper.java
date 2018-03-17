package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.ua.heugue_ydee.utils.Time;

/**
 * La classe d'Helper qui va permettre d'etablir plusieurs actions avec notre BDD
 */
public class HighScoreDBHelper extends SQLiteOpenHelper implements DatabaseAdaptable {

    private static final String DB_NAME = "objectFinderDatabase";
    private static final int DB_VERSION = 1;

    //Table Sprint
    private static final String NAME_TABLE_SPRINT = "sprintScore";

    //Noms des colonnes : table Sprint
    private static final String ID_SPRINT = "idSprint";
    private static final String NAME_SPRINT = "name";
    private static final String MINUTES_SPRINT = "minutes";
    private static final String SECONDS_SPRINT = "seconds";
    private static final String MILLIS_SPRINT = "millis";
    private static final String DIFFICULTE_SPRINT = "difficulte";

    //Table Defi
    private static final String NAME_TABLE_DEFI = "defiScore";

    //Noms des colonnes : table Defi
    private static final String ID_DEFI = "idDefi";
    private static final String NAME_DEFI = "name";
    private static final String SCORE_DEFI = "score";
    private static final String DIFFICULTE_DEFI = "difficulte";

    //Requete de creation
    private static final String CREATE_TABLE_SQL_SPRINT =
            "CREATE TABLE " + NAME_TABLE_SPRINT + " (" +
                    ID_SPRINT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_SPRINT + " TEXT, " +
                    MINUTES_SPRINT + " INTEGER, " +
                    SECONDS_SPRINT + " INTEGER, " +
                    MILLIS_SPRINT + " INTEGER, " +
                    DIFFICULTE_SPRINT + " TEXT)";

    private static final String CREATE_TABLE_SQL_DEFI =
            "CREATE TABLE " + NAME_TABLE_DEFI + " (" +
                    ID_DEFI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_DEFI + " TEXT, " +
                    SCORE_DEFI + " INTEGER, " +
                    DIFFICULTE_DEFI + " TEXT)";

    //Requete de suppression des tables
    private static final String DROP_TABLE_SPRINT = "DROP TABLE " + NAME_TABLE_SPRINT;
    private static final String DROP_TABLE_DEFI = "DROP TABLE " + NAME_TABLE_DEFI;

    /**
     * Default constructor
     */
    public HighScoreDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_SQL_SPRINT);
        db.execSQL(CREATE_TABLE_SQL_DEFI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_TABLE_SPRINT);
        db.execSQL(DROP_TABLE_DEFI);
        onCreate(db);
    }

    /**
     * Ajoute un nouveau sprintScore dans la table correspondante
     * @param sprintScore : le sprintscore
     */
    public void addHighScoreSprint(SprintScore sprintScore, int difficulte) throws IdentifierFoundException {

        if(sprintScore.getId() != null)
            throw new IdentifierFoundException();

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_SPRINT, sprintScore.getName());
        values.put(MINUTES_SPRINT,sprintScore.getDuration().getMinutes());
        values.put(SECONDS_SPRINT,sprintScore.getDuration().getSeconds());
        values.put(MILLIS_SPRINT,sprintScore.getDuration().getMilliseconds());

        switch (difficulte) {
            case 0 :
                values.put(DIFFICULTE_SPRINT, "Facile");
                break;
            case 1:
                values.put(DIFFICULTE_SPRINT, "Normale");
                break;
            case 2 :
                values.put(DIFFICULTE_SPRINT, "Difficile");
                break;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        if (db.isReadOnly())
            throw new RuntimeException("Base de données inaccessible");

        db.insert(NAME_TABLE_SPRINT ,null, values);
    }

    /**
     * Ajoute un defiScore dans la table correspondante
     * @param defiScore : defiscore
     */
    public void addHighScoreDefi(DefiScore defiScore, int difficulte) throws IdentifierFoundException {

        if(defiScore.getId() != null)
            throw new IdentifierFoundException();

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_DEFI, defiScore.getName());
        values.put(SCORE_DEFI, defiScore.getScore());

        switch (difficulte) {
            case 0 :
                values.put(DIFFICULTE_DEFI, "Facile");
                break;
            case 1:
                values.put(DIFFICULTE_DEFI, "Normale");
                break;
            case 2 :
                values.put(DIFFICULTE_DEFI, "Difficile");
                break;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        if (db.isReadOnly())
            throw new RuntimeException("Base de données inaccessible");

        db.insert(NAME_TABLE_DEFI ,null, values);
    }

    /**
     * Retourne la liste des 10 meilleurs score du mode Sprint
     * @return : la liste des 10 meilleurs score du mode Sprint
     */
    public List<Score> getHighScoreSprintLimitTen(int indDifficulte) {

        String difficulte = "";

        switch (indDifficulte) {
            case 0 :
                difficulte = "Facile";
                break;
            case 1:
                difficulte = "Normale";
                break;
            case 2 :
                difficulte = "Difficile";
                break;
        }

        final String queryHighScoreSprintLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_SPRINT +
                " WHERE " + DIFFICULTE_SPRINT + " = '" + difficulte +
                "' ORDER BY " + MINUTES_SPRINT +
                ", " + SECONDS_SPRINT +
                ", " + MILLIS_SPRINT +
                " LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor notreTopDix = db.rawQuery(queryHighScoreSprintLimitTen, null);

        List<Score> notreListeDesTopDix = new ArrayList<>();

        try {

            if(notreTopDix != null){
                if(notreTopDix.moveToFirst()){
                    do {
                        SprintScore sprintScore = new SprintScore();
                        //sprintScore.setId(notreTopDix.getLong(1));
                        sprintScore.setName(notreTopDix.getString(1));
                        sprintScore.setDuration(new Time(0,notreTopDix.getInt(2),notreTopDix.getInt(3),notreTopDix.getInt(4)));

                        notreListeDesTopDix.add(sprintScore);
                    } while (notreTopDix.moveToNext());
                }
            }

        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }

    /**
     * Retourne la liste des 10 meilleurs scores du mode Defi
     * @return : la liste des 10 meilleurs scores du mode Defi
     */
    public List<Score> getHighScoreDefiLimitTen(int indDifficulte) {

        String difficulte = "";

        switch (indDifficulte) {
            case 0 :
                difficulte = "Facile";
                break;
            case 1:
                difficulte = "Normale";
                break;
            case 2 :
                difficulte = "Difficile";
                break;
        }

        final String queryHighScoreDefiLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_DEFI +
                " WHERE " + DIFFICULTE_DEFI + " = '" + difficulte +
                "' ORDER BY " + SCORE_DEFI +
                " DESC LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor notreTopDix = db.rawQuery(queryHighScoreDefiLimitTen, null);

        List<Score> notreListeDesTopDix = new ArrayList<>();

        try {
            while (notreTopDix.moveToNext()) {

                DefiScore defiScore = new DefiScore();

                //On complete les differents champs
                defiScore.setName(notreTopDix.getString(1));
                defiScore.setScore(notreTopDix.getInt(2));

                notreListeDesTopDix.add(defiScore);
            }
        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }
}