import java.awt.*;

/**
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

        if (vx == 0) {
            double angle = Math.atan2(Math.abs(dest.y - getY()), Math.abs(dest.x - getX()));
            vx = Math.signum(dest.x - getX()) * speed;
            vy = (float) (-Math.signum(dest.y - getY()) * Math.abs(vx) * Math.tan(angle));
        }

        move(vx, "East");
        move(vy, "North");
    }

    @Override
    protected void onHit(Ninja ninja) {
        super.onHit(ninja);
        getStage().removeActor(this);
    }
}
