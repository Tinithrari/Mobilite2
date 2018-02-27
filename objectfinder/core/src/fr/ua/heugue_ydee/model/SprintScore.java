package fr.ua.heugue_ydee.model;

import fr.ua.heugue_ydee.utils.Time;
import java.util.*;

/**
 * 
 */
public class SprintScore {

    /**
     * Default constructor
     */
    public SprintScore() {
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
     * @param id 
     * @param name 
     * @param minutes 
     * @param seconds 
     * @param millis
     */
    public void SprintScore(Long id, java.lang.String name, int minutes, int seconds, int millis) {
        this.id = id;
        this.name = name;
        this.duration = new Time(0, minutes, seconds, millis);
    }

    /**
     * @param name 
     * @param duration
     */
    public void SprintScore(java.lang.String name, Time duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
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
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }
}