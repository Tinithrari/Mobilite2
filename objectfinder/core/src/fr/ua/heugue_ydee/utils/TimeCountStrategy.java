package fr.ua.heugue_ydee.utils;

import java.util.*;

/**
 * 
 */
public interface TimeCountStrategy {

    /**
     * Count the time using millisecond
     * @param millis The delta time to add in millisecond
     */
    void addTimeMillis(int millis);

    /**
     * Get the time
     *
     * @return the time stocked in this strategy
     */
    Time getTime();
}