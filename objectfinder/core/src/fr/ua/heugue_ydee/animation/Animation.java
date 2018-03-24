package fr.ua.heugue_ydee.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'une animation en mémoire
 */
public class Animation {

    private Texture texture;
    private List<TextureRegion> frames;

    /**
     * Constructeur de l'Animation
     *
     * @param texture
     */
    public Animation(Texture texture){
        this.texture = texture;
        this.frames = new ArrayList<TextureRegion>();
    }

    /**
     * Retourne la frame associée à l'index
     *
     * @param index l'index de la frame
     * @return La frame correspondant
     */
    public TextureRegion getFrame(int index){
        return this.frames.get(index);
    }

    /**
     * Retourne le nombre de frame composant l'animation
     *
     * @return la taille de la liste de frames
     */
    public int size(){
        return this.frames.size();
    }

    /**
     * Ajoute une frame a notre liste courante
     *
     * @param rectangle
     */
    public void addFrame(Rectangle rectangle){
        TextureRegion frame = new TextureRegion(texture, (int)rectangle.getX(), (int)rectangle.getY(),
                (int)rectangle.width, (int)rectangle.height);
        this.frames.add(frame);
    }

    /**
     * Retourne la texture associée à l'animation
     *
     * @return la texture
     */
    public Texture getTexture() {
        return texture;
    }
}
