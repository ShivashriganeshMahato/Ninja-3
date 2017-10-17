import java.awt.*;

/**
 * Harmful entity that moves linearly towards an inputted destination point
 *
 * @author Shivashriganesh Mahato
 */
public class Bullet extends Obstacle {
    private Point dest;
    private float vx;
    private float vy;
    private float speed = 2;

    public Bullet(int destX, int destY) {
        super("resources/sprites/Bullet.png", 1, 20, 20);

        vx = vy = 0;
        dest = new Point(destX, destY);
    }

    @Override
    public void update() {
        super.update();

        // Define the x and y velocities at the start of the Bullet's life. We cannot put this in the constructor
        // because the Bullet isn't added to the world yet when the constructor is called, so it had no x and y position
        if (vx == 0) {
            // Identify the angle between the turret and the destination, and calculate an appropriate y velocity. X
            // velocity should always be the same
            double angle = Math.atan2(Math.abs(dest.y - getY()), Math.abs(dest.x - getX()));
            vx = Math.signum(dest.x - getX()) * speed;
            vy = (float) (-Math.signum(dest.y - getY()) * Math.abs(vx) * Math.tan(angle));
        }

        move(vx, "East");
        move(vy, "North");

        // Remove this Actor if it goes out of the screen (other than if it goes off from the left, as this is handled
        // in Level)
        if (getX() > 800 + getWidth() / 2 || getY() > 600 + getHeight() / 2 || getY() < -getHeight() / 2) {
            if (getStage() != null)
                getStage().removeActor(this);
        }
    }

    /**
     * Event that is triggered when this Bullet hits the level's Ninja. Bullet deducts points from ninja and removes
     * itself from the stage
     *
     * @param ninja The level's Ninja that is hit
     */
    @Override
    protected void onHit(Ninja ninja) {
        super.onHit(ninja);
        getStage().removeActor(this);
    }
}
