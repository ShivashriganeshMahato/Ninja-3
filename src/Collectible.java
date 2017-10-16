import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public abstract class Collectible extends Actor {
    private Picture sprite;
    
    public Collectible(String spritePath) {
        sprite = new Picture(spritePath);
        sprite.resize(50, 50);
    }

    /**
     * Event that is triggered when this Actor comes in contact with the level's Ninja, indicating is has been "picked
     * up"
     *
     * @param ninja The level's Ninja that has "collected" this item
     */
    protected abstract void onCollect(Ninja ninja);
    
    public void update() {
        setPicture(sprite);

        // Fire onCollect event and remove self from stage on contact with ninja
        for (Actor actor : getTouching()) {
            if (actor instanceof Ninja) {
                onCollect((Ninja) actor);
                getStage().removeActor(this);
            }
        }
    }
}