import mayflower.Actor;
import mayflower.Picture;

/**
 * A harmful entity that is stationary in the world
 *
 * @author Shivashriganesh Mahato
 */
public class Obstacle extends Actor {
    private Picture sprite;
    private int damage;

    public Obstacle(String spritePath, int damage, int w, int h) {
        sprite = new Picture(spritePath);
        sprite.resize(w, h);
        this.damage = damage;
    }

    public Obstacle(String spritePath, int damage) {
        this(spritePath, damage, 80, 80);
    }

    public void update() {
        setPicture(sprite);

        // Check for collision with Ninja. If found, call onHit event to perform on hit action
        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                onHit((Ninja) actor);
            }
        }
    }

    /**
     * Event fired when Ninja comes in contact with this Obstacle
     *
     * @param ninja The Ninja that has come into contact with this Obstacle
     */
    protected void onHit(Ninja ninja) {
        // Default on hit action is to deduct lives from Ninja
        ninja.lowerLives(damage);
    }
}
