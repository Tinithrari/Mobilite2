package fr.ua.heugue_ydee.environment;

/**
 * Permits an object wbich can be destructed to be observed
 */
public interface DestroyableObservable {

    /**
     * Add an observer to the list of item to notify
     *
     * @param observer The observer to add to the list
     */
    void addDestroyableObserver(DestroyableObserver observer);

    /**
     * Remove an observer from the list of item to notify
     *
     * @param observer The observer to remove from the list
     */
    void removeDestroyableObserver(DestroyableObserver observer);

    /**
     * Notify all object of the destruction of the current object
     */
    void notifyDestroyableObservers();

}