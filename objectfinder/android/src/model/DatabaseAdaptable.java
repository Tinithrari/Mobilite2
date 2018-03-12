package model;

import java.util.List;

/**
 * Interface nous permettant l'ajout de nos scores
 * et egalement les acces aux records en fonction du mode de jeu
 */
public interface DatabaseAdaptable {

    /**
     * Ajoute un score issu du mode Sprint dans la table
     * @param sprintScore
     */
    void addHighScoreSprint(SprintScore sprintScore) throws IdentifierFoundException;

    /**
     * Ajoute un score issu du mode Defi dans la table
     * @param defiScore
     */
    void addHighScoreDefi(DefiScore defiScore) throws IdentifierFoundException;

    /**
     * @return la liste des 10 meilleurs du mode Sprint
     */
    List<SprintScore> getHighScoreSprintLimitTen();

    /**
     * @return la liste des 10 meilleurs du mode Defi
     */
    List<DefiScore> getHighScoreDefiLimitTen();

}