package model;

import fr.ua.heugue_ydee.utils.Time;

/**
 * 
 */
public class SprintScore implements Score{

    /**
     * Default constructor
     */
    public SprintScore() {
        this.id = null;
        this.name = null;
        this.duration = null;
    }

    /**
     * L'identifiant
     */
    private Long id;

    /**
     * Le nom du joueur
     */
    private String name;

    /**
     * Le temps
     */
    private Time duration;

    /**
     * Constructeur de SprintScore prenant l'id, le nom, les minutes, secondes et millisecondes
     * @param id : l'identifiant
     * @param name : le nom du joueur
     * @param minutes : les minutes
     * @param seconds : les secondes
     * @param millis : les millisecondes
     */
    public SprintScore(Long id, String name, int minutes, int seconds, int millis) {
        this.id = id;
        this.name = name;
        this.duration = new Time(0, minutes, seconds, millis);
    }

    /**
     * Constructeur prenant en parametre le nom et la duree
     * @param name : le nom
     * @param duration : la duree
     */
    public SprintScore(String name, Time duration) {
        this.id = null;
        this.name = name;
        this.duration = duration;
    }

    /**
     * Retourne l'identifiant
     * @return : l'identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le nom du joueur
     * @return : le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la duree
     * @return : la duree
     */
    public Time getDuration() {
        return duration;
    }

    /**
     * Modification de l'identifiant
     * @param id : l'identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Modification du nom
     * @param name : le nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modification de la duree
     * @param duration : la duree
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    /**
     * Affichage de nos differentes informations pour le score Sprint
     */
    public String toString(){
        return this.name + " : " + this.duration.toString();
    }
}