import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public abstract class Collectable extends Actor {
    private Picture sprite;
    
    public Collectable(String spritePath) {
        sprite = new Picture(spritePath);
        sprite.resize(50, 50);
    }
    
    protected abstract void onCollect(Ninja ninja);
    
    public void update() {
        setPicture(sprite);
        
        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                onCollect((Ninja) actor);
                getStage().removeActor(this);
            }
        }
    }
}