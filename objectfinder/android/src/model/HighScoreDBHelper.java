package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteClosable;

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

    //Table Defi
    private static final String NAME_TABLE_DEFI = "defiScore";

    //Noms des colonnes : table Defi
    private static final String ID_DEFI = "idDefi";
    private static final String NAME_DEFI = "name";
    private static final String SCORE_DEFI = "score";

    //Requete de creation
    private static final String CREATE_TABLE_SQL_SPRINT =
            "CREATE TABLE " + NAME_TABLE_SPRINT + " (" +
                    ID_SPRINT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_SPRINT + " TEXT, " +
                    MINUTES_SPRINT + " INTEGER, " +
                    SECONDS_SPRINT + " INTEGER, " +
                    MILLIS_SPRINT + " INTEGER)";

    private static final String CREATE_TABLE_SQL_DEFI =
            "CREATE TABLE " + NAME_TABLE_DEFI + " (" +
                    ID_DEFI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_DEFI + " TEXT, " +
                    SCORE_DEFI + " INTEGER)";

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
    public void addHighScoreSprint(SprintScore sprintScore) throws IdentifientNotFoundException{

        if(sprintScore.getId() == null)
            throw new IdentifientNotFoundException();

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_SPRINT, sprintScore.getName());
        values.put(MINUTES_SPRINT,sprintScore.getDuration().getMinutes());
        values.put(SECONDS_SPRINT,sprintScore.getDuration().getSeconds());
        values.put(MILLIS_SPRINT,sprintScore.getDuration().getMilliseconds());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(NAME_TABLE_SPRINT ,null, values);
    }

    /**
     * Ajoute un defiScore dans la table correspondante
     * @param defiScore : defiscore
     */
    public void addHighScoreDefi(DefiScore defiScore) throws IdentifientNotFoundException {

        if(defiScore.getId() == null)
            throw new IdentifientNotFoundException();

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_DEFI, defiScore.getName());
        values.put(SCORE_DEFI, defiScore.getScore());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(NAME_TABLE_DEFI ,null, values);
    }

    /**
     * Retourne la liste des 10 meilleurs score du mode Sprint
     * @return : la liste des 10 meilleurs score du mode Sprint
     */
    public List<SprintScore> getHighScoreSprintLimitTen() {

        final String queryHighScoreSprintLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_SPRINT +
                " ORDER BY " + MINUTES_SPRINT +
                ", " + SECONDS_SPRINT +
                ", " + MILLIS_SPRINT +
                " LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor notreTopDix = db.rawQuery(queryHighScoreSprintLimitTen, null);

        List<SprintScore> notreListeDesTopDix = new ArrayList<>();

        try {
            while (notreTopDix.moveToNext()) {
                //On complete les differents champs
                SprintScore sprintScore = new SprintScore();
                sprintScore.setId(notreTopDix.getLong(1));
                sprintScore.setName(notreTopDix.getString(2));
                sprintScore.setDuration(new Time(0,notreTopDix.getInt(3),notreTopDix.getInt(4),notreTopDix.getInt(5)));

                notreListeDesTopDix.add(sprintScore);
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
    public List<DefiScore> getHighScoreDefiLimitTen() {

        final String queryHighScoreDefiLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_DEFI +
                " ORDER BY " + SCORE_DEFI +
                " DESC LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor notreTopDix = db.rawQuery(queryHighScoreDefiLimitTen, null);

        List<DefiScore> notreListeDesTopDix = new ArrayList<>();

        try {
            while (notreTopDix.moveToNext()) {

                DefiScore defiScore = new DefiScore();

                //On complete les differents champs
                defiScore.setId(notreTopDix.getLong(1));
                defiScore.setName(notreTopDix.getString(2));
                defiScore.setScore(notreTopDix.getInt(3));

                //DefiScore defiScore = new DefiScore(notreTopDix.getLong(1),notreTopDix.getString(2),notreTopDix.getLong(3));
                notreListeDesTopDix.add(defiScore);
            }
        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }
}