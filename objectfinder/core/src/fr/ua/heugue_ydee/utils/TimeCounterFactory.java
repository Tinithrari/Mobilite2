package fr.ua.heugue_ydee.utils;

/**
 * 
 */
public final class TimeCounterFactory {

    /**
     * Default constructor
     */
    public TimeCounterFactory() {

    }

    /**
     * @return A time count strategy using the chronometer strategy
     */
    public TimeCountStrategy createChronometer() {
        return new Chronometer();
    }

    /**
     * Create a time count strategy using the minuter strategy
     *
     * @param minutes Number of minutes to set in the minuter
     * @param second Number of seconds to set in the minuter
     * @return A time count strategy using the minuter strategy which begin at minutes:seconds
     */
    public TimeCountStrategy createMinuter(int minutes, int second) {
        return new Minuter(minutes, second);
    }

}