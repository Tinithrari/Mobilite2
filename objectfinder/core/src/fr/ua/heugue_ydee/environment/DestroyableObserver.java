package fr.ua.heugue_ydee.environment;

/**
 * Permits to observe object which can be destructed
 */
public interface DestroyableObserver {

    /**
     * Notify this object of the destruction of an object
     *
     * @param obs The observable which fire the event
     */
    void notifyDestroyableObserver(DestroyableObservable obs);

}