package fr.ua.heugue_ydee.environment;

/**
 * 
 */
public interface DestroyableObservable {

    /**
     * @param observer
     */
    void addDestroyableObserver(DestroyableObserver observer);

    /**
     * @param observer
     */
    void removeDestroyableObserver(DestroyableObserver observer);

    /**
     * 
     */
    void notifyDestroyableObservers();

}