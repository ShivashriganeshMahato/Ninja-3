import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */
public class KitKat extends Collectable {
    public KitKat() {
        super("resources/sprites/KitKat.png");
    }
    
    public void onCollect(Ninja ninja) {
        ninja.addPoints(1);
    }
}
