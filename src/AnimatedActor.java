import mayflower.*;

/**
 * @author Shivashriganesh Mahato
 */

public class AnimatedActor extends GravityActor {
    private Animation animation;
    private Timer animationTimer;

    public AnimatedActor() {
        animation = null;
        animationTimer = new Timer();
    }

    public void setAnimation(Animation a) {
        animation = a;
        animation.reset();
        animationTimer.reset();
    }

    public Animation getAnimation() {
        return animation;
    }

    public void update() {
        super.update();

        if (animation != null) {
            if (animationTimer.hasTimePassed(animation.getFrameRate())) {
                animationTimer.reset();
                setPicture(animation.getNextFrame());
            }
        }
    }
}