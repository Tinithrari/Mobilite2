package fr.ua.heugue_ydee.model;

/**
 * 
 */
public class DefiScore {

    /**
     * Default constructor
     */
    public DefiScore() {
        this.id = null;
        this.name = null;
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     *
     */
    private long score;

    /**
     * @param id
     * @param name
     * @param score
     */
    public void DefiScore(Long id, String name, long score) {
        this.id = new Long(id);
        this.name = name;
        this.score = score;
    }

    /**
     * @param name
     * @param score
     */
    public void DefiScore(String name, long score) {
        this.id = new Long(0);
        this.name = name;
        this.score = score;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/DefiScore.java
     *
     * @return
=======
     * Return the score value
     *
     * @return A string that represent the score value
     */
    @Override
    public String getScoreValue() {
        return Long.toString(this.score);
    }

    /**
     * Retourne le score obtenu
     * @return : le score obtenu par le joueur
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/DefiScore.java
     */
    public long getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(long score) {
        this.score = score;
    }
}