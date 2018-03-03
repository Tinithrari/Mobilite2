package fr.ua.heugue_ydee.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by xavie on 01/03/2018.
 */

public abstract class ParticlePhysics {
    private Vector2 forces;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 position;

    private float inverseMass;

    /**
     * Initialize a particle physics
     *
     * @param position Position of the particle
     * @param mass Mass of the particle
     */
    public ParticlePhysics(Vector2 position, float mass) {
        this.position = position;
        this.inverseMass = 1 / mass;

        this.forces = Vector2.Zero;
        this.velocity = Vector2.Zero;
        this.acceleration = Vector2.Zero;
        this.position = Vector2.Zero;
    }

    /**
     * Add force to the particle system
     *
     * @param force The force to add to the particle system
     */
    public final void addForce(Vector2 force) {
        this.forces = this.forces.add(force);
    }

    /**
     * Update the particle system
     * Note: Call it once per frame
     *
     * @param delta the delta time between two update
     */
    public void update(float delta) {
        this.position = this.position.add(this.velocity.scl(delta));
        this.acceleration = this.forces.scl(this.inverseMass);
        this.velocity = this.velocity.add(this.acceleration.scl(delta));

        // Reducing velocity
        this.velocity = this.velocity.add(this.acceleration.scl(delta).scl(-1));

        // Reinitialize force
        this.forces = Vector2.Zero;
    }

    /**
     * Get the position of the particle system
     *
     * @return The position of the particle system
     */
    public Vector2 getPosition() {
        return position;
    }
}
