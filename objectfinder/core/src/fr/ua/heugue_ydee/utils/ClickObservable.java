package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for click observation
 */
public interface ClickObservable {

    /**
     * Add a click observer to the list of this observable object
     *
     * @param clickObserver The click observer to add
     */
    void addClickObserver(ClickObserver clickObserver);

    /**
     * Remove a click observer from the list of this observable object
     *
     * @param clickObserver The clickObserver to remove
     */
    void removeClickObserver(ClickObserver clickObserver);

    /**
     * Notify all observer of the list
     *
     * @param position The position of the click
     */
    void notifyObserver(Vector2 position);

}