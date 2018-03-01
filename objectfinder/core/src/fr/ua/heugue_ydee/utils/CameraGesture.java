package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import fr.ua.heugue_ydee.physics.ParticlePhysics;

/**
 * Camera wrapper to manage movement
 */
public class CameraGesture extends ParticlePhysics implements InputProcessor {
    private Camera camera;

    private Rectangle clickZone;
    private Vector2 oldPosition;

    private boolean dragging;

    /**
     * Create a physic encapsulation for a camera
     *
     * @param camera The camera to control
     * @param mass The mass of the camera
     */
    public CameraGesture(Camera camera, float mass) {
        super(new Vector2(camera.position.x, camera.position.y), mass);
        this.camera = camera;
        this.dragging = false;
        this.clickZone = null;
        this.oldPosition = null;
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
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (pointer > 0 || button != Input.Buttons.LEFT)
            return false;

        this.oldPosition = null;
        this.dragging = false;
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
}
