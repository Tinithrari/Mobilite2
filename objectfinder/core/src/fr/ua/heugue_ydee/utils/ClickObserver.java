package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface to observe click event
 */
public interface ClickObserver {

    /**
     * Permit your object to be notified for a click event
     *
     * @param position The position of the click
     */
    void notifyClick(Vector2 position);

}