
import java.util.*;

/**
 * 
 */
public abstract class TimeCounterFactory {

    /**
     * Default constructor
     */
    public TimeCounterFactory() {
    }

    /**
     * @return
     */
    public TimeCountStrategy createChronometer() {
        // TODO implement here
        return null;
    }

    /**
     * @param minutes 
     * @param second 
     * @return
     */
    public TimeCountStrategy createMinuter(long minutes, int second) {
        // TODO implement here
        return null;
    }

}