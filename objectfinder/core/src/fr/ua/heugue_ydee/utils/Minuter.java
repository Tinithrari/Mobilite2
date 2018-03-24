package fr.ua.heugue_ydee.utils;

import java.util.Locale;

/**
 * Count time using the minuter strategy
 */
public class Minuter implements TimeCountStrategy {

    private Time time;

    /**
     * Construct a minuter set at minutes, seconds
     *
     * @param minutes Number of minutes
     * @param seconds Number of seconds
     */
    public Minuter(int minutes, int seconds) {
        this.time = new Time(0, minutes, seconds, 0);
    }

    /**
     * Count the time using millisecond
     *
     * @param millis The delta time to add in millisecond
     */
    @Override
    public void addTimeMillis(int millis) {
        this.time = this.time.addTime(0, 0, 0, - millis);
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
        return String.format(Locale.FRANCE,"%2d", this.time.getMinutes()) + ":"
                + String.format(Locale.FRANCE,"%2d",this.time.getSeconds()) + ":"
                + String.format(Locale.FRANCE,"%2d",this.time.getMilliseconds());
    }
}