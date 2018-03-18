package fr.ua.heugue_ydee.utils;

import java.util.Locale;

/**
 * Count the time using the chronometer strategy
 */
public class Chronometer implements TimeCountStrategy {

    private Time time;

    /**
     * Default constructor
     */
    public Chronometer() {
        this.time = new Time();
    }


    /**
     * Count the time using millisecond
     *
     * @param millis The delta time to add in millisecond
     */
    @Override
    public void addTimeMillis(int millis) {
        this.time = this.time.addTime(0, 0, 0, millis);
    }

    /**
     * Get the time
     *
     * @return the time stocked in this strategy
     */
    @Override
    public Time getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format(Locale.FRANCE,"%2d", this.time.getHours()) + ":"
                + String.format(Locale.FRANCE,"%2d", this.time.getMinutes()) + ":"
                + String.format(Locale.FRANCE,"%2d",this.time.getSeconds()) + ":"
                + String.format(Locale.FRANCE,"%2d",this.time.getMilliseconds());
    }
}