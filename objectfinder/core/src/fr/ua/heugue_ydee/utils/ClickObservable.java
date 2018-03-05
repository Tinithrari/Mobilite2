package fr.ua.heugue_ydee.utils;

/**
 * 
 */
public interface ClickObservable {

    /**
     * @param clickObserver
     */
    void addClickObserver(ClickObserver clickObserver);

    /**
     * @param clickObserver
     */
    void removeClickObserver(ClickObserver clickObserver);

    /**
     * 
     */
    void notifyObserver();

}