package fr.ua.heugue_ydee.environment;

/**
 * Permits to observe object which can be destructed
 */
public interface DestroyableObserver {

    /**
     * Notify this object of the destruction of an object
     */
    void notifyDestroyableObserver(DestroyableObservable obs);

}