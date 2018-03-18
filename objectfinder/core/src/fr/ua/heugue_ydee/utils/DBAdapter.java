package fr.ua.heugue_ydee.utils;

import fr.ua.heugue_ydee.model.DatabaseAdaptable;
import fr.ua.heugue_ydee.model.DefiScore;
import fr.ua.heugue_ydee.model.IdentifierFoundException;
import fr.ua.heugue_ydee.model.SprintScore;

/**
 * Permits crossplatform usage of databases
 */
public class DBAdapter {

    private DatabaseAdaptable database;

    /**
     * Create a database adapter
     *
     * @param database the database to use
     */
    public DBAdapter(DatabaseAdaptable database) {
        this.database = database;
    }

    /**
     * Add a defi score into the database
     *
     * @param name The name of the player
     * @param score The score of the player
     */
    public void addScoreDefi(String name, long score, int difficulty) throws IdentifierFoundException {
        DefiScore defiScore = new DefiScore();
        defiScore.setName(name);
        defiScore.setScore(score);
        this.database.addHighScoreDefi(defiScore, difficulty);
    }

    /**
     * Add a score sprint to the database
     *
     * @param name The name of the player
     * @param time The time of the player
     */
    public void addScoreSprint(String name, Time time, int difficulty) throws IdentifierFoundException {
        SprintScore score = new SprintScore();
        score.setName(name);
        score.setDuration(time);
        this.database.addHighScoreSprint(score, difficulty);
    }

}