package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

import fr.ua.heugue_ydee.environment.Terrain;
import fr.ua.heugue_ydee.physics.ParticlePhysics;

/**
 * Camera wrapper to manage movement
 */
public class CameraGesture extends ParticlePhysics implements InputProcessor, ClickObservable {
    private Camera camera;

    private Rectangle clickZone;
    private Vector2 oldPosition;
    private List<ClickObserver> clickObservers;
    private Terrain terrain;

    private boolean dragging;

    private final float PERCENTAGE = 0.025f;

    /**
     * Create a physic encapsulation for a camera
     *
     * @param camera The camera to control
     * @param mass The mass of the camera
     * @param terrain The terrain to ankle the camera
     */
    public CameraGesture(Camera camera, float mass, Terrain terrain) {
        super(new Vector2(camera.position.x, camera.position.y), mass);
        this.camera = camera;
        this.dragging = false;
        this.clickZone = null;
        this.oldPosition = null;
        this.clickObservers = new LinkedList<ClickObserver>();
        this.terrain = terrain;
    }

    /**
     * Update the camera position
     *
     * @param delta the delta time between two update
     */
    public void update(float delta) {
        super.update(delta);
        this.camera.translate(
                new Vector3(super.getPosition().x, super.getPosition().y, 0).add(new Vector3(this.camera.position).scl(-1))
        );
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (pointer > 0 || button != Input.Buttons.LEFT)
            return false;

        this.oldPosition = new Vector2(x, y);
        this.dragging = true;
        int widthper = (int)(Gdx.graphics.getWidth() * PERCENTAGE);
        int heightper = (int)(Gdx.graphics.getHeight() * PERCENTAGE);
        this.clickZone = new Rectangle(x - widthper, y - heightper, widthper * 2, heightper * 2);
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (pointer > 0 || button != Input.Buttons.LEFT)
            return false;

        this.oldPosition = null;
        this.dragging = false;
        if (clickZone != null) {
            Vector3 position = camera.unproject(new Vector3(x, y, 0));
            Vector2 position2D = new Vector2(position.x, position.y);
            notifyObserver(position2D);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (! dragging)
            return false;

        Vector2 position = new Vector2(x, y);
        Vector2 forces = position.add(new Vector2(oldPosition).scl(-1));
        forces = new Vector2(-forces.x, forces.y);
        this.addForce(forces);
        this.oldPosition = new Vector2(x, y);

        if (clickZone != null) {
            if (! clickZone.contains(x, y))
                clickZone = null;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    /**
     * Add a click observer to the list of this observable object
     *
     * @param clickObserver The click observer to add
     */
    @Override
    public void addClickObserver(ClickObserver clickObserver) {
        this.clickObservers.add(clickObserver);
    }

    /**
     * Remove a click observer from the list of this observable object
     *
     * @param clickObserver The clickObserver to remove
     */
    @Override
    public void removeClickObserver(ClickObserver clickObserver) {
        this.clickObservers.remove(clickObserver);
    }

    /**
     * Notify all observer of the list
     */
    @Override
    public void notifyObserver(Vector2 position) {
        for (ClickObserver obs : clickObservers) {
            obs.notifyClick(position);
        }
    }
}
