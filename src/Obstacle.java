import mayflower.*;

/**
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
        
        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                onHit((Ninja) actor);
            }
        }
    }

    protected void onHit(Ninja ninja) {
        ninja.lowerLives(damage);
    }
}
