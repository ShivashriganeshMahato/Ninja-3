/**
 * @author Shivashriganesh Mahato
 */
public class SpecialItem extends Collectible {
    public SpecialItem() {
        super("resources/sprites/KitKat.png");
    }

    @Override
    protected void onCollect(Ninja ninja) {
        ninja.addPoints(10);
    }
}
