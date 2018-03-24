package fr.ua.heugue_ydee.utils;

import java.util.Locale;
import java.util.Objects;

/**
 * Class for measuring time
 */
public class Time {
    private static final int MAX_MILLISECONDS = 1000;
    private static final int MAX_SECONDS = 60;
    private static final int MAX_MINUTES = 60;
    private long hours;
    private int  minutes;
    private int  seconds;
    private int  milliseconds;

    /**
     * Construct a time representation
     * @param hours hours
     * @param minutes minutes
     * @param seconds seconds
     * @param milliseconds milliseconds
     */
    public Time(long hours, int minutes, int seconds, int milliseconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.correctTime();
    }

    public Time() {
        this(0, 0, 0, 0);
    }

    /**
     * Get the hours in time
     * @return The hours of the time
     */
    public long getHours() {
        return hours;
    }

    /**
     * Get the minutes of the time
     * @return The minutes of the time
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Get the seconds of the time
     * @return The seconds of the time
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Get the milliseconds of the times
     * @return the milliseconds of the times
     */
    public int getMilliseconds() {
        return milliseconds;
    }

    /**
     * Correct the time if needed
     */
    private void correctTime() {

        // Manage the excess amount of time
        while (this.milliseconds >= MAX_MILLISECONDS)
        {
            this.milliseconds -= MAX_MILLISECONDS;
            this.seconds += 1;
        }

        while (this.seconds >= MAX_SECONDS)
        {
            this.seconds -= MAX_SECONDS;
            this.minutes += 1;
        }

        while (this.minutes >= MAX_MINUTES)
        {
            this.minutes -= MAX_MINUTES;
            this.hours += 1;
        }

        // Manage the lack amount of time
        while (this.milliseconds < 0)
        {
            this.milliseconds = MAX_MILLISECONDS + this.milliseconds;
            this.seconds -= 1;
        }

        while (this.seconds < 0)
        {
            this.seconds = MAX_SECONDS + this.seconds;
            this.minutes -= 1;
        }

        while (this.minutes < 0)
        {
            this.minutes = MAX_MINUTES + this.minutes;
            this.hours -= 1;
        }

        if (this.hours < 0)
        {
            this.hours = 0;
            this.minutes = 0;
            this.seconds = 0;
            this.milliseconds = 0;
        }
    }

    /**
     * Add an amount of time to the current time
     *
     * @param hours Number of hours to add
     * @param minutes Number of minutes to add
     * @param seconds Number of seconds to add
     * @param milliseconds Number of milliseconds to add
     *
     * @return The new Time after adding this time
     */
    public Time addTime(long hours, int minutes, int seconds, int milliseconds) {
        Time t = new Time(this.hours + hours,
        this.minutes + minutes,
        this.seconds + seconds,
        this.milliseconds + milliseconds);

        t.correctTime();

        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hours == time.hours &&
                minutes == time.minutes &&
                seconds == time.seconds &&
                milliseconds == time.milliseconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds, milliseconds);
    }

    @Override
    public String toString() {
        return String.format(Locale.FRANCE,"%2d", hours) + ":"
                + String.format(Locale.FRANCE,"%2d",minutes) + ":"
                + String.format(Locale.FRANCE,"%2d",seconds) + ":"
                + String.format(Locale.FRANCE,"%3d",milliseconds);
    }
}
