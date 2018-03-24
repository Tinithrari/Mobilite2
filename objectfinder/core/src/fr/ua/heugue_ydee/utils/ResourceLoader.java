package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import fr.ua.heugue_ydee.animation.Animation;
import fr.ua.heugue_ydee.animation.AnimationStore;

/**
 * Created by xavie on 22/03/2018.
 */

public class ResourceLoader {
    public static final String DUCK_TEXTURE = "sprites/duck.png";
    public static final String WATER_TEXTURE = "sprites/water.png";
    public static final String ROCKS_TEXTURE = "sprites/rocks.png";
    public static final String GRASS_TEXTURE = "sprites/grass.png";
    public static final String SHEEP_TEXTURE = "sprites/sheep.png";
    public static final String RAIN_TEXTURE = "sprites/splash.png";
    public static final String EGG_TEXTURE = "sprites/egg.png";

    public static final String WATER_ANIMATION = "water_animation";

    public static final String WHITE_DUCK_RIGHT = "white_duck_right";
    public static final String WHITE_DUCK_UP = "white_duck_up";
    public static final String WHITE_DUCK_DOWN = "white_duck_down";
    public static final String WHITE_DUCK_LEFT = "white_duck_left";

    public static final String WHITE_SHEEP_RIGHT = "white_sheep_right";
    public static final String WHITE_SHEEP_UP = "white_sheep_up";
    public static final String WHITE_SHEEP_DOWN = "white_sheep_down";
    public static final String WHITE_SHEEP_LEFT = "white_sheep_left";

    public static final String RAIN_ANIMATION = "rain_animation";

    public static final String EGG_ANIMATION = "egg_animation";
    
    private static void loadDuck() {
        TextureStore textureStore = TextureStore.get();
        Texture duckTexture = textureStore.getTexture(DUCK_TEXTURE);

        Animation duckDownAnimation = new Animation(duckTexture);
        Animation duckLeftAnimation = new Animation(duckTexture);
        Animation duckRightAnimation = new Animation(duckTexture);
        Animation duckUpAnimation = new Animation(duckTexture);
        Rectangle frame = new Rectangle(144, 0, 48, 48);

        duckDownAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            duckDownAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_DUCK_DOWN, duckDownAnimation);

        frame.x = 144;
        frame.y += 48;

        duckLeftAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            duckLeftAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_DUCK_LEFT, duckLeftAnimation);

        frame.x = 144;
        frame.y += 48;

        duckRightAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            duckRightAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_DUCK_RIGHT, duckRightAnimation);

        frame.x = 144;
        frame.y += 48;

        duckUpAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            duckUpAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_DUCK_UP, duckUpAnimation);
    }

    private static void loadSheep() {
        TextureStore textureStore = TextureStore.get();
        Texture duckTexture = textureStore.getTexture(SHEEP_TEXTURE);

        Animation sheepDownAnimation = new Animation(duckTexture);
        Animation sheepLeftAnimation = new Animation(duckTexture);
        Animation sheepRightAnimation = new Animation(duckTexture);
        Animation sheepUpAnimation = new Animation(duckTexture);
        Rectangle frame = new Rectangle(144, 0, 48, 48);

        sheepDownAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            sheepDownAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_SHEEP_DOWN, sheepDownAnimation);

        frame.x = 144;
        frame.y += 48;

        sheepLeftAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            sheepLeftAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_SHEEP_LEFT, sheepLeftAnimation);

        frame.x = 144;
        frame.y += 48;

        sheepRightAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            sheepRightAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_SHEEP_RIGHT, sheepRightAnimation);

        frame.x = 144;
        frame.y += 48;

        sheepUpAnimation.addFrame(frame);
        for (int i = 0; i < 2; ++i) {
            frame.x += 48;
            sheepUpAnimation.addFrame(frame);
        }
        AnimationStore.get().add(WHITE_SHEEP_UP, sheepUpAnimation);
    }

    private static void loadWater() {
        TextureStore textureStore = TextureStore.get();
        Texture waterTexture = textureStore.getTexture(WATER_TEXTURE);

        Animation waterAnimation = new Animation(waterTexture);

        Rectangle frame = new Rectangle(0, 0, 210, 210);

        for (int i = 0; i < 2940; i += 210) {
            frame.x = i;
            waterAnimation.addFrame(frame);
        }

        AnimationStore.get().add(WATER_ANIMATION, waterAnimation);
    }

    private static void loadRain() {
        Texture pluie = TextureStore.get().getTexture(RAIN_TEXTURE);
        Animation animPluie = new Animation(pluie);
        Rectangle frame = new Rectangle();
        for (int i = 0; i < 192; i += 64) {
            frame.y = i;
            for (int j = 0; j < 192; j += 64) {
                frame.x = j;
                animPluie.addFrame(frame);
            }
        }

        AnimationStore.get().add(RAIN_ANIMATION, animPluie);
    }

    private static void loadTerrain() {
        TextureStore textureStore = TextureStore.get();
        textureStore.getTexture(ROCKS_TEXTURE);
        textureStore.getTexture(GRASS_TEXTURE);
    }

    private static void loadEgg() {
        TextureStore textureStore = TextureStore.get();
        Texture eggTexture = textureStore.getTexture(EGG_TEXTURE);

        Animation egg = new Animation(eggTexture);
        Rectangle frame = new Rectangle(0, 0, 76, 78);

        for (int i = 0; i < 304; i+=76) {
            frame.x = i;
            egg.addFrame(frame);
        }

        AnimationStore.get().add(EGG_ANIMATION, egg);
    }

    public static void load() {
        TextureStore.get().unload();
        AnimationStore.get().unload();
        loadDuck();
        loadSheep();
        loadWater();
        loadTerrain();
        loadRain();
        loadEgg();
    }
}
