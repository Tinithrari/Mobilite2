package fr.ua.heugue_ydee.model;

import java.util.List;

/**
 * 
 */
public interface DatabaseAdaptable {

    /**
     * Ajoute un score issu du mode Sprint dans la table
     * @param sprintScore
     */
<<<<<<< HEAD:objectfinder/android/src/model/DatabaseAdaptable.java
    public void addHighScoreSprint(SprintScore sprintScore);
=======
    public void addHighScoreSprint(SprintScore sprintScore, int difficulte) throws IdentifierFoundException;
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/DatabaseAdaptable.java

    /**
     * Ajoute un score issu du mode Defi dans la table
     * @param defiScore
     */
<<<<<<< HEAD:objectfinder/android/src/model/DatabaseAdaptable.java
    public void addHighScoreDefi(DefiScore defiScore);
=======
    public void addHighScoreDefi(DefiScore defiScore, int difficulte) throws IdentifierFoundException;
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/DatabaseAdaptable.java

    /**
     * @return la liste des 10 meilleurs du mode Sprint
     */
    public List<Score> getHighScoreSprintLimitTen(int indDifficulte);

    /**
     * @return la liste des 10 meilleurs du mode Defi
     */
    public List<Score> getHighScoreDefiLimitTen(int indDifficulte);

}