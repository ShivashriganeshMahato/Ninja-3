import mayflower.Actor;
import mayflower.Timer;

/**
 * Stationary Actor in world that generates Bullets to shoot at character every few seconds
 *
 * @author Shivashriganesh Mahato
 */
public class Turret extends Actor {
    private Timer timer;

    public Turret() {
        timer = new Timer();
    }

    @Override
    public void update() {
        setPicture("resources/sprites/Turret.png");

        // Every 3 seconds, shoot a bullet towards the position of the Ninja at that time
        if (timer.hasTimePassed(3000)) {
            GameStage stage = (GameStage) getStage();
            Ninja ninja = stage.getGameChar();
            stage.addActor(new Bullet(ninja.getX(), ninja.getY()), getX(), getY());
            timer.reset();
        }
    }
}
