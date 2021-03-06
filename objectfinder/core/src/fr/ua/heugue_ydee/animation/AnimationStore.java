package fr.ua.heugue_ydee.animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranys on 22/03/2018.
 */

public class AnimationStore {

    private static AnimationStore singleAnimation = new AnimationStore();
    private Map<String, Animation> animations = new HashMap<String, Animation>();;

    private AnimationStore() {
    }

    /**
     * Retourne une instance du store
     *
     * @return l'instance de classe Animation correspondante
     */
    public static AnimationStore get(){ return singleAnimation;}

    /**
     *
     * @param reference
     * @return l'animation correspondante
     */
    public Animation getAnimation(String reference){

        if(animations.get(reference) == null) {
            fail("The animation doesn't exist.");
        }

        return animations.get(reference);
    }

    /**
     * Ajoute l'animation a la map courante
     * @param reference
     * @param animation
     */
    public void add(String reference, Animation animation) {
        if((reference == null) || reference.isEmpty())
            throw new IllegalArgumentException("Reference");
        if (animation == null)
            throw new IllegalArgumentException("Animation");
        if (animations.get(reference) == null)
            animations.put(reference,animation);
    }

    /**
     * Gestion des erreurs en cas d'animation inexistante
     * @param message
     */
    private void fail(String message) {
        System.err.println(message);
        System.exit(0);
    }

    public void unload() {
        this.animations = new HashMap<String, Animation>();
    }
}
