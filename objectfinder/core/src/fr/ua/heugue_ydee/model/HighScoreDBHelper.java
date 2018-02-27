package fr.ua.heugue_ydee.model;

import java.util.*;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
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
        db.execSQl(DROP_TABLE_DEFI);
        onCreate(db);
    }

    /**
     * @param sprintScore 
     * @return
     */
    public void addHighScoreSprint(SprintScore sprintScore) {

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
     * @param defiScore 
     * @return
     */
    public void addHighScoreDefi(DefiScore defiScore) {

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_DEFI, defiScore.getName());
        values.put(SCORE_DEFI, defiScore.getScore());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(NAME_TABLE_DEFI ,null, values);
    }

    /**
     * @return
     */
    public List<SprintScore> getHighScoreSprintLimitTen() {

        final String queryHighScoreSprintLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_SPRINT +
                " ORDER BY " + MINUTES_SPRINT +
                ", " + SECONDS_SPRINT +
                ", " + MILLIS_SPRINT +
                " LIMIT 10";

        Cursor notreTopDix = this.rawQuery(queryHighScoreSprintLimitTen, null);

        List<SprintScore> notreListeDesTopDix = new ArrayList<SprintScore>();

        try {
            while (notreTopDix.moveToNext()) {
                SprintScore sprintScore = new SprintScore(notreTopDix.getLong(1),notreTopDix.getString(2),notreTopDix.getLong(3),notreTopDix.getInt(4),notreTopDix.getInt(5));
                notreListeDesTopDix.add(defiScore);
            }
        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }

    /**
     * @return
     */
    public List<DefiScore> getHighScoreDefiLimitTen() {

        final String queryHighScoreDefiLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_DEFI +
                " ORDER BY " + SCORE_DEFI +
                " DESC LIMIT 10";

        Cursor notreTopDix = this.rawQuery(queryHighScoreDefiLimitTen, null);

        List<DefiScore> notreListeDesTopDix = new ArrayList<DefiScore>();

        try {
            while (notreTopDix.moveToNext()) {
                DefiScore defiScore = new DefiScore(notreTopDix.getLong(1),notreTopDix.getString(2),notreTopDix.getLong(3));
                notreListeDesTopDix.add(defiScore);
            }
        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }
}