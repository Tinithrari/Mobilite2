package fr.ua.heugue_ydee.model;

import fr.ua.heugue_ydee.utils.Time;

/**
 * 
 */
public class SprintScore {

    /**
     * Default constructor
     */
    public SprintScore() {
        this.id = null;
        this.name = null;
        this.duration = null;
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
    private Time duration;

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     * @param id 
     * @param name 
     * @param minutes 
     * @param seconds 
     * @param millis
=======
     * Constructeur de SprintScore prenant l'id, le nom, les minutes, secondes et millisecondes
     *
     * @param id : l'identifiant
     * @param name : le nom du joueur
     * @param minutes : les minutes
     * @param seconds : les secondes
     * @param millis : les millisecondes
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    public void SprintScore(Long id, String name, int minutes, int seconds, int millis) {
        this.id = id;
        this.name = name;
        this.duration = new Time(0, minutes, seconds, millis);
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     * @param name
     * @param duration
     */
    public void SprintScore(String name, Time duration) {
        this.id = id;
=======
     * Constructeur prenant en parametre le nom et la duree
     *
     * @param name : le nom
     * @param duration : la duree
     */
    public SprintScore(String name, Time duration) {
        this.id = null;
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
        this.name = name;
        this.duration = duration;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     * @return
=======
     * Retourne l'identifiant
     *
     * @return : l'identifiant
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    public Long getId() {
        return id;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     * @return
=======
     * Retourne le nom du joueur
     *
     * @return : le nom du joueur
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    public String getName() {
        return name;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     *
     * @return
=======
     * Retourne la duree
     *
     * @return : la duree
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    public Time getDuration() {
        return duration;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     *
     * @param name
=======
     * Modification du nom
     *
     * @param name : le nom du joueur
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
     *
     * @param duration
=======
     * Return the score value
     *
     * @return A string that represent the score value
     */
    @Override
    public String getScoreValue() {
        return this.duration.toString();
    }

    /**
     * Modification de la duree
     *
     * @param duration : la duree
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }
<<<<<<< HEAD:objectfinder/android/src/model/SprintScore.java
=======

    /**
     * Donne une chaine de caractère représentant l'objet
     *
     * @return Retourne le score formatté en chaine de caractere
     */
    @Override
    public String toString(){
        return this.name + " : " + this.duration.toString();
    }
>>>>>>> develop:objectfinder/core/src/fr/ua/heugue_ydee/model/SprintScore.java
}