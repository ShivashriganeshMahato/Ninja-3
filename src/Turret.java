import mayflower.Actor;
import mayflower.Timer;

/**
 * @author Shivashriganesh Mahato
 */
public class Turret extends Actor {
    private Timer timer;

    public Turret() {
        timer = new Timer();
    }

    @Override
    public void update() {
        setPicture("resources/sprites/Block.png");

        if (timer.hasTimePassed(3000)) {
            GameStage stage = (GameStage) getStage();
            Ninja ninja = stage.getGameChar();
            stage.addActor(new Bullet(ninja.getX(), ninja.getY()), getX(), getY());
            timer.reset();
        }
    }
}
