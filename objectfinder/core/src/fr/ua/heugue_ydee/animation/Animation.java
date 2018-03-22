package fr.ua.heugue_ydee.animation;

import com.badlogic.gdx.graphics.Texture;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aranys on 22/03/2018.
 */

public class Animation {

    private Texture texture;
    private List<Rectangle> nosRectangles;

    /**
     * Constructeur de l'Animation
     *
     * @param texture
     */
    public Animation(Texture texture){
        this.texture = texture;
        this.nosRectangles = new ArrayList<Rectangle>();
    }

    /**
     *
     * @param index
     * @return le Rectangle correspondant
     */
    public Rectangle getFrame(int index){
        return this.nosRectangles.get(index);
    }

    /**
     *
     * @return la taille de la liste de nos rectangles
     */
    public int size(){
        return this.nosRectangles.size();
    }

    /**
     * Ajoute un rectangle a notre liste courante
     *
     * @param rectangle
     */
    public void addFrame(Rectangle rectangle){
        this.nosRectangles.add(rectangle);
    }

    /**
     *
     * @return la texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Modification de la texture
     *
     * @param texture
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
