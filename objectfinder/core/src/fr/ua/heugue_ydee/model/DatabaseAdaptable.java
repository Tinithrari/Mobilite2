package fr.ua.heugue_ydee.model;

import java.util.*;

/**
 * 
 */
public interface DatabaseAdaptable {

    /**
     * Ajoute un score issu du mode Sprint dans la table
     * @param sprintScore
     */
    public void addHighScoreSprint(SprintScore sprintScore);

    /**
     * Ajoute un score issu du mode Defi dans la table
     * @param defiScore
     */
    public void addHighScoreDefi(DefiScore defiScore);

    /**
     * @return la liste des 10 meilleurs du mode Sprint
     */
    public List<SprintScore> getHighScoreSprintLimitTen();

    /**
     * @return la liste des 10 meilleurs du mode Defi
     */
    public List<DefiScore> getHighScoreDefiLimitTen();

}