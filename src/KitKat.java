/**
 * Basic Collectible item that gives points to character
 *
 * @author Shivashriganesh Mahato
 */
public class KitKat extends Collectible {
    public KitKat() {
        super("resources/sprites/KitKat.png");
    }

    /**
     * If collected, increase the score of the Ninja that collects it
     */
    public void onCollect(Ninja ninja) {
        ninja.addPoints(1);
    }
}
