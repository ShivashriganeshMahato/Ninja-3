/**
 * Collectible item that provides an abundance in points
 *
 * @author Shivashriganesh Mahato
 */
public class SpecialItem extends Collectible {
    public SpecialItem() {
        super("resources/sprites/SpecialItem.png");
    }

    /**
     * The special item will simply give an abundant amount of points to the Ninja collecting it
     *
     * @param ninja The level's Ninja that has "collected" this item
     */
    @Override
    protected void onCollect(Ninja ninja) {
        ninja.addPoints(10);
    }
}
