package fr.ua.heugue_ydee.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.ua.heugue_ydee.physics.ParticlePhysics;

/**
 * Created by xavie on 01/03/2018.
 */

public class CameraGesture extends ParticlePhysics{
    private OrthographicCamera camera;

    private Rectangle clickZone;

    /**
     * Create a physic encapsulation for a camera
     *
     * @param camera
     * @param mass
     */
    public CameraGesture(OrthographicCamera camera, float mass) {
        super(new Vector2(camera.position.x, camera.position.y), mass);
        this.camera = camera;
        this.clickZone = null;
    }

    /**
     * Update the camera position
     *
     * @param delta the delta time between two update
     */
    public void update(float delta) {
        super.update(delta);
        this.camera.position.x = super.getPosition().x;
        this.camera.position.y = super.getPosition().y;
    }
}
