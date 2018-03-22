package fr.ua.heugue_ydee.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aranys on 22/03/2018.
 */

public class TextureStore {

    private static TextureStore singleTexture = new TextureStore();
    private Map<String, Texture> textures = new HashMap<String, Texture>();

    /**
     * @return le TextureStore courant
     */
    private static TextureStore get(){
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
}
