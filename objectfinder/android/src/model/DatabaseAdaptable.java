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
    public void addHighScoreSprint(SprintScore sprintScore) throws IdentifientNotFoundException;

    /**
     * Ajoute un score issu du mode Defi dans la table
     * @param defiScore
     */
    public void addHighScoreDefi(DefiScore defiScore) throws IdentifientNotFoundException;

    /**
     * @return la liste des 10 meilleurs du mode Sprint
     */
    public List<SprintScore> getHighScoreSprintLimitTen();

    /**
     * @return la liste des 10 meilleurs du mode Defi
     */
    public List<DefiScore> getHighScoreDefiLimitTen();

}