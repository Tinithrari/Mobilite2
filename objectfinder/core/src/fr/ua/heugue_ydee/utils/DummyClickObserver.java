package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by xavie on 05/03/2018.
 */

public class DummyClickObserver implements ClickObserver {
    /**
     * Permit your object to be notified for a click event
     *
     * @param position The position of the click
     */
    @Override
    public void notifyClick(Vector2 position) {
        System.out.println("A click happened at position: " + position.toString());
    }
}
