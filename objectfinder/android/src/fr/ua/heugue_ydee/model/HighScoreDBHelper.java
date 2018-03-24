package fr.ua.heugue_ydee.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQl(DROP_TABLE_DEFI);
        onCreate(db);
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/HighScoreDBHelper.java
     * @param sprintScore 
     * @return
     */
    public void addHighScoreSprint(SprintScore sprintScore) {
=======
     * Associe l'indice a la difficulte correspondante
     * @param indice
     * @return
     */
    public String findDifficulte(final int indice){
        switch (indice) {
            case 0 :
                return "Basique";
            case 1:
                return "Facile";
            case 2 :
                return "Normale";
            case 3 :
                return "Difficile";
            default :
                return "Basique";
        }
    }

    /**
     * Ajoute un nouveau sprintScore dans la table correspondante
     * @param sprintScore : le sprintscore
     */
    public void addHighScoreSprint(SprintScore sprintScore, int difficulte) throws IdentifierFoundException {

        if(sprintScore.getId() != null)
            throw new IdentifierFoundException();
>>>>>>> develop:objectfinder/android/src/fr/ua/heugue_ydee/model/HighScoreDBHelper.java

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_SPRINT, sprintScore.getName());
        values.put(MINUTES_SPRINT,sprintScore.getDuration().getMinutes());
        values.put(SECONDS_SPRINT,sprintScore.getDuration().getSeconds());
        values.put(MILLIS_SPRINT,sprintScore.getDuration().getMilliseconds());

        values.put(DIFFICULTE_SPRINT,findDifficulte(difficulte));

        SQLiteDatabase db = this.getWritableDatabase();

        if (db.isReadOnly())
            throw new RuntimeException("Base de données inaccessible");

        db.insert(NAME_TABLE_SPRINT ,null, values);
    }

    /**
     * @param defiScore 
     * @return
     */
<<<<<<< HEAD:objectfinder/android/src/model/HighScoreDBHelper.java
    public void addHighScoreDefi(DefiScore defiScore) {
=======
    public void addHighScoreDefi(DefiScore defiScore, int difficulte) throws IdentifierFoundException {

        if(defiScore.getId() != null)
            throw new IdentifierFoundException();
>>>>>>> develop:objectfinder/android/src/fr/ua/heugue_ydee/model/HighScoreDBHelper.java

        ContentValues values = new ContentValues();

        //Preparation du tuple a inserer
        values.put(NAME_DEFI, defiScore.getName());
        values.put(SCORE_DEFI, defiScore.getScore());

        values.put(DIFFICULTE_DEFI,findDifficulte(difficulte));

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(NAME_TABLE_DEFI ,null, values);
    }

    /**
     * @return
     */
    public List<Score> getHighScoreSprintLimitTen(int indDifficulte) {

        String difficulte = findDifficulte(indDifficulte);

        final String queryHighScoreSprintLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_SPRINT +
                " WHERE " + DIFFICULTE_SPRINT + " = '" + difficulte +
                "' ORDER BY " + MINUTES_SPRINT +
                ", " + SECONDS_SPRINT +
                ", " + MILLIS_SPRINT +
                " LIMIT 10";

        Cursor notreTopDix = this.rawQuery(queryHighScoreSprintLimitTen, null);

<<<<<<< HEAD:objectfinder/android/src/model/HighScoreDBHelper.java
        List<SprintScore> notreListeDesTopDix = new ArrayList<SprintScore>();

        try {
            while (notreTopDix.moveToNext()) {
                SprintScore sprintScore = new SprintScore(notreTopDix.getLong(1),notreTopDix.getString(2),notreTopDix.getLong(3),notreTopDix.getInt(4),notreTopDix.getInt(5));
                notreListeDesTopDix.add(defiScore);
=======
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
>>>>>>> develop:objectfinder/android/src/fr/ua/heugue_ydee/model/HighScoreDBHelper.java
            }

        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }

    /**
     * @return
     */
    public List<Score> getHighScoreDefiLimitTen(int indDifficulte) {

        String difficulte = findDifficulte(indDifficulte);

        final String queryHighScoreDefiLimitTen = "SELECT * " +
                "FROM " + NAME_TABLE_DEFI +
                " WHERE " + DIFFICULTE_DEFI + " = '" + difficulte +
                "' ORDER BY " + SCORE_DEFI +
                " DESC LIMIT 10";

        Cursor notreTopDix = this.rawQuery(queryHighScoreDefiLimitTen, null);

<<<<<<< HEAD:objectfinder/android/src/model/HighScoreDBHelper.java
        List<DefiScore> notreListeDesTopDix = new ArrayList<DefiScore>();

        try {
            while (notreTopDix.moveToNext()) {
                DefiScore defiScore = new DefiScore(notreTopDix.getLong(1),notreTopDix.getString(2),notreTopDix.getLong(3));
=======
        List<Score> notreListeDesTopDix = new ArrayList<>();

        try {
            while (notreTopDix.moveToNext()) {

                DefiScore defiScore = new DefiScore();

                //On complete les differents champs
                defiScore.setName(notreTopDix.getString(1));
                defiScore.setScore(notreTopDix.getInt(2));

>>>>>>> develop:objectfinder/android/src/fr/ua/heugue_ydee/model/HighScoreDBHelper.java
                notreListeDesTopDix.add(defiScore);
            }
        } finally {
            notreTopDix.close();
        }

        return notreListeDesTopDix;
    }
}