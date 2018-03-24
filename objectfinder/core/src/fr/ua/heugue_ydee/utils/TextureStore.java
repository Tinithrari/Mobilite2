package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranys on 22/03/2018.
 */

public class TextureStore {

    private static TextureStore singleTexture = new TextureStore();
    private Map<String, Texture> textures = new HashMap<String, Texture>();

    private TextureStore() {

    }

    /**
     * Return an instance of the store
     *
     * @return le TextureStore courant
     */
    public static TextureStore get(){
        return singleTexture;
    }

    /**
     *
     * @param reference
     * @return la texture associe a la reference correspondante
     */
    public Texture getTexture(String reference){
        if(textures.get(reference) == null){
            Texture image = new Texture(Gdx.files.internal(reference));
            textures.put(reference,image);
        }

        return textures.get(reference);
    }

    public void unload() {
        textures = new HashMap<String, Texture>();
    }
}
