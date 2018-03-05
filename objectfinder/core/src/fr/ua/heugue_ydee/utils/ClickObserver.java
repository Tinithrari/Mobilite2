package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 */
public interface ClickObserver {

    /**
     * @param position
     */
    void notifyClick(Vector2 position);

}