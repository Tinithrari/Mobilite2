package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import fr.ua.heugue_ydee.animation.Animation;
import fr.ua.heugue_ydee.animation.AnimationStore;

/**
 * Created by xavie on 22/03/2018.
 */

public class ResourceLoader {
    private static final String DUCK_TEXTURE = "sprites/duck.png";
    public static final String WHITE_DUCK_RIGHT = "white_duck_right";

    private static void loadDuck() {
        TextureStore textureStore = TextureStore.get();
        Texture duckTexture = textureStore.getTexture(DUCK_TEXTURE);

        Animation duckRightAnimation = new Animation(duckTexture);
        Rectangle frame = new Rectangle(144, 96, 48, 48);
        duckRightAnimation.addFrame(frame);
        frame.x += 48;
        duckRightAnimation.addFrame(frame);
        frame.x += 48;
        duckRightAnimation.addFrame(frame);
        AnimationStore.get().add(WHITE_DUCK_RIGHT, duckRightAnimation);
    }

    public static void load() {
        loadDuck();
    }
}
