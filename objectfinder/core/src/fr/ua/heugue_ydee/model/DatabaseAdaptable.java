package fr.ua.heugue_ydee.model;

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
    public void addHighScoreSprint(SprintScore sprintScore, int difficulte) throws IdentifierFoundException;

    /**
     * Ajoute un score issu du mode Defi dans la table
     * @param defiScore
     */
    public void addHighScoreDefi(DefiScore defiScore, int difficulte) throws IdentifierFoundException;

    /**
     * @return la liste des 10 meilleurs du mode Sprint
     */
    public List<Score> getHighScoreSprintLimitTen(int indDifficulte);

    /**
     * @return la liste des 10 meilleurs du mode Defi
     */
    public List<Score> getHighScoreDefiLimitTen(int indDifficulte);

}