package fr.ua.heugue_ydee.model;

/**
 * Interface for score formatting
 */
public interface Score {

    /**
     * @return la chaine qui va representer le score
     */
    String toString();

    /**
     * Modification du nom du joueur
     * @param name : le nom du joueur
     */
    void setName(String name);

    /**
     * Return the score value
     *
     * @return A string that represent the score value
     */
    String getScoreValue();
}
